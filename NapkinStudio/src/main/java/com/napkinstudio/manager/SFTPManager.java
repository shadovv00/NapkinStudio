package com.napkinstudio.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.napkinstudio.entity.Attachment;
import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.StatusChange;
import com.napkinstudio.entity.SynchronizationDate;
import com.napkinstudio.entity.User;
import com.napkinstudio.entity.UserOrder;
import com.napkinstudio.sapcommunicationmodels.DataTransferFromSAP;
import com.napkinstudio.sapcommunicationmodels.DataTransferToSAP;
import com.napkinstudio.util.AttachmentFormSAP;
import com.napkinstudio.util.CommentFromSAP;
import com.napkinstudio.util.FileTransfer;
import com.napkinstudio.util.OrderToSAP;
import com.napkinstudio.util.OrdersToSAP;
import com.napkinstudio.util.StatusChangeFromSAP;
import com.napkinstudio.util.UserOrderFromSAP;
import com.napkinstudio.util.UserToSAP;
import com.napkinstudio.util.UsersToSAP;
import com.thoughtworks.xstream.XStream;

@Service("sftpService")
@PropertySource({ "classpath:project.properties" })
public class SFTPManager {

	@Autowired
    Environment env;
	
	@Autowired
	XStream xstream;

	@Autowired
	private OrderManager orderManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private CommentsManager commentsManager;

	@Autowired
	private UserOrderManager userOrderManager;

    @Autowired
    private SAPstatusManager sapStatusManager;

    @Autowired
    private StatusChangeManager statusChangeManager;

	@Autowired
	private AttachmentManager attachmentManager;

	@Autowired
	private SynchronizationDateManager synchro_dateManager;

	@Autowired
	private FileTransfer fileTransfer;


//	@Autowired
	FTPSClient ftpClient;
	
