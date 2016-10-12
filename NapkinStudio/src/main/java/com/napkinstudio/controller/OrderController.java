package com.napkinstudio.controller;



import com.napkinstudio.entity.*;
import com.napkinstudio.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private MailManager mailManager;
    @Autowired
    private CommentsManager commentManager;


    @ModelAttribute("user")
    public User user(Principal principal){
        String login = principal.getName();
        User user = userManager.findByLogin(login);

        Role role = roleManager.findByUserId(user.getUserId());
        user.setRole(role);
        return user;
    }

    @ModelAttribute("orderIdList")
    public List<String> orderIdList () {
        String [] orderIdList;
        return new ArrayList<String>();
    }

    @ModelAttribute("userOrders")
    List<UserOrder> UserOrders(@ModelAttribute("user") User user) {



        Integer roleId = user.getRole().getId();
        List<UserOrder> userOrders = userOrderManager.findOrdersByUserId(user.getUserId());

                Integer unreadCommentsCount = 0;
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
                if(user.getRole().getId() == 5)
                unreadCommentsCount = commentManager.countUnreadCommentsByRoleId(user.getUserId(),userOrder.getOrder().getOrderId(), 5);
                else if(user.getRole().getId() == 1)
                {
                    List<Integer> roleIdList = new ArrayList<>();
                    roleIdList.add(5);
                    roleIdList.add(1);
                    unreadCommentsCount =commentManager.countUnreadCommentsByRoleIds(user.getUserId(),userOrder.getOrder().getOrderId(),roleIdList);
                }

                else if(user.getRole().getId() == 2 || user.getRole().getId() == 4 )
                    unreadCommentsCount =commentManager.countAllUnreadComments(user.getUserId(),userOrder.getOrder().getOrderId());

                userOrder.getOrder().setUnreadCommentsCount(unreadCommentsCount);
            }catch (NullPointerException e) {
                e.printStackTrace(System.out);
                iterator.remove();
            }


        }
        return userOrders;
    }


//    @RequestMapping("orders/sendEmail/{index}")
//    public String sentMail(@ModelAttribute("userOrders") UserOrder userOrders, @PathVariable("index") int index ){
//        System.out.println(index);
//
//        System.out.println(userOrders.getOrder().getOrderId());
//          Order order =  userOrders.getOrder();
//        List<UserOrder> userOrderList = userOrderManager.findUserOrdersByOrderId(order.getOrderId());
//        order.setItsUsers(userOrderList);
//
//        System.out.println(order.getItsUsers().get(0).getUser().getEmail());
//         mailManager.sendEmail();
//        return "redirect:/orders";
//    }

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
