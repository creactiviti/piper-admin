package com.creactiviti.piper.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

  private final RestTemplate rest = new RestTemplate();
  
  @GetMapping("/")
  public String home () {
    return "redirect:/jobs";
  }
  
}
