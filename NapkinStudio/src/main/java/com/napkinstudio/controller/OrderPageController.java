package com.napkinstudio.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.FileMeta;
import com.napkinstudio.entity.MultiFile;
import com.napkinstudio.entity.MultiFileBucket;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.SAPstatus;
import com.napkinstudio.entity.StatusChange;
import com.napkinstudio.entity.StatusSAPStatusRole;
import com.napkinstudio.entity.User;
import com.napkinstudio.manager.CommentsManager;
import com.napkinstudio.manager.MailManager;
import com.napkinstudio.manager.OrderManager;
import com.napkinstudio.manager.ProgresBarFieldsManager;
import com.napkinstudio.manager.RoleManager;
import com.napkinstudio.manager.SAPstatusManager;
import com.napkinstudio.manager.StatusChangeManager;
import com.napkinstudio.manager.StatusManager;
import com.napkinstudio.manager.StatusSAPStatusRoleManager;
import com.napkinstudio.manager.UserManager;
import com.napkinstudio.manager.UserOrderManager;
import com.napkinstudio.simplemodel.FileInfo;
import com.napkinstudio.util.MultiFileValidator;


@Controller
public class OrderPageController {

    private int ordId;

    private final static String UPLOAD_LOCATION = "C:/mytemp/";

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private StatusManager statusManager;

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private UserOrderManager userOrderManager;

    @Autowired
    private StatusSAPStatusRoleManager statusSAPStatusRoleManager;

    @Autowired
    private SAPstatusManager sapStatusManager;

    @Autowired
    private ProgresBarFieldsManager progresBarFieldsManager;

    @Autowired
    private StatusChangeManager statusChangeManager;
    @Autowired
    private CommentsManager commentsManager;

    @Autowired
    MultiFileValidator multiFileValidator;

    @Autowired
    private MailManager mailManager;

    @ModelAttribute("user")
    public User user(Principal principal) {
        String login = principal.getName();
        User user = userManager.findByLogin(login);

        List<Role> roles = roleManager.findByUserId(user.getUserId());
        user.setRoles(roles);
        return user;
    }

    @ModelAttribute("comment")
    Comments comments() {
        return new Comments();
    }

//  @RequestMapping(value = "/orders/{orderId}")
//    @ModelAttribute("theOrder")
//    public Order order() {
//            Order order = new Order();
////        Order order = orderManager.findById(orderId);
////        Integer SSId = order.getSAPstatus().getId();
////        Integer roleId = user.getRoles().get(0).getId();
////
////        StatusSAPStatusRole statusSAPStatusRole;
////        try {
////            statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId, SSId);
////            System.out.println(statusSAPStatusRole.getStatus().getName());
////            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
////            statusSAPStatusRolesList.add(statusSAPStatusRole);
////            order.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
////        } catch (NullPointerException e) {
////            e.printStackTrace(System.out);
////        }
//
//        return order;

//    }

    @RequestMapping(value = "/orders/{orderId}")
    public String goToOrders(Model model, @PathVariable("orderId") int orderId, @ModelAttribute("user") User user) {
        ordId = orderId;
        Order theOrder = orderManager.findById(orderId);
        Integer SSId = theOrder.getSAPstatus().getId();
        Integer roleId = user.getRoles().get(0).getId();
//        Map<String, Order> modelMap = new HashMap<>();
//        modelMap.put("theOrder",theOrder);
//        model.mergeAttributes(modelMap);
        MultiFileBucket filesModel = new MultiFileBucket();
        model.addAttribute("multiFileBucket", filesModel);
        StatusSAPStatusRole statusSAPStatusRole;
        try {
            statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId, SSId);
            System.out.println(statusSAPStatusRole.getStatus().getName());
            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
            statusSAPStatusRolesList.add(statusSAPStatusRole);
            theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
        } catch (NullPointerException e) {
            e.printStackTrace(System.out);
        }

//        String login = principal.getName();
//        User user = userManager.findByLogin(login);
//        Order theOrder =orderManager.findById(orderId);

//        List<Role> roles = roleManager.findByUserId(user.getUserId());
//        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());
//        Integer roleId = roles.get(0).getId();
//        Integer SSId = theOrder.getSAPstatus().getId();
//        System.out.println("role=" + roleId + "; SSId=" + SSId);
//        StatusSAPStatusRole statusSAPStatusRole;
//        try {
//            statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId, SSId);
//            System.out.println(statusSAPStatusRole.getStatus().getName());
//            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
//            statusSAPStatusRolesList.add(statusSAPStatusRole);
//            theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
//        } catch (NullPointerException e) {
//            e.printStackTrace(System.out);
//        }
//        user.setRoles(roles);

//        List<ProgresBarFields> barFields = progresBarFieldsManager.findAll();
//        int orderId = theOrder.getOrderId();
//        int roleId = user.getRoles().get(0).getId();
        List<Object[]> barFields = progresBarFieldsManager.findBarByRolePVICheckReject(orderId, roleId, theOrder.getPVIcheckScen(), theOrder.getRejected());
//        System.out.println(barFields);
//        System.out.println(barFields.toString());
//        System.out.println(barFields.size());
        Date initDate = new Date();
        Date dateToComp = new Date();


