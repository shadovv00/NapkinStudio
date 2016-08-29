package com.napkinstudio.controller;

import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.User;
import com.napkinstudio.manager.OrderManager;
import com.napkinstudio.manager.RoleManager;
import com.napkinstudio.manager.StatusManager;
import com.napkinstudio.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

public class OrderOverviewController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private StatusManager statusManager;

    @Autowired
    private OrderManager orderManager;


    @RequestMapping(value = "/orderoverview")
    public String goToOrderOverview(Model model, Principal principal) {
        String login = principal.getName();
        System.out.println("OrderOverviewContr");
        System.out.println(login);
        System.out.println(principal);
        System.out.println(model);
        User user = userManager.findByLogin(login);

//        List<Role> roles = roleManager.findByUserId(user.getUserId());
//      List<Order> orders = orderManager.findByUserId(user.getUserId());
////
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
//        user.setRoles(roles);

        model.addAttribute("user",user);
//        model.addAttribute("statusList", statusList);
        return "orders";
    }



}
