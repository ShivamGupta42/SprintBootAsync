package com.springboot.async.springBootAsync.service;


import com.springboot.async.springBootAsync.Entities.User;
import com.springboot.async.springBootAsync.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    Object target;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async
    public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<User> users = parseCSVFile(file);
        logger.info("saving list of users of size " + users.size() + " " + Thread.currentThread().getName());
        users = repository.saveAll(users);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<User>> findAllUsers() {
        logger.info("get list of user by " + Thread.currentThread().getName());
        List<User> users = repository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    private List<User> parseCSVFile(final MultipartFile file) throws Exception {
        final List<User> users = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setPhoneNumber(data[2]);
                    users.add(user);
                }
                return users;
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }


    public CompletableFuture<List<User>> saveUsersOldWay(MultipartFile file) {

        return CompletableFuture.completedFuture(null)
                .thenApply(__ -> {

                    CompletableFuture<List<User>> users = null;
                    try {
                        users = parseCSVFileOldWay(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("ThenApply method " + Thread.currentThread().getName());
                    return users;
                }).thenCompose(users ->{
                    logger.info("ThenCompose saving users in db " +Thread.currentThread().getName());
                    List<User> savedUsers = repository.saveAll(users.join());
                    return CompletableFuture.completedFuture(savedUsers);
                });
    }

    private CompletableFuture<List<User>> parseCSVFileOldWay(final MultipartFile file) throws Exception {
        logger.info("parse method " + Thread.currentThread().getName());
        final List<User> users = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setPhoneNumber(data[2]);
                    users.add(user);
                }
                return CompletableFuture.completedFuture(users);
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }


    @Async
    public CompletableFuture<List<User>> saveUsersOldWayWithAsync(MultipartFile file) {

        return CompletableFuture.completedFuture(null)
                .thenApply(__ -> {

                    CompletableFuture<List<User>> users = null;
                    try {
                        users = parseCSVFileOldWayWithAsync(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("ThenApply method " + Thread.currentThread().getName());
                    return users;
                }).thenCompose(users ->{
                    logger.info("ThenCompose saving users in db " +Thread.currentThread().getName());
                    List<User> savedUsers = repository.saveAll(users.join());
                    return CompletableFuture.completedFuture(savedUsers);
                });
    }


    private CompletableFuture<List<User>> parseCSVFileOldWayWithAsync(final MultipartFile file) throws Exception {
        logger.info("parse method " + Thread.currentThread().getName());
        final List<User> users = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setPhoneNumber(data[2]);
                    users.add(user);
                }
                return CompletableFuture.completedFuture(users);
            }
        } catch (final IOException e) {
            logger.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }


}