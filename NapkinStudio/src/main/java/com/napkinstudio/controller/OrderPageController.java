package com.napkinstudio.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.MultiFileBucket;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.SAPstatus;
import com.napkinstudio.entity.StatusChange;
import com.napkinstudio.entity.StatusSAPStatusRole;
import com.napkinstudio.entity.User;
import com.napkinstudio.entity.UserOrder;
import com.napkinstudio.manager.CommentsManager;
import com.napkinstudio.manager.MailManager;
import com.napkinstudio.manager.OrderManager;
import com.napkinstudio.manager.OrderPageManager;
import com.napkinstudio.manager.ProgresBarFieldsManager;
import com.napkinstudio.manager.RoleManager;
import com.napkinstudio.manager.SAPstatusManager;
import com.napkinstudio.manager.StatusChangeManager;
import com.napkinstudio.manager.StatusManager;
import com.napkinstudio.manager.StatusSAPStatusRoleManager;
import com.napkinstudio.manager.UserManager;
import com.napkinstudio.manager.UserOrderManager;
import com.napkinstudio.util.MultiFileValidator;


@Controller
public class OrderPageController {

    @Autowired
    OrderPageManager orderPageManager;

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

    private List<String> idList;

    @ModelAttribute("user")
    public User user(Principal principal) {
        String login = principal.getName();
        User user = userManager.findByLogin(login);

        Role role = roleManager.findByUserId(user.getUserId());
        user.setRole(role);
        return user;
    }


    @ResponseBody
    @RequestMapping(value = "/order-ids", method = RequestMethod.POST)
    public String setOrderIdsList(@RequestParam(value = "IDList[]") List<String> ids) {

        this.idList = ids;

        return new Boolean(true).toString();
    }


//     }

//
//    @RequestMapping(value = "/order-ids", method = RequestMethod.POST)
//    @ResponseBody// <== this annotation will bind Arr class and convert to json response.
//    public Arr addAnotherAppointment(
//              @RequestBody Arr arr,
//
//            BindingResult errors) {
//        System.out.println("aaaaaaaaaaa");
//        System.out.println(arr.getTestArray());
//
//        return arr;
//    }


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
    public String goToOrders(Model model, @PathVariable("orderId") Integer orderId, @ModelAttribute("user") User user) {

        Integer roleId = user.getRole().getId();
        Integer userId = user.getUserId();

        if (roleId == 5) {
            roleId = 1;
        }

        UserOrder userOrder = userOrderManager.findOrdersByUserAndOrderId(userId, orderId);

        if (userOrder == null) {
            return "orders";
        }

        Order theOrder = userOrder.getOrder();
        List<UserOrder> userOrderList = new ArrayList<>();
        userOrderList.add(userOrder);
        theOrder.setItsUsers(userOrderList);


        Integer SSId = theOrder.getSAPstatus().getId();


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
                if (object[1] instanceof Date) {
                    initDate = (Date) object[1];
                }
            }
//////            remove old data from orevious rounds
            if (object[1] instanceof Date) {
                dateToComp = (Date) object[1];
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
//
        String prevId = "", nextId = "";
        if (idList != null && idList.size() > 0) {
            int prev = idList.indexOf(orderId.toString()) - 1;
            int next = idList.indexOf(orderId.toString()) + 1;
            prevId = prev >= 0 ? idList.get(prev) : "";
            nextId = next <= (idList.size() - 1) ? idList.get(next) : "";
        }


        model.addAttribute("nextId", nextId);
        model.addAttribute("prevId", prevId);

        try {

            List<Comments> comments = null;
            if (user.getRole().getId() == 5) {
                List<Integer> roleIdList = new ArrayList<>();
                roleIdList.add(5);
                roleIdList.add(1);
                Map<Integer, List<Comments>> commentsMap = commentsManager.findCommentsbyOrderAndRoleIDs(orderId, roleIdList, userOrder);
                List<Comments> customerComments = commentsMap.get(5);
                List<Comments> deptorComments = commentsMap.get(1);
                model.addAttribute("CustomerComments", customerComments);
                model.addAttribute("DeptorComments", deptorComments);
            } else if (user.getRole().getId() == 1) {
//                List<Comments> customerComments = commentsManager.findCommentsByOrderAndRoleId(orderId, 5);
//                List<Comments> deptorComments = commentsManager.findCommentsByOrderAndRoleId(orderId, 1);
                List<Integer> roleIdList = new ArrayList<>();
                roleIdList.add(5);
                roleIdList.add(1);
                roleIdList.add(2);

                Map<Integer, List<Comments>> commentsMap = commentsManager.findCommentsbyOrderAndRoleIDs(orderId, roleIdList, userOrder);
                List<Comments> customerComments = commentsMap.get(5);
                List<Comments> deptorComments = commentsMap.get(1);
                List<Comments> PVIComments = commentsMap.get(2);

                model.addAttribute("CustomerComments", customerComments);
                model.addAttribute("DeptorComments", deptorComments);
                model.addAttribute("PVIComments",PVIComments);

            } else if (user.getRole().getId() == 2 || user.getRole().getId() == 4) {

                Map<Integer, List<Comments>> commentsMap = commentsManager.findCommentsbyOrderId(orderId, userOrder);

                model.addAttribute("PVIComments", commentsMap.get(2));
                model.addAttribute("DeptorComments", commentsMap.get(1));
                model.addAttribute("DTPComments", commentsMap.get(4));
                model.addAttribute("CustomerComments", commentsMap.get(5));
                model.addAttribute("StampsManufacComments", commentsMap.get(6));
                model.addAttribute("ProductionComments", commentsMap.get(7));

            }

//            model.addAttribute("count",commentsMap);


        } catch (Exception e) {
            e.printStackTrace(System.out);
        }


        //        model.addAttribute("user", user);
//        model.addAttribute("userOrders", userOrders);
        model.addAttribute("theOrder", theOrder);
        model.addAttribute("barFields", barFields);
        model.addAttribute("orderPviCheck", theOrder.getPVIcheckScen());

        // setting lastlook

        userOrder.setLastLook(new Date());
        userOrderManager.save(userOrder);

        return "orderpage";
    }

