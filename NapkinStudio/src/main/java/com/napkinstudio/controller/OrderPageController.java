package com.napkinstudio.controller;


import com.napkinstudio.entity.*;
import com.napkinstudio.manager.*;
import com.napkinstudio.util.MultiFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


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

        Role role = roleManager.findByUserId(user.getUserId());
        user.setRole(role);
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
        Integer roleId = user.getRole().getId();
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
        User userTo = userOrderManager.findUserforOrdedByRole(orderId, roleId).get(0).getUser();

        Role role = roleManager.findById(roleId);
        Order order = orderManager.findById(orderId);

        comment.setFromUser(user);
        comment.setToUser(userTo);
        comment.setForRole(role);
        comment.setOrder(order);


        if ((comment.getForRole().getId() == 2 && user.getRole().getId() != 2) || comment.getForRole().getId() == 4) {
            Role forRole = roleManager.findById(comment.getForRole().getId());
            comment.setForRole(forRole);
            mailManager.sendEmail(comment);

        }

        commentsManager.save(comment);

        return "redirect:/orders/" + orderId + ".html?success=true";
    }

    //
    @RequestMapping(value = "/changestatus/{orderId}/{answer}")
    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, @ModelAttribute("user") User user) {

//        Comments comment = new Comments();
//        comment.setFromUser(user);
//        comment.setCommText("");


        Role role = user.getRole();

        Order theOrder = orderManager.findById(orderId);

        model.addAttribute("theOrder", theOrder);

        statusChanginLogic(theOrder,  role, answer);

        if(theOrder.getItsUsers() != null)
        mailManager.sendEmail(theOrder);

        return "redirect:/orders/{orderId}";
    }


    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    // functions for internal use
    public void statusChanginLogic(Order theOrder,  Role role, String answer) {
//       List<UserOrder>  userTo = null;
//        UserOrder userToOrder;
//        List<UserOrder> UserOrdersList;
        SAPstatus newSAPStatus;
        StatusChange statusChange = new StatusChange();
        statusChange.setDateTime(new Date());
        System.out.println(theOrder.getOrderId());
//__1
        if ((theOrder.getSapStatus().getId() == 1) && role.getId() == 2) {
            if (answer.equals("yes")) {
                newSAPStatus = sapStatusManager.findById(2);
                theOrder.setSAPstatus(newSAPStatus);
                theOrder.setRejected(false);
                theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 4));

                prepareOrder(theOrder);
                orderManager.save(theOrder);

                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);

            }
        } else
//__2
            if (theOrder.getSapStatus().getId() == 2 && role.getId() == 4) {
                if (answer.equals("yes")) {
                    if (theOrder.getPVIcheckScen()) {
                        newSAPStatus = sapStatusManager.findById(3);
                        theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2));

//                        mailManager.sendEmail(theOrder);
                    } else {
                        newSAPStatus = sapStatusManager.findById(4);
                        if (theOrder.getApprovalBy().equals("Deptor"))
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  1));
                        else if (theOrder.getApprovalBy().equals("Customer") && !theOrder.getDebCheckScen()) {
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 5));
                        } if (theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen()) {
                            theOrder.setProcessId((byte) 1);
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  1));
                        }
//
//                        mailManager.sendEmail(theOrder);
                    }


                    theOrder.setSAPstatus(newSAPStatus);

                     prepareOrder(theOrder);
                    orderManager.save(theOrder);
                    statusChange.setOrder(theOrder);
                    statusChange.setSAPstatus(newSAPStatus);
                    statusChangeManager.save(statusChange);
                }
            } else
