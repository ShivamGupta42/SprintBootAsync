package com.springboot.async.springBootAsync.controllers;

import com.springboot.async.springBootAsync.Entities.PostMatchReq;
import com.springboot.async.springBootAsync.Entities.User;
import com.springboot.async.springBootAsync.service.UserService;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@RestController
@RequestMapping("boot/async")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.toString());
    @Autowired
    private UserService service;



    @PostMapping(value = "/testPostMethod", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void testPostMethod( @ModelAttribute PostMatchReq req) throws Exception {
        System.out.println("receiving values");
        System.out.println("req body score:"+ req.toString());
    }



    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        long start = System.currentTimeMillis();
        for (MultipartFile file : files) {
            service.saveUsers(file);
        }
        logger.info("Time taken to process the req :"+ (System.currentTimeMillis()-start));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping(value = "/users/oldway", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsersOldWay(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        long start = System.currentTimeMillis();
        for (MultipartFile file : files) {
            service.saveUsersOldWay(file);
        }
        logger.info("Time taken to process the req :"+ (System.currentTimeMillis()-start));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/users/oldway/async", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsersOldWayWithAsyncAnnotation(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        long start = System.currentTimeMillis();
        for (MultipartFile file : files) {
            service.saveUsersOldWayWithAsync(file);
        }
        logger.info("Time taken to process the req :"+ (System.currentTimeMillis()-start));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/users", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers() {
        return  service.findAllUsers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    public  ResponseEntity getUsers(){
        CompletableFuture<List<User>> users1=service.findAllUsers();
        CompletableFuture<List<User>> users2=service.findAllUsers();
        CompletableFuture<List<User>> users3=service.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}