        for (Iterator<Object[]> iterator = barFields.iterator(); iterator.hasNext(); ) {
            Object[] object = iterator.next();
            System.out.println(object[0] + "; " + object[1]);
////            get real initialization date
            if ((object[0] != null) && ((object[0].equals("Proof requested")) || (object[0].equals("Proof request set up")))) {
                if (object[1] instanceof java.util.Date) {
                    initDate = (java.util.Date) object[1];
                }
            }
//////            remove old data from orevious rounds
            if (object[1] instanceof java.util.Date) {
                dateToComp = (java.util.Date) object[1];
            }
            System.out.println(dateToComp + " compare to " + initDate);
            if (dateToComp.before(initDate)) {
                object[1] = null;
            }
////            correcting progresbar according to onhold
            if (theOrder.getSAPstatus().getId() == 9) {
//                System.out.println(object[1]);
                if (object[1] == null) {
                    iterator.remove();
                }
            } else {
                if (object[0].equals("On hold")) {
//                    System.out.println("rem");
                    iterator.remove();
                }
            }
        }


        try {
            Map<Integer, List<Comments>> commentsMap = commentsManager.findCommentsbyOrderId(orderId);


            model.addAttribute("PVIComments", commentsMap.get(2));
            model.addAttribute("DeptorComments", commentsMap.get(1));
            model.addAttribute("DTPComments", commentsMap.get(4));
            model.addAttribute("CustomerComments", commentsMap.get(5));
            model.addAttribute("StampsManufacComments", commentsMap.get(6));
            model.addAttribute("ProductionComments", commentsMap.get(7));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        //        model.addAttribute("user", user);
//        model.addAttribute("userOrders", userOrders);
        model.addAttribute("theOrder", theOrder);
        model.addAttribute("barFields", barFields);
        model.addAttribute("orderPviCheck", theOrder.getPVIcheckScen());

        return "orderpage";
    }


    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    String doAddComment(@ModelAttribute("comment") Comments comment, @ModelAttribute("user") User user, BindingResult result) {
        int roleId = comment.getForRole().getId();
        int orderId = comment.getOrder().getOrderId();
        User userTo = userOrderManager.findUserforOrdedByRole(orderId, roleId);

        Role role = roleManager.findById(roleId);
        Order order = orderManager.findById(orderId);

        comment.setFromUser(user);
        comment.setToUser(userTo);
        comment.setForRole(role);
        comment.setOrder(order);


        if ((comment.getForRole().getId() == 2 && user.getRoles().get(0).getId() != 2) || comment.getForRole().getId() == 4) {
            Role forRole = roleManager.findById(comment.getForRole().getId());
            comment.setForRole(forRole);
            mailManager.sendEmail(comment);

        }


        commentsManager.save(comment);

        return "redirect:/orders/" + orderId + ".html?success=true";
    }

    //    @RequestMapping(value = "/changestatus/{orderId}/{answer}")
//    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) {
    @RequestMapping(value = "/changestatus/{orderId}/{answer}")
//    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) {
////        public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
////                BindingResult result, ModelMap model) throws IOException {
////    public String changeOrderStatus(@Valid MultiFileBucket multiFileBucket,
////                    BindingResult result,Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) throws IOException {
//
//        String login = principal.getName();
//        System.out.println("/////////////////changestatus/////////////////");
//        System.out.println(orderId);
//        System.out.println(answer);
//        User user = userManager.findByLogin(login);
//        List<Role> roles = roleManager.findByUserId(user.getUserId());
//        user.setRoles(roles);
//        Order theOrder = orderManager.findById(orderId);
//        model.addAttribute("theOrder", theOrder);
//
//        statusChanginLogic(theOrder,  roles, answer);
//
//        return "redirect:/orders/{orderId}";
//    }


    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    // functions for internal use
    public Comments statusChanginLogic(Order theOrder, Comments comment, List<Role> roles, String answer) {
        User userTo;
        SAPstatus newSAPStatus;
        StatusChange statusChange = new StatusChange();
        statusChange.setDateTime(new Date());
        System.out.println(theOrder.getOrderId());
//__1
        if ((theOrder.getSapStatus().getId() == 1) && roles.get(0).getId() == 2) {
            if (answer.equals("yes")) {
                newSAPStatus = sapStatusManager.findById(2);
                theOrder.setSAPstatus(newSAPStatus);
                theOrder.setRejected(false);
                userTo = userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 4);
                comment.setToUser(userTo);
                comment.setOrder(theOrder);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            }
        } else
//__2
            if (theOrder.getSapStatus().getId() == 2 && roles.get(0).getId() == 4) {
                if (answer.equals("yes")) {
                    if (theOrder.getPVIcheckScen()) {
                        newSAPStatus = sapStatusManager.findById(3);
                        userTo = userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2);
                    } else {
                        newSAPStatus = sapStatusManager.findById(4);
                        userTo = userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1);
                    }
                    if (theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen()) {
                        theOrder.setProcessId((byte) 1);
                    }