	public String handle() {
		System.out.println("FTPManager start");

		String message = "ok";
		
		String
				host = env.getProperty("ftp.host"),//"localhost",//"10.4.0.129",//194.44.213.118:44808
//				host = "10.4.0.129",
//				host = "194.44.213.118:44",
				username = env.getProperty("ftp.user"),//"catdogcat",
				password = env.getProperty("ftp.pass");//"2cats1dog";
//				username = "ftpuser",
//				password = "123";

		int 	port = Integer.parseInt(env.getProperty("ftp.port"));
		
		String 	pathToIsBusyFile = "dir/checkisbusy.txt",
				pathToKeepInSyncFile = "dir/keepinsync.txt",
				pathToFileToSAP = "dir/DataTransferToSAP.xml",
				pathToFileFromSAP = "dir/DataTransferFromSAP.xml",
				sPathToOrdersDirectory = "dir/orders";
		
        InputStream
        		is_ = null,
        		is0 = null,
        		is1 = null;
        OutputStream
        		os_ = null,
        		os0 = null,
        		os1 = null,
        		os2 = null;
        String str;
        String[] strArr;
        String 	fileFromSAPStatus = "delivered",
        		fileToSAPStatus = "accepted";
        boolean	isBusyChanged = false;
        byte[] bytes;
        DataTransferFromSAP dtfs;
        DataTransferToSAP dtts;
        
        BufferedReader reader = null;
        StringBuffer buf = new StringBuffer(1000);
        
        JSch jsch = new JSch();

        Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;

		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			session.connect();
			channel = session.openChannel( "sftp" );
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			SynchronizationDate synchroData= synchro_dateManager.findById(1);
//			System.out.println(synchroData);
			if (synchroData==null){synchroData=new SynchronizationDate();}
            synchroData.setId(1);

			is_ = sftpChannel.get(pathToIsBusyFile);
			if(is_ != null) {
				reader = new BufferedReader(new InputStreamReader(is_));
				if((str = reader.readLine()) != null) {
					if(str.equals("busy")) {
						System.out.println("Wait your turn!");
						return "Wait your turn!";
					} else {
						System.out.println("It's your turn!");
						isBusyChanged = true;
						reader.close();
						is_.close();
						System.out.println("is_ is closed? Answer: " + ftpClient.completePendingCommand());
						os_ = sftpChannel.put(pathToIsBusyFile);
						if(os_ != null) {
							os_.write("busy".getBytes());
							os_.flush();
							os_.close();
							System.out.println("2)os_ is closed? Answer: " + ftpClient.completePendingCommand());
						} else {
							System.out.println("Can't open outputstream <os_> to checkisbusy.txt!");
		            		System.out.println(ftpClient.getReplyString());
		            		return "2) Can't connect to checkisbusy.txt!";
						}
					}
				} else {
					reader.close();
					System.out.println("Wait your turn!");
					return "Wait your turn!";
				}
			} else {
				System.out.println("Can't open inputstream <is_> to checkisbusy.txt!");
        		System.out.println(ftpClient.getReplyString());
        		return "1) Can't connect to checkisbusy.txt!";
			}
			
			
			
			is0 = sftpChannel.get(pathToKeepInSyncFile);	
			if (is0 != null) {
				reader = new BufferedReader(new InputStreamReader(is0));
                while ((str = reader.readLine()) != null) {
                	strArr = str.split(":");
                	if(strArr.length >= 2) {
                		if(strArr[0].trim().equals("fileFromSAPStatus")) {
                			if(strArr[1].trim().equals("accepted")) {
                				fileFromSAPStatus = "accepted";
                			} else if(strArr[1].trim().equals("delivered")) {
                				fileFromSAPStatus = "delivered";
                			}
                			if(strArr[1].trim().equals("accepted")) {
                				fileFromSAPStatus = "accepted";
                			} else if(strArr[1].trim().equals("delivered")) {
                				fileFromSAPStatus = "delivered";
                			}
                		}
                		if(strArr[0].trim().equals("fileToSAPStatus")) {
                			if(strArr[1].trim().equals("accepted")) {
                				fileToSAPStatus = "accepted";
                			} else if(strArr[1].trim().equals("delivered")) {
                				fileToSAPStatus = "delivered";
                			}
                			if(strArr[1].trim().equals("accepted")) {
                				fileToSAPStatus = "accepted";
                			} else if(strArr[1].trim().equals("delivered")) {
                				fileToSAPStatus = "delivered";
                			}
                		}
                	}
                	System.out.println(str);
                    buf.append(str + "\n" );
                }
                reader.close();
                is0.close();
                System.out.println("is0 is closed? Answer: " + ftpClient.completePendingCommand());
            } else {
            	System.out.println("Can't open inputstream <is0> to keepinsyck.txt!");
        		System.out.println(ftpClient.getReplyString());
        		return "Can't connect to keepinsync.txt!";
            }
			
			
            if(fileFromSAPStatus.equals("accepted")) {
            	System.out.println("File from SAP has been already accepted!");
            } else if(fileFromSAPStatus.equals("delivered")) {
            	//SAP file was delivered by SAP, so Portal must accept it
            	is1 = sftpChannel.get(pathToFileFromSAP);
            	if(is1 != null) {
					xstream.processAnnotations(DataTransferFromSAP.class);
					dtfs = (DataTransferFromSAP) xstream.fromXML(is1);
                    //write to db

					if(dtfs.getSapOrders()!=null&&dtfs.getSapOrders().getOrders()!=null) {
						LinkedList<Order> orders = dtfs.getSapOrders().getOrders();
//						System.out.println("orders=" + dtfs.getSapOrders());
//						System.out.println("orders.=" + dtfs.getSapOrders().getOrders());
//						System.out.println("orders.size=" + orders.size());
						for (Order order : orders) {
							Order thisOrder = orderManager.findById(order.getOrderId());
//						for each field update
//						for(Field field : order.getClass().getDeclaredFields()){
//							System.out.println(field);
//							if (field.get()!=null){}
//						}
//						order.setUpdate(new Date());
							if (thisOrder != null ) {
								order.setProcessId(thisOrder.getProcessId());
								if (order.getLastModifiedDate() != null && thisOrder.getLastModifiedDate().after(order.getLastModifiedDate())){
									order.setLastModifiedDate(thisOrder.getLastModifiedDate());
								}
								if (order.getSapStatus().getId()!=6){
									order.setRejected(false);
								}
							}
							orderManager.save(order);
						}
					}
					if(dtfs.getSapUsers()!=null&&dtfs.getSapUsers().getUsers()!=null) {
						LinkedList<User> users = dtfs.getSapUsers().getUsers();
						System.out.println("users.size=" + users.size());
						for (User userSAP : users) {
							User thisUser = userManager.findByEmail(userSAP.getEmail());
							if (thisUser != null) {
								thisUser.setLastName(userSAP.getLastName());
								thisUser.setFirstName(userSAP.getFirstName());
								thisUser.setLogin(userSAP.getLogin());
								if (userSAP.getPassword()!=null){
									thisUser.setPassword(userSAP.getPassword());
								}
								thisUser.setEmail(userSAP.getEmail());
								thisUser.setEnabled(userSAP.getEnabled());
								thisUser.setRole(userSAP.getRole());
								userManager.save(thisUser);
							}else{
								userManager.save(userSAP);
							}
						}
					}
					if(dtfs.getSapComments()!=null&&dtfs.getSapComments().getComments()!=null) {
						LinkedList<CommentFromSAP> comments = dtfs.getSapComments().getComments();
						System.out.println("comments.size=" + comments.size());
						for (CommentFromSAP commentSAP : comments) {
							Comments newComment = new Comments();
							newComment.setCommText(commentSAP.getCommText());
							newComment.setToUser(userManager.findByEmail(commentSAP.getToUser()));
							newComment.setFromUser(userManager.findByEmail(commentSAP.getFromUser()));
							newComment.setForRole(roleManager.findById(commentSAP.getForRole()));
							newComment.setOrder(orderManager.findById(commentSAP.getOrder()));
							newComment.setLastModifiedDate(commentSAP.getDateTime());
							newComment.setDeleted(commentSAP.getDeleted());
							commentsManager.save(newComment);
						}
					}
					if(dtfs.getSapUserOrders()!=null&&dtfs.getSapUserOrders().getUserOrders()!=null) {
						LinkedList<UserOrderFromSAP> userOrders = dtfs.getSapUserOrders().getUserOrders();
						System.out.println("userOrders.size=" + userOrders.size());
						for (UserOrderFromSAP userOrdersSAP : userOrders) {
//							if (userOrderManager.findOrdersByUserAndOrderId(userManager.findByEmail(userOrdersSAP.getUser()).getUserId(),userOrdersSAP.getOrder())!=null){
//								continue;
//							}
							UserOrder newUserOrder = new UserOrder();
							User ordersUser = userManager.findByEmail(userOrdersSAP.getUser());
							if(userOrderManager.findUserforOrdedByRole(userOrdersSAP.getOrder(), roleManager.findByUserId(ordersUser.getUserId()).getId()).size()>0){
								newUserOrder =userOrderManager.findOrdersByUserAndOrderId(ordersUser.getUserId(),userOrdersSAP.getOrder());
							}else{
								newUserOrder.setOrder(orderManager.findById(userOrdersSAP.getOrder()));
							}
							newUserOrder.setUser(ordersUser);
							newUserOrder.setLastLook(new Date(1411419600000L));
							userOrderManager.save(newUserOrder);
						}
					}
                    if(dtfs.getSapStatusChanges()!=null&&dtfs.getSapStatusChanges().getStatusChanges()!=null) {
                        LinkedList<StatusChangeFromSAP> statusChanges = dtfs.getSapStatusChanges().getStatusChanges();
                        System.out.println("statusChanges.size=" + statusChanges.size());
                        for (StatusChangeFromSAP statusChangesSAP : statusChanges) {
                            StatusChange newStatusChange = new StatusChange();
                            newStatusChange.setSAPstatus(sapStatusManager.findById(statusChangesSAP.getSAPstatus()));
                            newStatusChange.setOrder(orderManager.findById(statusChangesSAP.getOrder()));
                            newStatusChange.setDateTime(statusChangesSAP.getDateTime());
                            statusChangeManager.save(newStatusChange);
                        }
                    }
					if(dtfs.getSapAttachments()!=null&&dtfs.getSapAttachments().getAttachments()!=null) {
						LinkedList<AttachmentFormSAP> attachments = dtfs.getSapAttachments().getAttachments();
						LinkedList<Attachment> newAttachmentList = new LinkedList<>();
						System.out.println("attachments.size=" + attachments.size());
						for (AttachmentFormSAP attachmentsSAP : attachments) {
							Attachment newAttachment = new Attachment();
							newAttachment.setRole(roleManager.findById(attachmentsSAP.getRole()));
							newAttachment.setOrder(orderManager.findById(attachmentsSAP.getOrder()));
							newAttachment.setAppendDate(attachmentsSAP.getAppendDate());
							newAttachment.setName(attachmentsSAP.getName());
							newAttachmentList.add(newAttachment);
						}
						attachmentManager.save(newAttachmentList);
					}


					//set date of the "fromSAP" file read
	                synchroData.setDateFromSAP(new Date());
                    synchroData.setErrorFromSAP(false);
	                fileFromSAPStatus = "accepted";
	                is1.close();
	                System.out.println("is1 is closed? Answer: " + ftpClient.completePendingCommand());
            	}

				//here I download attachments from ftp and save them to local storage
//				fileTransfer.transferOrdersFromFtpToLocalStorage(ftpClient);
            	
            }
            if(fileToSAPStatus.equals("accepted")) {
            	//Portal file was accepted by SAP, so new portal file can be uploaded
            	os2 = sftpChannel.put(pathToFileToSAP);
            	if(os2 != null) {
            		dtts = new DataTransferToSAP();
//	                User us = new User();
//	                us.setEnabled(true);
//	                us.setFirstName("Cheryl");
//	                us.setLastName("Brooks");
//	                us.setLastModifiedDate(new Date());
//	                us.setLogin("trdd");
//	                dtts.setUser(us);
	                //read from db
					System.out.print("/////////////////////////orderManager///////////////////////////");
//					Orders ordersToSAP =new Orders();
//					LinkedList<Order> outOrders = orderManager.getUpdatedOrders(synchroData.getDateToSAP());
//					if (outOrders!=null){
//						for (Order outOrder : outOrders) {
//							outOrder.setItsUsers(null);
//							outOrder.setComments(null);
//							outOrder.setStatusChanges(null);
//						}
//						System.out.print(outOrders.size());
//						ordersToSAP.setOrders(outOrders);
//						dtts.setSapOrders(ordersToSAP);
//					}
//					TODO: Get from DB already formed OrderToSAP
					LinkedList<Order> outOrders = orderManager.getUpdatedOrders(synchroData.getDateToSAP());
					if (outOrders!=null){
						System.out.print(outOrders.size());
						OrdersToSAP ordersToSAP =new OrdersToSAP();
						LinkedList<OrderToSAP> outOrderList = new LinkedList<OrderToSAP>();
						for (Order outOrder : outOrders) {
							OrderToSAP newOrderToSAP = new OrderToSAP();
							newOrderToSAP.setOrderId(outOrder.getOrderId());
							newOrderToSAP.setSAPstatus(outOrder.getSapStatus().getId());
							newOrderToSAP.setLastModifiedDate(outOrder.getLastModifiedDate());
							newOrderToSAP.setDeleted(outOrder.getDeleted());
							newOrderToSAP.setRepeated(outOrder.getRepeated());
							newOrderToSAP.setVersion(outOrder.getVersion());
							outOrderList.add(newOrderToSAP);
						}
						ordersToSAP.setOrders(outOrderList);
						dtts.setSapOrders(ordersToSAP);
					}
//					TODO: Get from DB already formed UserToSAP
					LinkedList<User> outUsers = userManager.getUpdatedUsers(synchroData.getDateToSAP());
					if (outUsers!=null){
						System.out.print(outUsers.size());
						UsersToSAP usersToSAP =new UsersToSAP();
						LinkedList<UserToSAP> outUserList = new LinkedList<UserToSAP>();
						for (User outUser : outUsers) {
							UserToSAP newUserToSAP = new UserToSAP();
							newUserToSAP.setLastModifiedDate(outUser.getLastModifiedDate());
							newUserToSAP.setDateTime(outUser.getDateTime());
							newUserToSAP.setEmail(outUser.getEmail());
							newUserToSAP.setEnabled(outUser.getEnabled());
							newUserToSAP.setFirstName(outUser.getFirstName());
							newUserToSAP.setLastName(outUser.getLastName());
							newUserToSAP.setLogin(outUser.getLogin());
//							TODO: decode pass?
							newUserToSAP.setPassword(outUser.getPassword());
							newUserToSAP.setRole(outUser.getRole().getId());
//							TODO: correct lazy connection
//							newUserToSAP.setRole(roleManager.findByUserId(outUser.getUserId()).getId());
							outUserList.add(newUserToSAP);
						}
						usersToSAP.setUsers(outUserList);
						dtts.setSapUsers(usersToSAP);
					}

					//                            LinkedList<Order> outOrders = new LinkedList<Order>();
//					System.out.print(outOrders);

//                    Order s_order = new Order();
//                    s_order.setOrderId(123);
//                    s_order.setDebItemNum("123");
//                    s_order.setApprovalBy("123");
//					SAPstatus s_sapstatus= new SAPstatus();
//					s_sapstatus.setId(1);
//					s_sapstatus.setName("test status name");
//					s_order.setSAPstatus(s_sapstatus);
//                    outOrders.add(s_order);
//                    Order s_order1 = new Order();
//                    s_order1.setOrderId(890);
//                    s_order1.setDebItemNum("890");
//                    s_order1.setApprovalBy("890");
//                    outOrders.add(s_order1);

//					dtts.setOrders(outOrders);


					xstream.processAnnotations(DataTransferToSAP.class);
	                xstream.toXML(dtts, os2);
	                
	                fileToSAPStatus = "delivered";
	                os2.flush();
	                os2.close();
	                System.out.println("os2 is closed? Answer: " + ftpClient.completePendingCommand());
                    //set date of the "toSAP" file write
                    synchroData.setDateToSAP(new Date());
                    synchroData.setErrorToSAP(false);

            	} else {
            		System.out.println("Can't open outputstream <os2> to FileToSAP!");
            	}
            } else if(fileToSAPStatus.equals("delivered")) {
            	//Portal file has not been accepted by SAP yet, so new portal file must not be uploaded
            	System.out.println("Portal file has not been accepted by SAP yet, so new portal file must not be uploaded!");
            }
            //save date of the last synchronization to DB
            synchro_dateManager.save(synchroData);
            bytes = ("fileFromSAPStatus:\t" + fileFromSAPStatus + "\r\n"
            		+ "fileToSAPStatus:\t" + fileToSAPStatus + "\r\n").getBytes();
            os0 = sftpChannel.put("keepinsync.txt");
            if(os0 != null) {
            	os0.write(bytes);
            	os0.flush();
            	os0.close();
            	System.out.println("os0 is closed? Answer: " + ftpClient.completePendingCommand());
            } else {
            	System.out.println("Can't open outputstream <os0> to FileToSAP!");
        		System.out.println(ftpClient.getReplyString());
            }
            
            //change status in checkisbusy.txt file from "busy" to "opened"
            if(isBusyChanged) {
            	os_.close();
            	os_ = sftpChannel.put(pathToIsBusyFile);
    			if(os_ != null) {
    				os_.write("opened".getBytes());
    				os_.flush();
    				os_.close();
    				System.out.println("os_ is closed? Answer: " + ftpClient.completePendingCommand());
    				isBusyChanged = false;
    			} else {
					System.out.println("checkisbusy.txt must be opened again!");
				}
    		}
			

		} catch (IOException ioe) {
			System.out.println("FTP client received network error");
			message = "FTP client received network error";
			ioe.printStackTrace();
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
				
				if(is_ != null) is_.close();
        		if(is0 != null) is0.close();
        		if(is1 != null) is1.close();
        		
        		if(isBusyChanged) {
        			if(os_ == null) {
        				is_ = ftpClient.retrieveFileStream(pathToIsBusyFile);
        			}
        			if(os_ != null) {
        				os_.write("opened".getBytes());
        				os_.flush();
        				os_.close();
        				System.out.println("os_ is closed? Answer: " + ftpClient.completePendingCommand());
        			} else {
						System.out.println("checkisbusy.txt must be opened again!");
					}
        		}
        		if(os0 != null) os0.close();
        		if(os1 != null) os1.close();
        		if(os2 != null) os2.close();
        	} catch (IOException ex) {
                ex.printStackTrace();
            } finally {
            	if(os_ != null)
					try {
						os_.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
			// Disconnect
			if(session != null) {
		        if(sftpChannel != null) {
		        	sftpChannel.exit();
		        }
		        session.disconnect();
			}
		}
		return message;
	}
	
//	private static String getStringFromInputStream(InputStream is) {
//
//		BufferedReader br = null;
//		StringBuilder sb = new StringBuilder();
//
//		String line;
//		try {
//
//			br = new BufferedReader(new InputStreamReader(is));
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return sb.toString();
//
//	}
	
}



