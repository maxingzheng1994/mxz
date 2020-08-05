package com.mxz.service.people.controller;

import com.mxz.service.people.model.People;
import com.mxz.service.people.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/05 16:29
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleMapper peopleMapper;

    @RequestMapping("/insert")
    public int insert() {
        People people = new People();
        people.setUseName("sadas");
      return peopleMapper.insert(people);
    }
}
