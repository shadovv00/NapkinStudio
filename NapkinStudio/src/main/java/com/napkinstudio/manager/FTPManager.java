package com.napkinstudio.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.napkinstudio.entity.*;
import com.napkinstudio.util.*;
import com.napkinstudio.util.Orders;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.napkinstudio.sapcommunicationmodels.DataTransferFromSAP;
import com.napkinstudio.sapcommunicationmodels.DataTransferToSAP;
//import com.napkinstudio.sapcommunicationmodels.DataTransferFromSAP;
//import com.napkinstudio.sapcommunicationmodels.DataTransferToSAP;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@Service("ftpService")
@PropertySource({ "classpath:project.properties" })
public class FTPManager {
	
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

	private BCryptPasswordEncoder encoder;


//	@Autowired
	FTPSClient ftpClient;
	
	public String handle() {
		System.out.println("FTPManager start");
		encoder = new BCryptPasswordEncoder();

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
		
		String 	pathToIsBusyFile = "checkisbusy.txt",
				pathToKeepInSyncFile = "keepinsync.txt",
				pathToFileToSAP = "DataTransferToSAP.xml",
				pathToFileFromSAP = "DataTransferFromSAP.xml",
				sPathToOrdersDirectory = "orders";
		
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
        
        
		try {
			// Connect to host
			ftpClient = new FTPSClient(false);
			ftpClient.connect(host, port);
			int reply = ftpClient.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {

				// Login
				if (ftpClient.login(username, password)) {
					// Set protection buffer size
					ftpClient.execPBSZ(0);
					// Set data channel protection to private
					ftpClient.execPROT("P");
					// Enter local passive mode
					ftpClient.enterLocalPassiveMode();
					
//					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    SynchronizationDate synchroData= synchro_dateManager.findById(1);
//					System.out.println(synchroData);
					if (synchroData==null){synchroData=new SynchronizationDate();}
                    synchroData.setId(1);

					is_ = ftpClient.retrieveFileStream(pathToIsBusyFile);
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
								os_ = ftpClient.storeFileStream(pathToIsBusyFile);
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
					
					
					
					is0 = ftpClient.retrieveFileStream(pathToKeepInSyncFile);	
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
		            	is1 = ftpClient.retrieveFileStream(pathToFileFromSAP);
		            	if(is1 != null) {
							xstream.processAnnotations(DataTransferFromSAP.class);
							dtfs = (DataTransferFromSAP) xstream.fromXML(is1);
                            //write to db

							if(dtfs.getSapOrders()!=null&&dtfs.getSapOrders().getOrders()!=null) {
								LinkedList<Order> orders = dtfs.getSapOrders().getOrders();
//								System.out.println("orders=" + dtfs.getSapOrders());
//								System.out.println("orders.=" + dtfs.getSapOrders().getOrders());
//								System.out.println("orders.size=" + orders.size());
								for (Order order : orders) {
									Order thisOrder = orderManager.findById(order.getOrderId());
//								for each field update
//								for(Field field : order.getClass().getDeclaredFields()){
//									System.out.println(field);
//									if (field.get()!=null){}
//								}
//								order.setUpdate(new Date());
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
										if (userSAP.getPassword()!=null&&userSAP.getPassword()!=""){
											thisUser.setPassword(userSAP.getPassword());
										}
										thisUser.setEmail(userSAP.getEmail());
										thisUser.setEnabled(userSAP.getEnabled());
//										thisUser.setEnabled(true);
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
//									if (userOrderManager.findOrdersByUserAndOrderId(userManager.findByEmail(userOrdersSAP.getUser()).getUserId(),userOrdersSAP.getOrder())!=null){
//										continue;
//									}
									UserOrder newUserOrder = new UserOrder();
									User ordersUser = userManager.findByEmail(userOrdersSAP.getUser());
									List<UserOrder> oldUserOrders = userOrderManager.findUserforOrdedByRole(userOrdersSAP.getOrder(), roleManager.findByUserId(ordersUser.getUserId()).getId());
									if(oldUserOrders.size()>0){
										newUserOrder =oldUserOrders.get(0);
//										newUserOrder =userOrderManager.findOrdersByUserAndOrderId(ordersUser.getUserId(),userOrdersSAP.getOrder());
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
//						fileTransfer.transferOrdersFromFtpToLocalStorage(ftpClient);
		            	
		            }
		            if(fileToSAPStatus.equals("accepted")) {
		            	//Portal file was accepted by SAP, so new portal file can be uploaded
		            	os2 = ftpClient.storeFileStream(pathToFileToSAP);
		            	if(os2 != null) {
		            		dtts = new DataTransferToSAP();
//			                User us = new User();
//			                us.setEnabled(true);
//			                us.setFirstName("Cheryl");
//			                us.setLastName("Brooks");
//			                us.setLastModifiedDate(new Date());
//			                us.setLogin("trdd");
//			                dtts.setUser(us);
			                //read from db
							System.out.print("/////////////////////////orderManager///////////////////////////");
//							Orders ordersToSAP =new Orders();
//							LinkedList<Order> outOrders = orderManager.getUpdatedOrders(synchroData.getDateToSAP());
//							if (outOrders!=null){
//								for (Order outOrder : outOrders) {
//									outOrder.setItsUsers(null);
//									outOrder.setComments(null);
//									outOrder.setStatusChanges(null);
//								}
//								System.out.print(outOrders.size());
//								ordersToSAP.setOrders(outOrders);
//								dtts.setSapOrders(ordersToSAP);
//							}
//							TODO: Get from DB already formed OrderToSAP
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
//			USERS to SAP
//							TODO: Get from DB already formed UserToSAP
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
//									TODO: decode pass? - not neccecary any more
//									newUserToSAP.setPassword(outUser.getPassword());
									newUserToSAP.setRole(outUser.getRole().getId());
//									TODO: correct lazy connection
//									newUserToSAP.setRole(roleManager.findByUserId(outUser.getUserId()).getId());
									outUserList.add(newUserToSAP);
								}
								usersToSAP.setUsers(outUserList);
								dtts.setSapUsers(usersToSAP);
							}

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
		            		System.out.println(ftpClient.getReplyString());
		            	}
		            } else if(fileToSAPStatus.equals("delivered")) {
		            	//Portal file has not been accepted by SAP yet, so new portal file must not be uploaded
		            	System.out.println("Portal file has not been accepted by SAP yet, so new portal file must not be uploaded!");
		            }
		            //save date of the last synchronization to DB
		            synchro_dateManager.save(synchroData);
		            bytes = ("fileFromSAPStatus:\t" + fileFromSAPStatus + "\r\n"
		            		+ "fileToSAPStatus:\t" + fileToSAPStatus + "\r\n").getBytes();
		            os0 = ftpClient.storeFileStream("keepinsync.txt");
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
		            	os_ = ftpClient.storeFileStream(pathToIsBusyFile);
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
					
					// Logout
					ftpClient.logout();

				} else {
					System.out.println("FTP login failed");
				}

				// Disconnect
				ftpClient.disconnect();
				System.out.println("operations over!");
			} else {
				System.out.println("FTP connect to host failed");
			}
		} catch (IOException ioe) {
			System.out.println("FTP client received network error");
			message = "FTP client received network error";
			ioe.printStackTrace();
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
			if(ftpClient != null) {
				try {
					// Logout
//					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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


