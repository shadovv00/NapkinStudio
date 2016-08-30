package com.napkinstudio.controller;


import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.StatusSAPStatusRole;
import com.napkinstudio.entity.User;
import com.napkinstudio.entity.UserOrder;
import com.napkinstudio.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User1 on 23.08.2016.
 */

@Controller
public class OrderController {

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


    @RequestMapping(value = "/orders")
    public String goToOrders(Model model, Principal principal) {
        String login = principal.getName();
        User user = userManager.findByLogin(login);

        List<Role> roles = roleManager.findByUserId(user.getUserId());
        Integer roleId =roles.get(0).getId();

        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());

        for (Iterator<UserOrder> iterator = userOrders.iterator(); iterator.hasNext();) {
            UserOrder userOrder = iterator.next();
            Integer SSId = userOrder.getOrder().getSAPstatus().getId();
            System.out.println(roleId + " " + SSId);
            StatusSAPStatusRole statusSAPStatusRole;
            try{
                statusSAPStatusRole =  statusSAPStatusRoleManager.findStatusByRoleIdAndSAPStatusId(roleId,SSId);
                System.out.println(statusSAPStatusRole.getStatus().getName());
                List<StatusSAPStatusRole> statusSAPStatusRolesList = new ArrayList<>();
                statusSAPStatusRolesList.add(statusSAPStatusRole);
                userOrder.getOrder().getSAPstatus().setStatusSAPStatuseRoles(statusSAPStatusRolesList);
            }catch (NullPointerException e) {
                e.printStackTrace(System.out);
                iterator.remove();

            }


        }


//        orders.get(0).getOrder().getSAPstatus().getStatusSAPStatuseRoles()
//
//    List<Status> statusList = statusManager.findByRoleAndSAPStatusId(roles.get(0).getId(), orders.get(0).getSAPstatus().getId());
//       System.out.println(orders.get(0).getSAPstatus().getName());
//
//        for(Status status: statusList
//             ) {
//            System.out.println(status.getName());
//
//        }
////        System.out.println(orders.get(0).getSAPstatus().getStatuses().get(0).getName());
////        user.setOrders(orders);
         user.setRoles(roles);

        model.addAttribute("user",user);
        model.addAttribute("userOrders",userOrders);
//        model.addAttribute("statusList", statusList);
        return "orders";
    }

    @RequestMapping("/orders/${orderId}")
    public String orderReview(Model model, @PathVariable int orderId, Principal principal) {
        model.addAttribute("orderId",orderId);
        return "orderReview";
    }


}