//__3
                if (theOrder.getSapStatus().getId() == 3 && role.getId() == 2 && theOrder.getPVIcheckScen()) {


                    if (answer.equals("yes")) {
                        newSAPStatus = sapStatusManager.findById(4);


                        theOrder.setSAPstatus(newSAPStatus);
                        theOrder.setRejected(false);


                        if (theOrder.getApprovalBy().equals("Deptor"))
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));
                        else if (theOrder.getApprovalBy().equals("Customer") && !theOrder.getDebCheckScen()) {
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  5));
                        } else if (theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen())
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));

                       prepareOrder(  theOrder);
                        orderManager.save(theOrder);
                        statusChange.setOrder(theOrder);
                        statusChange.setSAPstatus(newSAPStatus);
                        statusChangeManager.save(statusChange);
                    } else if (answer.equals("no")) {
                        theOrder.setItsUsers(null);
                        discardOrders(theOrder, statusChange);
                    }

                } else
//__4
                    if (theOrder.getSapStatus().getId() == 4 && role.getId() == 1 && theOrder.getApprovalBy().equals("Deptor")) {
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
                        theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  2));
                        prepareOrder(theOrder);

                    } else
//5 and 7
                        if (theOrder.getSapStatus().getId() == 4 && role.getId() == 5 && theOrder.getApprovalBy().equals("Customer")
                                && (!theOrder.getDebCheckScen() || (theOrder.getDebCheckScen() && theOrder.getProcessId() == 2))) {
                            if (answer.equals("yes")) {
                                approveOrders(theOrder, statusChange);
                                theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2));

                            } else if (answer.equals("no")) {
                                theOrder.setProcessId((byte) 3);
                                theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));

                                orderManager.save(theOrder);
                            }
                            prepareOrder(  theOrder);
                        } else
//__6
                            if (theOrder.getSapStatus().getId() == 4 && role.getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen() && theOrder.getProcessId() == 1) {
                                if (answer.equals("yes")) {
                                    theOrder.setProcessId((byte) 2);
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  5));

                                    orderManager.save(theOrder);
                                } else if (answer.equals("no")) {
                                    discardOrders(theOrder, statusChange);
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  2));
                                }
                                prepareOrder(  theOrder);

                            } else
//__8
                                if (theOrder.getSapStatus().getId() == 4 && role.getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getProcessId() == 3) {
                                    if (answer.equals("yes")) {
                                        approveOrders(theOrder, statusChange);

                                    } else if (answer.equals("no")) {
                                        discardOrders(theOrder, statusChange);
                                    }
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  2));
                                    prepareOrder(  theOrder);

                                } else
//__9
                                    if (theOrder.getSapStatus().getId() == 6 && role.getId() == 2) {
                                        if (answer.equals("yes")) {
//                TODO: Check what todo next
                                            newSAPStatus = sapStatusManager.findById(1);
                                            theOrder.setSAPstatus(newSAPStatus);
                                            theOrder.setItsUsers(null);
                                            orderManager.save(theOrder);
                                            statusChange.setOrder(theOrder);
                                            statusChange.setSAPstatus(newSAPStatus);
                                            statusChangeManager.save(statusChange);
                                        } else if (answer.equals("no")) {
                                            newSAPStatus = sapStatusManager.findById(2);
                                            theOrder.setSAPstatus(newSAPStatus);
                                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  4));
                                            prepareOrder(theOrder);
                                            orderManager.save(theOrder);
                                            statusChange.setOrder(theOrder);
                                            statusChange.setSAPstatus(newSAPStatus);
                                            statusChangeManager.save(statusChange);
                                        }
                                    } else

//__10
                                        if (theOrder.getSapStatus().getId() == 5 && role.getId() == 2 && theOrder.getProcessId() == 4) {
                                            if (answer.equals("yes")) {
                                                theOrder.setProcessId((byte) 5);
                                                theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(),  4));
                                                prepareOrder(theOrder);
                                                orderManager.save(theOrder);
                                            }
                                        } else

