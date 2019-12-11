//package com.mxz.service;
//
//import com.mxz.model.Info;
//import com.mxz.model.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@Service("mongoService")
//public class MongoService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Transactional(rollbackFor = ArithmeticException.class)
//    public void updateWithTransaction() {
//        Query query = new Query(Criteria.where("username").is("vulgar-cd"));
//        Update update = new Update();
//        update.set("age", 10);
//        mongoTemplate.updateFirst(query, update, User.class);
//        User user = mongoTemplate.findOne(query, User.class);
////        log.info("user is {}", JSON.toJSON(user));
//        update = new Update();
//        update.set("description", "hahahaha");
//        mongoTemplate.updateFirst(query, update, Info.class);
//        Info info = mongoTemplate.findOne(query, Info.class);
//        //测试事务回滚
//        int i = 1/0;
//    }
//
//    public User insert(User user) {
//        return mongoTemplate.insert(user);
//    }
//
//}