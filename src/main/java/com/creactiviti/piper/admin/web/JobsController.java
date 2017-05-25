package com.creactiviti.piper.admin.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class JobsController {

  private final RestTemplate rest = new RestTemplate();
  
  @Value("${piper.api.url}")
  private String api = "http://localhost:8080"; 
  
  @GetMapping("/jobs")
  public String list (@RequestParam(value="p",defaultValue="1") Integer aPage, Model aModel) {
    Map<String,Object> jobs = rest.getForObject(String.format("%s/jobs?p=%s",api,aPage), Map.class);
    aModel.addAttribute("jobs",jobs);
    aModel.addAttribute("page",aPage);
    return "jobs";
  }
  
  @GetMapping("/jobs/{id}")
  public String get (@PathVariable("id") String aJobId, Model aModel) {
    Map<String,Object> jobs = rest.getForObject(String.format("%s/jobs/%s",api,aJobId), Map.class);
    aModel.addAttribute("job",jobs);
    return "job";
  }
  
}
