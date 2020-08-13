package com.mxz.service.web;

import com.mxz.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
*@Description 
*@author mxz
*2018-07-26
**/
@Controller
@RequestMapping("/user")
public class UserController {
   

//    @Autowired
//    private LocationRepository locationRepository;

//    @Resource(name = "mongoService")
//    private MongoService mongoService;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("uid", "asdasdas");
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hi")
    public String hi() {
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
    public ModelAndView getUser(User user) {
        return new ModelAndView("index");
//        Query query = Query.query(Criteria.where("username").is(user.getUsername()));
//        return mongoTemplate.find(query, User.class);
    }
    
    @GetMapping("/error")
    public int error() {
        return 1/0;
    }

}