    //delete NapkinSudio by commit
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


        if (comment.getForRole().getId() !=user.getRole().getId()) {

            mailManager.sendEmail(comment);
        }
        Role forRole = roleManager.findById(comment.getForRole().getId());
        comment.setForRole(forRole);

        commentsManager.save(comment);

        return "redirect:/orders/" + orderId + ".html?success=true";
    }

    @ResponseBody
    @RequestMapping(value = "/edit-comment", method = RequestMethod.POST)
    public String editComment(@RequestBody Comments newComment) {
        Integer commentId = newComment.getId();
        Comments comment = commentsManager.findCommentById(commentId);
        comment.setCommText(newComment.getCommText());
        commentsManager.save(comment);

        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/delete-comment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam("commentId") int id) {
//        Integer commentId = Integer.parseInt(id);
        System.out.println(id);
        commentsManager.deleteById(id);
        return "true";
    }


    ////delete NapkinSudio by commit
        @RequestMapping(value = "/changestatus/{orderId}/{answer}")
    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, @ModelAttribute("user") User user) {

//        Comments comment = new Comments();
//        comment.setFromUser(user);
//        comment.setCommText("");


        Role role = user.getRole();

        Order theOrder = orderManager.findById(orderId);

        model.addAttribute("theOrder", theOrder);

        statusChanginLogic(theOrder, role, answer);

        if (theOrder.getItsUsers() != null)
            mailManager.sendEmail(theOrder);

        return "redirect:/orders/{orderId}";
    }


    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    // functions for internal use
    public void statusChanginLogic(Order theOrder, Role role, String answer) {
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
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));
                        else if (theOrder.getApprovalBy().equals("Customer") && !theOrder.getDebCheckScen()) {
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 5));
                        }
                        if (theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen()) {
                            theOrder.setProcessId((byte) 1);
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));
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
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 5));
                        } else if (theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen()) {
                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 1));
                            theOrder.setProcessId((byte) 1);
                        }

                        prepareOrder(theOrder);
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
                        theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2));
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
                            prepareOrder(theOrder);
                        } else
//__6
                            if (theOrder.getSapStatus().getId() == 4 && role.getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getDebCheckScen() && theOrder.getProcessId() == 1) {
                                if (answer.equals("yes")) {
                                    theOrder.setProcessId((byte) 2);

                                    orderManager.save(theOrder);
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 5));
                                } else if (answer.equals("no")) {
                                    discardOrders(theOrder, statusChange);
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2));
                                }
                                prepareOrder(theOrder);

                            } else
//__8
                                if (theOrder.getSapStatus().getId() == 4 && role.getId() == 1 && theOrder.getApprovalBy().equals("Customer") && theOrder.getProcessId() == 3) {
                                    if (answer.equals("yes")) {
                                        approveOrders(theOrder, statusChange);

                                    } else if (answer.equals("no")) {
                                        discardOrders(theOrder, statusChange);
                                    }
                                    theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 2));
                                    prepareOrder(theOrder);

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
                                            theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 4));
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
                                                theOrder.setItsUsers(userOrderManager.findUserforOrdedByRole(theOrder.getOrderId(), 4));
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
                                            } else

