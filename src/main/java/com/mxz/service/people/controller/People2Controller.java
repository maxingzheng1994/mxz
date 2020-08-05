package com.mxz.service.people.controller;

import com.mxz.model.User;
import com.mxz.service.people.model.People2;
import com.mxz.service.people.service.People2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/05 16:48
 */
@RestController
@RequestMapping("/people2")
public class People2Controller {

    @Autowired
    private People2Service people2Service;

    @RequestMapping("/save")
    public void test(People2 people2) {
        people2Service.save(people2);
    }

    @RequestMapping("/")
    public List<People2> test() {
        return people2Service.list();
    }

}