//__11
                                            if (theOrder.getSapStatus().getId() == 5 && role.getId() == 4 && theOrder.getProcessId() == 5) {
                                                if (answer.equals("yes")) {
                                                    newSAPStatus = sapStatusManager.findById(7);
                                                    theOrder.setSAPstatus(newSAPStatus);
                                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 4));
                                                    prepareOrder(theOrder);
                                                    orderManager.save(theOrder);
                                                    statusChange.setOrder(theOrder);
                                                    statusChange.setSAPstatus(newSAPStatus);
                                                    statusChangeManager.save(statusChange);
                                                }
                                            }



    }

    private void prepareOrder(Order theOrder) {

            User userTo = theOrder.getItsUsers().get(0).getUser();
            Role userToRoles = roleManager.findByUserId(userTo.getUserId());
//            userTo.setRole(userToRoles);

            try {
                Integer userToRoleId = userToRoles.getId();
                Integer SSId = theOrder.getSAPstatus().getId();

                StatusSAPStatusRole statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(userToRoleId, SSId);

                List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
                statusSAPStatusRolesList.add(statusSAPStatusRole);

                theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
            } catch (NullPointerException e) {
                System.out.println(e.getStackTrace());
            }

//       commentsManager.save(comment);

    }

    @RequestMapping(value = "/changestatus/{orderId}/{answer}", method = RequestMethod.POST)
    public String changeOrderStatusAndUpload(@Valid MultiFileBucket multiFileBucket,
                                             BindingResult result, Model model, @PathVariable int orderId, @PathVariable String answer, @ModelAttribute("user") User user, @ModelAttribute("comment") Comments comment) throws IOException {
        Order theOrder = orderManager.findById(orderId);
        Role role = user.getRole();

        statusChanginLogic(theOrder,  role, answer);
        if(comment.getCommText() != "") {
            comment.setFromUser(user);
            comment.setToUser(theOrder.getItsUsers().get(0).getUser());
            comment.setForRole(theOrder.getSAPstatus().getStatusSAPStatuseRoles().get(0).getRole());
            comment.setOrder(theOrder);
            commentsManager.save(comment);
        }
                  mailManager.sendEmail(theOrder);

//        if (result.hasErrors()) {
        System.out.println("validation errors in multi upload");
        System.out.println(result.hasErrors());
        return "redirect:/orders/{orderId}";
//        }

    }

    @RequestMapping(value = "/save-file/{orderId}", method = RequestMethod.POST)
    public
    @ResponseBody
    FileMeta doUpload(@RequestParam("files[]") MultipartFile multipartFile, @PathVariable int orderId) {
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
            File file = new File(UPLOAD_LOCATION + orderId + "/" + multipartFile.getOriginalFilename());
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
            fileMeta.setFileName(multipartFile.getOriginalFilename());
            fileMeta.setFileSize(multipartFile.getSize() / 1024 + " Kb");
            fileMeta.setFileType(multipartFile.getContentType());
            fileMeta.setBytes(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMeta;
    }

    @RequestMapping(value = "/remove-file/{orderId}/{fileName:.*}")
    public
    @ResponseBody
    void doRemove(@PathVariable int orderId, @PathVariable String fileName) {
        System.out.println("/remove-file");
        System.out.println(fileName);
        try {
            File file = new File(UPLOAD_LOCATION + orderId + "/" + fileName);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            }
//            else{System.out.println("Delete operation is failed.");}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // functions for internal use
    public Order approveOrders(Order theOrder, StatusChange statusChange) {
        SAPstatus newSAPStatus = sapStatusManager.findById(5);
        theOrder.setSAPstatus(newSAPStatus);
        theOrder.setProcessId((byte) 4);
        orderManager.save(theOrder);
        statusChange.setOrder(theOrder);
        statusChange.setSAPstatus(newSAPStatus);
        statusChangeManager.save(statusChange);
        return theOrder;
    }

    public Order discardOrders(Order theOrder, StatusChange statusChange) {
        SAPstatus newSAPStatus = sapStatusManager.findById(6);
        theOrder.setSAPstatus(newSAPStatus);
        theOrder.setRejected(true);
        orderManager.save(theOrder);
        statusChange.setOrder(theOrder);
        statusChange.setSAPstatus(newSAPStatus);
        statusChangeManager.save(statusChange);
        return theOrder;
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


}
