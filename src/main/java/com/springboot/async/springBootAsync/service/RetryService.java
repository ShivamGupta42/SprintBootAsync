package com.springboot.async.springBootAsync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    /*
      Requires a configuration file with @EnableRetry annotation
     */
    @Retryable(value=RuntimeException.class,maxAttemptsExpression = "${retry.max.attempts}", backoff = @Backoff(delayExpression = "${retry.max.delay}"))
    public String retryAnnotations(String req){
        System.out.println("Exception Occurred "+req);
        throw new RuntimeException();
    }

    @Recover
    public void recover(RuntimeException r, String req){
        System.out.println("Unable to fetch result for "+req);
    }

    public String retryTemplate( String req){
        System.out.println("Exception Occurred "+req);
        try {
            throw new Exception();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return "Resolved!";
    }



}
