package com.springboot.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class loginController {

      @RequestMapping("/index")
      public String dashboard() {
          return "dashboard";
      }
}
