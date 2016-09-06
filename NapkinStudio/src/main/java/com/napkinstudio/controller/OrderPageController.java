package com.napkinstudio.controller;


import com.napkinstudio.entity.*;
import com.napkinstudio.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



@Controller
public class OrderPageController {

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


    @RequestMapping(value = "/orders/{orderId}")
    public String goToOrders(Model model,@PathVariable int orderId, Principal principal) {
        String login = principal.getName();
        User user = userManager.findByLogin(login);
        Order theOrder =orderManager.findById(orderId);

        List<Role> roles = roleManager.findByUserId(user.getUserId());
        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());
        Integer roleId =roles.get(0).getId();
        Integer SSId = theOrder.getSAPstatus().getId();
        System.out.println("role="+roleId + "; SSId=" + SSId);
        StatusSAPStatusRole statusSAPStatusRole;
        try{
            statusSAPStatusRole =  statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId,SSId);
            System.out.println(statusSAPStatusRole.getStatus().getName());
            List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
            statusSAPStatusRolesList.add(statusSAPStatusRole);
            theOrder.getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
        }catch (NullPointerException e) {
            e.printStackTrace(System.out);
        }
        user.setRoles(roles);

//        List<ProgresBarFields> barFields = progresBarFieldsManager.findAll();
        List<Object[]> barFields = progresBarFieldsManager.findBarByRolePVICheckReject(orderId,roleId,theOrder.getPVIcheckScen(),theOrder.getRejected());
//        System.out.println(barFields);
//        System.out.println(barFields.toString());
//        System.out.println(barFields.size());
            Date initDate=new Date();
            Date dateToComp=new Date();


        for (Iterator<Object[]> iterator = barFields.iterator(); iterator.hasNext();) {
            Object[] object = iterator.next();
            System.out.println(object[0]+"; "+object[1]);
////            get real initialization date
            if ((object[0]!=null)&&((object[0].equals("Proof requested"))||(object[0].equals("Proof request set up")))){
                if (object[1] instanceof java.util.Date) {
                    initDate = (java.util.Date) object[1];
                }
            }
//////            remove old data from orevious rounds
            if (object[1] instanceof java.util.Date) {
                dateToComp = (java.util.Date) object[1];
            }
            System.out.println(dateToComp+" compare to "+initDate);
            if(dateToComp.before(initDate)){
                object[1]=null;
            }
////            correcting progresbar according to onhold
            if (theOrder.getSAPstatus().getId()==9){
//                System.out.println(object[1]);
                if (object[1]==null){
                    iterator.remove();
                }
            }else {
                if (object[0].equals("On hold")){
//                    System.out.println("rem");
                    iterator.remove();
                }
            }
        }



        model.addAttribute("user",user);
        model.addAttribute("userOrders",userOrders);
        model.addAttribute("theOrder",theOrder);
        model.addAttribute("barFields",barFields);
        return "orderpage";
    }


