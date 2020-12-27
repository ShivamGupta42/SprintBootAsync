package com.springboot.async.springBootAsync.controllers;

import com.springboot.async.springBootAsync.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boot/async")
public class RetryController {
    @Autowired
    RetryService retryService;

    @Autowired
    RetryTemplate retryTemplate;

    @GetMapping("/retry/hello")
    public String getHello(){
    //return retryService.retryAnnotations("Say Hello");
     return retryTemplate.execute(arg -> retryService.retryTemplate("Say Hello to template"));
    }



}
