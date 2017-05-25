package com.creactiviti.piper.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home () {
    return "redirect:/jobs";
  }
  
}