//    @RequestMapping(value = "/changestatus/{orderId}/{answer}")
//    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) {
    @RequestMapping(value = "/changestatus/{orderId}/{answer}")
    public String changeOrderStatus(Model model, @PathVariable int orderId, @PathVariable String answer, Principal principal) {

        String login = principal.getName();
        System.out.println("/////////////////changestatus/////////////////");
        System.out.println(orderId);
        System.out.println(answer);
        User user = userManager.findByLogin(login);
        List<Role> roles = roleManager.findByUserId(user.getUserId());
//        user.setRoles(roles);
        Order theOrder =orderManager.findById(orderId);
        SAPstatus newSAPStatus;
        StatusChange statusChange =new StatusChange();
        statusChange.setDateTime(new Date());
        System.out.println(theOrder.getOrderId());
//__1
        if (theOrder.getSapStatus().getId()==1&&roles.get(0).getId()==2){
            if (answer.equals("yes")){
                    newSAPStatus=sapStatusManager.findById(2);
                    theOrder.setSAPstatus(newSAPStatus);
                    theOrder.setRejected(false);
                    orderManager.save(theOrder);
                    statusChange.setOrder(theOrder);
                    statusChange.setSAPstatus(newSAPStatus);
                    statusChangeManager.save(statusChange);
            }
        }
//__2
        if (theOrder.getSapStatus().getId()==2&&user.getRoles().get(0).getId()==4){
            if (answer.equals("yes")){
                if (theOrder.getPVIcheckScen()){
                    newSAPStatus=sapStatusManager.findById(3);
                } else{
                    newSAPStatus=sapStatusManager.findById(4);
                }
                theOrder.setSAPstatus(newSAPStatus);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            }
        }
//__3
        if (theOrder.getSapStatus().getId()==3&&user.getRoles().get(0).getId()==2&&theOrder.getPVIcheckScen()){
            if (answer.equals("yes")){
                newSAPStatus=sapStatusManager.findById(4);
                theOrder.setSAPstatus(newSAPStatus);
                theOrder.setRejected(false);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            } else if (answer.equals("no")){
                newSAPStatus=sapStatusManager.findById(6);
                theOrder.setSAPstatus(newSAPStatus);
                theOrder.setRejected(true);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            }

        }
//__4
        if (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==1&&theOrder.getApprovalBy()=="Deptor"){
            if (answer.equals("yes")){
                newSAPStatus=sapStatusManager.findById(7);
                theOrder.setSAPstatus(newSAPStatus);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            } else if (answer.equals("no")){
                newSAPStatus=sapStatusManager.findById(6);
                theOrder.setSAPstatus(newSAPStatus);
                theOrder.setRejected(true);
                orderManager.save(theOrder);
                statusChange.setOrder(theOrder);
                statusChange.setSAPstatus(newSAPStatus);
                statusChangeManager.save(statusChange);
            }

        }

//                (theOrder.getSapStatus().getId()==1&&user.getRoles().get(0).getId()==2)||
//                (theOrder.getSapStatus().getId()==2&&user.getRoles().get(0).getId()==4)||
//                (theOrder.getSapStatus().getId()==3&&user.getRoles().get(0).getId()==2&&theOrder.getPVIcheckScen())||
//                (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==1&&theOrder.getApprovalBy()=='Deptor')||
//                (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==5&&theOrder.getApprovalBy()=='Customer'&&!theOrder.getDebCheckScen())||
//                (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==1&&theOrder.getApprovalBy()=='Customer'&&theOrder.getDebCheckScen())||
//                (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==5&&theOrder.getApprovalBy()=='Customer'&&theOrder.getDebCheckScen()&&!theOrder.getToDeptor())||
//                (theOrder.getSapStatus().getId()==4&&user.getRoles().get(0).getId()==1&&theOrder.getApprovalBy()=='Customer'&&theOrder.getToDeptor())||
//                (theOrder.getSapStatus().getId()==6&&user.getRoles().get(0).getId()==2)||
//                (theOrder.getSapStatus().getId()==5&&user.getRoles().get(0).getId()==2&&!theOrder.getToDeptor())||
//                (theOrder.getSapStatus().getId()==5&&user.getRoles().get(0).getId()==4&&theOrder.getToDeptor())


//        if (answer.equals("yes")){
//            int prevSapStatusId=theOrder.getSAPstatus().getId();
//            if (prevSapStatusId<11){
//                newSAPStatus=sapStatusManager.findById(prevSapStatusId+1);
//                theOrder.setSAPstatus(newSAPStatus);
//                theOrder.setRejected(false);
//                orderManager.save(theOrder);
//                statusChange.setOrder(theOrder);
//                statusChange.setSAPstatus(newSAPStatus);
//                statusChangeManager.save(statusChange);
//            }
//        }else if (answer.equals("no")){
//            newSAPStatus=sapStatusManager.findById(6);
//            System.out.println(newSAPStatus.toString());
//            theOrder.setSAPstatus(newSAPStatus);
//            theOrder.setRejected(true);
//            orderManager.save(theOrder);
//            statusChange.setOrder(theOrder);
//            statusChange.setSAPstatus(newSAPStatus);
//            statusChangeManager.save(statusChange);
//        }

        model.addAttribute("theOrder",theOrder);
        return "redirect:/orders/{orderId}";
    }



}
