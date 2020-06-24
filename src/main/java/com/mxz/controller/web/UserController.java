package com.mxz.controller.web;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mxz.common.advice.aspect.HumenService;
import com.mxz.model.Location;
import com.mxz.model.User;
import com.mxz.service.LocationRepository;
import com.mxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
*@Description 
*@author mxz
*2018-07-26
**/
@RestController
public class UserController {
   
    @Autowired
    private UserService userService;


//    @Autowired
//    private LocationRepository locationRepository;

//    @Resource(name = "mongoService")
//    private MongoService mongoService;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;

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
//        mongoService.updateWithTransaction();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return user;
//        return mongoService.insert(user);
    }

//    @RequestMapping("/hello3")
//    public List<Location> hell3o(){
//        return locationRepository.findAll();
//    }

    @GetMapping("")
    public List<User> getUser(User user) {
        return null;
//        Query query = Query.query(Criteria.where("username").is(user.getUsername()));
//        return mongoTemplate.find(query, User.class);
    }
    
    @GetMapping("/error")
    public int error() {
        return 1/0;
    }

}
