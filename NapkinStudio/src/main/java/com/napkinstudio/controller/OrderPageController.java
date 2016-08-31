package com.napkinstudio.controller;


import com.napkinstudio.entity.*;
import com.napkinstudio.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
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


    @RequestMapping(value = "/orders/{orderId}")
    public String goToOrders(Model model,@PathVariable int orderId, Principal principal) {
        String login = principal.getName();
        System.out.println("/////////////////orderpage/////////////////");
        System.out.println(principal);
        System.out.println(login);
        User user = userManager.findByLogin(login);
        System.out.println("/////////////////user/////////////////");
        System.out.println(user.toString());
        Order theOrder =orderManager.findById(orderId);
        System.out.println("/////////////////theOrder/////////////////");
        System.out.println(theOrder.toString());

        List<Role> roles = roleManager.findByUserId(user.getUserId());
        System.out.println("/////////////////roles/////////////////");
        System.out.println(roles.toString());
//      List<Order> orders = orderManager.findByUserId(user.getUserId());
        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());
//        orders.get(0).getOrder().getSAPstatus().getStatusSAPStatuseRoles()
        Integer roleId =roles.get(0).getId();
        Integer SSId = userOrders.get(0).getOrder().getSAPstatus().getId();
        System.out.println(roleId + " " + SSId);


        StatusSAPStatusRole statusSAPStatusRole =  statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId,SSId);

//        System.out.println(statusSAPStatusRole.getStatus().getName());
        List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
        statusSAPStatusRolesList.add(statusSAPStatusRole);
        userOrders.get(0).getOrder().getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
//        user.setRoles(roles);
        model.addAttribute("user",user);
        model.addAttribute("userOrders",userOrders);
        model.addAttribute("theOrder",theOrder);
//        model.addAttribute("statusList", statusList);
        return "orderpage";
    }

    @RequestMapping(value = "/changestatus/{answer}")
    public String changeOrderStatus(Model model, @PathVariable String answer, Principal principal) {
        String login = principal.getName();
        System.out.println("/////////////////changestatus/////////////////");
        System.out.println(answer);
        System.out.println(login);
        User user = userManager.findByLogin(login);
        Order theOrder =orderManager.findById(1);

        List<Role> roles = roleManager.findByUserId(user.getUserId());
//      List<Order> orders = orderManager.findByUserId(user.getUserId());
        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());
//        orders.get(0).getOrder().getSAPstatus().getStatusSAPStatuseRoles()
        Integer roleId =roles.get(0).getId();
        Integer SSId = userOrders.get(0).getOrder().getSAPstatus().getId();
        System.out.println(roleId + " " + SSId);


        StatusSAPStatusRole statusSAPStatusRole =  statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId,SSId);

        System.out.println(statusSAPStatusRole.getStatus().getName());
        List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
        statusSAPStatusRolesList.add(statusSAPStatusRole);
        userOrders.get(0).getOrder().getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
        user.setRoles(roles);
        model.addAttribute("user",user);
        model.addAttribute("userOrders",userOrders);
        model.addAttribute("theOrder",theOrder);
//        model.addAttribute("statusList", statusList);
        return "orderpage";
    }



}
