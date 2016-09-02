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
        List<Object[]> barFields = progresBarFieldsManager.findBarByRolePVICheckReject(roleId,false,false);
        System.out.println(barFields);
        System.out.println(barFields.toString());

        for (Object[] object: barFields) {
            System.out.println(object.toString());

            System.out.println(object[0]+"; "+object[1]);
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
        Order theOrder =orderManager.findById(orderId);
        SAPstatus newSAPStatus;
        StatusChange statusChange =new StatusChange();
        statusChange.setDateTime(new Date());
        System.out.println(theOrder.getOrderId());
        if (answer.equals("yes")){
            int prevSapStatusId=theOrder.getSAPstatus().getId();
            if (prevSapStatusId<11){
                newSAPStatus=sapStatusManager.findById(prevSapStatusId+1);
                theOrder.setSAPstatus(newSAPStatus);
                orderManager.save(theOrder);
//                statusChange.setOrder(theOrder);
//                statusChange.setSAPstatus(newSAPStatus);
//                statusChangeManager.save(statusChange);
            }
        }else if (answer.equals("no")){
            newSAPStatus=sapStatusManager.findById(6);
            System.out.println(newSAPStatus.toString());
            theOrder.setSAPstatus(newSAPStatus);
            orderManager.save(theOrder);
//            statusChange.setOrder(theOrder);
//            statusChange.setSAPstatus(newSAPStatus);
//            statusChangeManager.save(statusChange);
        }

        model.addAttribute("theOrder",theOrder);
        return "orderpage";
    }



}
