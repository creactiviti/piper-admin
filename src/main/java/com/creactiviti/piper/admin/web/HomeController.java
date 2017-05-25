package com.creactiviti.piper.admin.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

  private final RestTemplate rest = new RestTemplate();
  
  @GetMapping("/")
  public String home (Model aModel) {
    Map<String,Object> jobs = rest.getForObject("http://localhost:8080/jobs", Map.class);
    aModel.addAttribute("jobs",jobs);
    return "home";
  }
  
}
