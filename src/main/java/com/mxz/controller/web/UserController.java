package com.mxz.controller.web;

import com.mxz.common.advice.aspect.HumenService;
import com.mxz.model.User;
import com.mxz.service.MongoService;
import com.mxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
*@Description 
*@author mxz
*2018-07-26
**/
@RequestMapping("user")
@RestController
public class UserController {
   
    @Autowired
    private UserService userService;

    @Resource(name = "mongoService")
    private MongoService mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/hello")
    public String hello() {
        HumenService h =  (HumenService)userService;
        h.playGame();
        return "hello";
    }

    @GetMapping("/hi")
    public String hi() {
        HumenService h =  (HumenService)userService;
        h.playGame();
        return "hi";
    }

    @GetMapping("/transaction")
    public void updateWithTransaction() {
        mongoService.updateWithTransaction();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return mongoService.insert(user);
    }

    @GetMapping("")
    public List<User> getUser(User user) {
        Query query = Query.query(Criteria.where("username").is(user.getUsername()));
        return mongoTemplate.find(query, User.class);
    }
    
    @GetMapping("/error")
    public int error() {
        return 1/0;
    }
}