                    theOrder.setSAPstatus(newSAPStatus);
                    comment.setToUser(userTo);
                    comment.setOrder(theOrder);
                    orderManager.save(theOrder);
                    statusChange.setOrder(theOrder);
                    statusChange.setSAPstatus(newSAPStatus);
                    statusChangeManager.save(statusChange);
                }
            } else
//__3
                if (theOrder.getSapStatus().getId() == 3 && roles.get(0).getId() == 2 && theOrder.getPVIcheckScen()) {
                    if (answer.equals("yes")) {
                        newSAPStatus = sapStatusManager.findById(4);
                        theOrder.setSAPstatus(newSAPStatus);
                        theOrder.setRejected(false);
                        orderManager.save(theOrder);
                        statusChange.setOrder(theOrder);
                        statusChange.setSAPstatus(newSAPStatus);
                        statusChangeManager.save(statusChange);
                    } else if (answer.equals("no")) {
                        discardOrders(theOrder, statusChange);
                    }

                } else
//__4
                    if (theOrder.getSapStatus().getId() == 4 && roles.get(0).getId() == 1 && theOrder.getApprovalBy().equals("Deptor")) {
                        if (answer.equals("yes")) {
                            approveOrders(theOrder, statusChange);
//                newSAPStatus=sapStatusManager.findById(7);
//                theOrder.setSAPstatus(newSAPStatus);
//                orderManager.save(theOrder);
//                statusChange.setOrder(theOrder);
//                statusChange.setSAPstatus(newSAPStatus);
//                statusChangeManager.save(statusChange);
                        } else if (answer.equals("no")) {
                            discardOrders(theOrder, statusChange);
//                newSAPStatus=sapStatusManager.findById(6);
//                theOrder.setSAPstatus(newSAPStatus);
//                theOrder.setRejected(true);
//                orderManager.save(theOrder);
//                statusChange.setOrder(theOrder);
//                statusChange.setSAPstatus(newSAPStatus);
//                statusChangeManager.save(statusChange);
                        }

                    } else
//__5&7
                        if (theOrder.getSapStatus().getId() == 4 && roles.get(0).getId() == 5 && theOrder.getApprovalBy().equals("Customer")
                                && (!theOrder.getDebCheckScen() || (theOrder.getDebCheckScen() && theOrder.getProcessId() == 2))) {
                            if (answer.equals("yes")) {
                                approveOrders(theOrder, statusChange);
                            } else if (answer.equals("no")) {
                                theOrder.setProcessId((byte) 3);
                                orderManager.save(theOrder);
                            }

                        } else
//__6
                            if (theOrder.getSapStatus().getId() == 4 && roles.get(0).getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen() && theOrder.getProcessId() == 1) {
                                if (answer.equals("yes")) {
                                    theOrder.setProcessId((byte) 2);
                                    orderManager.save(theOrder);
                                } else if (answer.equals("no")) {
                                    discardOrders(theOrder, statusChange);
                                }

                            } else
//__8
                                if (theOrder.getSapStatus().getId() == 4 && roles.get(0).getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getProcessId() == 3) {
                                    if (answer.equals("yes")) {
                                        approveOrders(theOrder, statusChange);
                                    } else if (answer.equals("no")) {
                                        discardOrders(theOrder, statusChange);
                                    }

                                } else
