package com.napkinstudio.controller;

import com.napkinstudio.entity.User;
import com.napkinstudio.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User1 on 31.08.2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserManager userManager;

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @RequestMapping("/loginproblem")
    public String showLogiinProblem() {
        return "loginproblem";
    }

    @RequestMapping("/register")
    public String showRegister() {
        return "user-register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("user") User user,
                             BindingResult result) {
        if (result.hasErrors()) {
                        return "user-register";
        }

        userManager.save(user);
        return "redirect:/login.html?success=true";
    }

    @RequestMapping("/register/avaliable")
    @ResponseBody
    public String avaliable(@RequestParam String username) {
        Boolean correct = userManager.findByLogin(username) == null;
        return correct.toString();
    }

    @RequestMapping("/register/availableLogin")
    @ResponseBody
    public String availableLogin( @ModelAttribute("user") User user) {
        Boolean correct = userManager.findByLogin(user.getLogin()) == null;
        return correct.toString();
    }
    @RequestMapping("/register/availableEmail")
    @ResponseBody
    public String availableEmail( @ModelAttribute("user") User user) {
        Boolean correct = userManager.findByEmail(user.getEmail()) == null;
        return correct.toString();
    }
}