//__12
                                                if (theOrder.getSapStatus().getId() == 7 && role.getId() == 2) {
                                                    if (answer.equals("yes")) {
                                                        newSAPStatus = sapStatusManager.findById(8);
                                                        theOrder.setSAPstatus(newSAPStatus);
                                                        orderManager.save(theOrder);
                                                        statusChange.setOrder(theOrder);
                                                        statusChange.setSAPstatus(newSAPStatus);
                                                        statusChangeManager.save(statusChange);
                                                    }
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

    //delete NapkinSudio by commit
    @RequestMapping(value = "/changestatus/{orderId}/{answer}", method = RequestMethod.POST)
    public String changeOrderStatusAndUpload(@Valid MultiFileBucket multiFileBucket,
                                             BindingResult result, Model model, @PathVariable int orderId, @PathVariable String answer, @ModelAttribute("user") User user, @ModelAttribute("comment") Comments comment) throws IOException {
        Order theOrder = orderManager.findById(orderId);
        Role role = user.getRole();

        statusChanginLogic(theOrder, role, answer);
        if (!comment.getCommText().equals("") ) {
            comment.setFromUser(user);
            comment.setToUser(theOrder.getItsUsers().get(0).getUser());
            comment.setForRole(theOrder.getSAPstatus().getStatusSAPStatuseRoles().get(0).getRole());
            comment.setOrder(theOrder);
            commentsManager.save(comment);
        }

        mailManager.sendEmail(theOrder);

//        User userTo = comment.getToUser();
//        List<Role> UserToRoles = roleManager.findByUserId(userTo.getUserId());
//        userTo.setRoles(UserToRoles);
////        try {
////            Integer userToRoleId = UserToRoles.get(0).getId();
////            Integer SSId = comment.getOrder().getSAPstatus().getId();
////
////            StatusSAPStatusRole statusSAPStatusRole = statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(userToRoleId, SSId);
////
////            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
////            statusSAPStatusRolesList.add(statusSAPStatusRole);
////
////            theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
////        } catch (NullPointerException e) {
////            System.out.println(e.getStackTrace());
////        }
//
//
//        comment.setToUser(userTo);
//        comment.setForRole(role);
//
//        commentsManager.save(comment);
//        Map<Comments, MultiFile> notification = new HashMap<>();
//        notification.put(comment, null);
//        mailManager.sendEmail(notification);
//
//
////        model.addAttribute("theOrder",theOrder);


//        if (result.hasErrors()) {
        System.out.println("validation errors in multi upload");
        System.out.println(result.hasErrors());
        return "redirect:/orders/{orderId}";
//        }

    }


    
    @RequestMapping(value = "/orders/{orderId}/save-printproof-to-tmp/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> savePrintProofToTmpDirectory(@RequestParam("files[]") MultipartFile multipartFile, @PathVariable int orderId) {
        return orderPageManager.savePrintProofToTmpDirectory(multipartFile, orderId);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/printproof/exist", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> hasPriptproof(HttpServletResponse response, @PathVariable int orderId) {
    	return orderPageManager.hasPriptproof(response, orderId);
    }
    
    

    @RequestMapping(value = "/orders/{orderId}/printproof", method = RequestMethod.GET)
    @ResponseBody
    public void getPrintproof(HttpServletResponse response, @PathVariable int orderId) {
    	orderPageManager.getPrintproof(response, orderId);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/clear-tmp/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> clearTmpDirectory(@PathVariable int orderId) {
    	return orderPageManager.clearTmpDirectory(orderId);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/save-file-to-tmp/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveFileToTmpDirectory(@RequestParam("files[]") MultipartFile multipartFile, @PathVariable int orderId) {
    	return orderPageManager.saveFileToTmpDirectory(multipartFile, orderId);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/order_attachments/remove_temp/{fileName:.*}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeAtachment(@PathVariable int orderId, @PathVariable String fileName) {
        return orderPageManager.removeAtachment(orderId, fileName);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/order_attachments/remove_all_temps", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<String>> removeAllAtachments(@PathVariable int orderId, @RequestParam("files[]") List<String> files) {
        return orderPageManager.removeAllAtachments(orderId, files);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/order_attachments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllOrderAttachments(@PathVariable String orderId) {
    	return orderPageManager.getAllOrderAttachments(orderId);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/order_attachments/{file:.*}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable String orderId, @PathVariable("file") String fileName) {
    	try {
			orderPageManager.downloadFile(response, orderId, fileName);
		} catch (IOException e) {
			System.out.println("Sth somewhere went wrong!");
			e.printStackTrace();
		}
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/order_attachments/remove/{fileName:.*}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeOrderAttachment(@PathVariable String orderId, @PathVariable String fileName) {
    	return orderPageManager.removeOrderAttachment(orderId, fileName);
    }
    
    
    
    @RequestMapping(value = "/orders/{orderId}/approve", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Map<String, Object>> approve(
            @PathVariable int orderId,
            @RequestParam(value = "files[]", required = false) List<String> files,
            @RequestParam(value = "printproof", required = false) String printproof) {
    	return orderPageManager.approve(orderId, files, printproof);
	}


}
