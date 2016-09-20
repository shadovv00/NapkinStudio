package com.napkinstudio.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.StatusSAPStatusRole;
import com.napkinstudio.entity.User;
import com.napkinstudio.entity.UserOrder;
import com.napkinstudio.manager.MailManager;
import com.napkinstudio.manager.OrderManager;
import com.napkinstudio.manager.RoleManager;
import com.napkinstudio.manager.StatusManager;
import com.napkinstudio.manager.StatusSAPStatusRoleManager;
import com.napkinstudio.manager.UserManager;
import com.napkinstudio.manager.UserOrderManager;

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

    @Autowired
    private MailManager mailManager;


    @ModelAttribute("user")
    public User user(Principal principal){
        String login = principal.getName();
        User user = userManager.findByLogin(login);

        List<Role> roles = roleManager.findByUserId(user.getUserId());
        user.setRoles(roles);
        return user;
    }

    @ModelAttribute("userOrders")
    List<UserOrder> UserOrders(@ModelAttribute("user") User user) {

        Integer roleId = user.getRoles().get(0).getId();
        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());

        for (Iterator<UserOrder> iterator = userOrders.iterator(); iterator.hasNext();) {
            UserOrder userOrder = iterator.next();
            Integer SSId = userOrder.getOrder().getSAPstatus().getId();
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
        return userOrders;
    }


    @RequestMapping("orders/sendEmail/{index}")
    public String sentMail(@ModelAttribute("userOrders") UserOrder userOrders, @PathVariable("index") int index ){
        System.out.println(index);

        System.out.println(userOrders.getOrder().getOrderId());
          Order order =  userOrders.getOrder();
        List<UserOrder> userOrderList = userOrderManager.findUserOrdersByOrderId(order.getOrderId());
        order.setItsUsers(userOrderList);

        System.out.println(order.getItsUsers().get(0).getUser().getEmail());
         mailManager.sendEmail(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders")
    public String goToOrders() {
              return "orders";
    }

//    @RequestMapping("/orders/{orderId}")
//    public String orderReview(Model model, @PathVariable int orderId, Principal principal) {
//
//            model.addAttribute("orderId",orderId);
//        return "orderReview";
//    }


}