//__9
                                    if (theOrder.getSapStatus().getId() == 6 && roles.get(0).getId() == 2) {
                                        if (answer.equals("yes")) {
//                TODO: Check what todo next
                                            newSAPStatus = sapStatusManager.findById(1);
                                            theOrder.setSAPstatus(newSAPStatus);
                                            orderManager.save(theOrder);
                                            statusChange.setOrder(theOrder);
                                            statusChange.setSAPstatus(newSAPStatus);
                                            statusChangeManager.save(statusChange);
                                        } else if (answer.equals("no")) {
                                            newSAPStatus = sapStatusManager.findById(2);
                                            theOrder.setSAPstatus(newSAPStatus);
                                            orderManager.save(theOrder);
                                            statusChange.setOrder(theOrder);
                                            statusChange.setSAPstatus(newSAPStatus);
                                            statusChangeManager.save(statusChange);
                                        }
                                    } else

//__10
                                        if (theOrder.getSapStatus().getId() == 5 && roles.get(0).getId() == 2 && theOrder.getProcessId() == 4) {
                                            if (answer.equals("yes")) {
                                                theOrder.setProcessId((byte) 5);
                                                orderManager.save(theOrder);
                                            }
                                        } else

//__11
                                            if (theOrder.getSapStatus().getId() == 5 && roles.get(0).getId() == 4 && theOrder.getProcessId() == 5) {
                                                if (answer.equals("yes")) {
                                                    newSAPStatus = sapStatusManager.findById(7);
                                                    theOrder.setSAPstatus(newSAPStatus);
                                                    orderManager.save(theOrder);
                                                    statusChange.setOrder(theOrder);
                                                    statusChange.setSAPstatus(newSAPStatus);
                                                    statusChangeManager.save(statusChange);
                                                }
                                            }
        return comment;


    }


    @RequestMapping(value = "/changestatus/{orderId}/{answer}", method = RequestMethod.POST)
//    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) {
//        public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
//                BindingResult result, ModelMap model) throws IOException {
    public String changeOrderStatusAndUpload(@Valid MultiFileBucket multiFileBucket,
                                             BindingResult result, Model model, @PathVariable int orderId, @PathVariable String answer, @ModelAttribute("user") User user, @ModelAttribute("comment") Comments comment) throws IOException {


        List<Role> roles = user.getRoles();
        Role role = roles.get(0);
        Order theOrder = orderManager.findById(orderId);
        comment = statusChanginLogic(theOrder, comment, roles, answer);
        comment.setFromUser(user);


        User userTo = comment.getToUser();
        List<Role> UserToRoles = roleManager.findByUserId(userTo.getUserId());
        userTo.setRoles(UserToRoles);
        try {
            Integer userToRoleId = UserToRoles.get(0).getId();
            Integer SSId = comment.getOrder().getSAPstatus().getId();

            StatusSAPStatusRole statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(userToRoleId, SSId);

            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
            statusSAPStatusRolesList.add(statusSAPStatusRole);

            theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
        } catch (NullPointerException e) {
            System.out.println(e.getStackTrace());
        }


        comment.setToUser(userTo);
        comment.setForRole(role);

        commentsManager.save(comment);
        Map<Comments, MultiFile> notification = new HashMap<>();
        notification.put(comment, null);
        mailManager.sendEmail(notification);


//        model.addAttribute("theOrder",theOrder);

//        if (result.hasErrors()) {
        System.out.println("validation errors in multi upload");
        System.out.println(result.hasErrors());
//            return "multiFileUploader";
//            return "redirect:/orders/{orderId}";
//        } else {
//        System.out.println("Fetching files");
//        List<String> fileNames = new ArrayList<String>();
        // Now do something with file...
//            for (FileBucket bucket : multiFileBucket.getFiles()) {
//                FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
//                fileNames.add(bucket.getFile().getOriginalFilename());
//                System.out.println("File saved "+bucket.getFile().getOriginalFilename());
//            }
//        for(FileBucket bucket : multiFileBucket.getFiles()){
//            System.out.println("buckets");
//            if(bucket.getFile()!=null){
//                System.out.println("!=null");
//                if (bucket.getFile().getSize() != 0) {
//                    System.out.println("!=0");
//                    FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
//                    fileNames.add(bucket.getFile().getOriginalFilename());
//                    System.out.println("File saved "+bucket.getFile().getOriginalFilename());
//                }
//            }
//        }


//        model.addAttribute("fileNames", fileNames);
//            return "singleFileUploader";
        return "redirect:/orders/{orderId}";
//        }

    }

    @RequestMapping(value = "/save-file/{orderId}", method = RequestMethod.POST)
    public @ResponseBody FileMeta doUpload(@RequestParam("files[]") MultipartFile multipartFile, @PathVariable int orderId) {
        System.out.println("/save-file");
        System.out.println(multipartFile.getOriginalFilename());
//        File file = new File("my-file.txt");
//        multipartFile.transferTo(file);
        FileMeta fileMeta = new FileMeta();
//        File directory= new File(UPLOAD_LOCATION +orderId+"/");
//        if (!directory.exists()) {
//            // ...
//        }
//        new File("C:/folder1")
        try {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));
            File file = new File(UPLOAD_LOCATION +orderId+"/"+ multipartFile.getOriginalFilename());
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
            fileMeta.setFileName(multipartFile.getOriginalFilename());
            fileMeta.setFileSize(multipartFile.getSize()/1024+" Kb");
            fileMeta.setFileType(multipartFile.getContentType());
