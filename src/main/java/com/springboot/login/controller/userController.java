package com.springboot.login.controller;

import com.springboot.login.helper.Message;
import com.springboot.login.model.User;
import com.springboot.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class userController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String Home() {

        return "home";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
         try{
             if(result.hasErrors()){
                 System.out.println("Error " + result.toString());
                 model.addAttribute("user", user);
                 return "signup";
             }
             user.setRole("ROLE_USER");
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             User result1 =this.userRepository.save(user);
             model.addAttribute("user",new User());
             session.setAttribute("message", new Message("Successfully registered", "alert-success"));
             return "signup";

         } catch (Exception e) {
             e.printStackTrace();
             model.addAttribute("user", user);
             session.setAttribute("message", new Message("Something went wrong", "alert-danger"));
             return "signup";

         }
    }

    @RequestMapping("/signing")
    public String customLogin(Model model) {
        model.addAttribute("title","Login Page");
        return "login";
    }

}
