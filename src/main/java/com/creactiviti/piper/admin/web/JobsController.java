package com.creactiviti.piper.admin.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class JobsController {

  private final RestTemplate rest = new RestTemplate();
  
  @Value("${piper.api.url}")
  private String api = "http://localhost:8080"; 
  
  private final ObjectMapper json = new ObjectMapper ();
  
  public JobsController() {
    json.enable(SerializationFeature.INDENT_OUTPUT);
  }
  
  @GetMapping("/jobs")
  public String list (@RequestParam(value="p",defaultValue="1") Integer aPage, Model aModel) {
    Map<String,Object> jobs = rest.getForObject(String.format("%s/jobs?p=%s",api,aPage), Map.class);
    aModel.addAttribute("jobs",jobs);
    aModel.addAttribute("page",aPage);
    aModel.addAttribute("json",json);
    return "jobs";
  }
  
  @GetMapping("/jobs/{id}")
  public String get (@PathVariable("id") String aJobId, Model aModel) {
    Map<String,Object> job = rest.getForObject(String.format("%s/jobs/%s",api,aJobId), Map.class);
    List<Map<String,Object>> execution = (List)job.get("execution");
    Collections.reverse(execution);
    aModel.addAttribute("job",job);
    aModel.addAttribute("json",json);
    return "job";
  }
  
  @ResponseBody
  @GetMapping("/jobs/{id}/restart")
  public String restart (@PathVariable("id") String aJobId) {
    rest.put(String.format("%s/jobs/%s/restart",api,aJobId), Map.class);
    return "OK";
  }
  
  @ResponseBody
  @GetMapping("/jobs/{id}/stop")
  public String stop (@PathVariable("id") String aJobId) {
    rest.put(String.format("%s/jobs/%s/stop",api,aJobId), Map.class);
    return "OK";
  }
  
}
