package com.napkinstudio.controller;

import com.napkinstudio.entity.User;
import com.napkinstudio.manager.RoleManager;
import com.napkinstudio.manager.StatusManager;
import com.napkinstudio.manager.UserManager;
import com.napkinstudio.util.FTPCommunicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by User1 on 19.07.2016.
 */
@Controller
public class HomeController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private FTPCommunicator converter;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private StatusManager statusManager;
//    @ModelAttribute("user")
//    public User construct(Principal principal) {
//       String login = principal.getName();
//        User user = userManager.findByLogin(login);
//        String role= user.getRoles().get(0).getName();
//        System.out.print(role);
//         return user;
//    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

//    @RequestMapping(value = "/orders")
//    public String goToOrders(Model model, Principal principal) {
//        String login = principal.getName();
//        User user = userManager.findByLogin(login);
//        List<Role> roles = roleManager.findByUserId(user.getUserId());
//        System.out.print(roles.get(0).getName());
//        List<Status> statusList = statusManager.findByRoleId(roles.get(0).getId());
//        roles.get(0).setStatus(statusList);
//        user.setRoles(roles);
//        model.addAttribute("user",user);
//        model.addAttribute("statusList", statusList);
//        return "orders";
//    }

    @RequestMapping(value = "/transfer")
    public String transfer() {

        User user = new User();
        user.setLogin("basbdbasd");

        try {
         userManager.upload(user);
             } catch (IOException e) {
            e.printStackTrace();
        }

        return "transfer";

    }
}