//            fileMeta.setBytes(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMeta;
    }
    @RequestMapping(value = "/remove-file/{orderId}/{fileName:.*}")
    public @ResponseBody void doRemove(@PathVariable int orderId,@PathVariable String fileName) {
        System.out.println("/remove-file");
        System.out.println(fileName);
        try{
            File file = new File(UPLOAD_LOCATION +orderId+"/"+ fileName);
            if(file.delete()){System.out.println(file.getName() + " is deleted!");}
//            else{System.out.println("Delete operation is failed.");}
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // functions for internal use
    public void approveOrders(Order theOrder, StatusChange statusChange) {
        SAPstatus newSAPStatus = sapStatusManager.findById(5);
        theOrder.setSAPstatus(newSAPStatus);
        orderManager.save(theOrder);
        statusChange.setOrder(theOrder);
        statusChange.setSAPstatus(newSAPStatus);
        statusChangeManager.save(statusChange);
    }

    public void discardOrders(Order theOrder, StatusChange statusChange) {
        SAPstatus newSAPStatus = sapStatusManager.findById(6);
        theOrder.setSAPstatus(newSAPStatus);
        theOrder.setRejected(true);
        orderManager.save(theOrder);
        statusChange.setOrder(theOrder);
        statusChange.setSAPstatus(newSAPStatus);
        statusChangeManager.save(statusChange);
    }


//    @RequestMapping(value = "/orders/addcoment")
//    public String addComment () {
//
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println(ordId);
//
//
//
//        return "redirect:/orders/{orderId}";
//    }

private final static String ORDER_FOLDER_PATH = UPLOAD_LOCATION;//"d:\\saphana\\localFiles\\order_attachments";
	
	@RequestMapping(value = "/orders/{orderId}/order_attachments", method = RequestMethod.GET)
	public @ResponseBody ArrayList<FileInfo> getAllOrderAttachments(@PathVariable String orderId) {
		
		ArrayList<FileInfo> fileInfoList = new ArrayList<>();
		FileInfo fileInfo;
		File file;
		
		System.out.println(ORDER_FOLDER_PATH + "\\" + orderId);
		File folder = new File(ORDER_FOLDER_PATH + "\\" + orderId);
		if(folder.exists()) {
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				file = listOfFiles[i];
				if (file.isFile()) {
					System.out.println("File: " + file.getName());
					fileInfo = new FileInfo();
					fileInfo.setName(file.getName());
					fileInfo.setSize(file.length());
					fileInfo.setLastModified(new Date(file.lastModified()));
					fileInfoList.add(fileInfo);
				} else if (file.isDirectory()) {
					System.out.println("Directory " + file.getName());
				}
			}
		} else {
			System.out.println("for this order any attachment is not available!");
		}
		
		return fileInfoList;
	}

	private final static String EXTERNAL_FILE_PATH = "d:\\saphana\\localFiles\\order_attachments\\";

	@RequestMapping(value = "/orders/{orderId}/order_attachments/{file:.*}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable String orderId, @PathVariable("file") String fileName) throws IOException {
		System.out.println("!! DOWNLOAD !!");
		File file = null;
		System.out.println(EXTERNAL_FILE_PATH + orderId + "\\" + fileName + "]");
		file = new File(EXTERNAL_FILE_PATH + orderId + "\\" + fileName);

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may
		 * provide save as popup, based on your browser setting
		 */
		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/orders/{orderId}/order_attachments/remove/{fileName:.*}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> removeOrderAttachments(@PathVariable String orderId, @PathVariable String fileName) {
		
		String path = ORDER_FOLDER_PATH + "\\" + orderId + "\\" + fileName;
		System.out.println(path);
		File file = new File(path);
		if(file.exists()) {
			if(file.delete()) {
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.CONFLICT);
			}
		} else {
			System.out.println("file not found!");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

}
