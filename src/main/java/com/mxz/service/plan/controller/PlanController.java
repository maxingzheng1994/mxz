package com.mxz.service.plan.controller;

import com.mxz.service.plan.mapper.PlanMapper;
import com.mxz.service.plan.model.Plan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/plan")
public class PlanController {

    @Resource
    private PlanMapper planMapper;

    @RequestMapping("/list")
    public List<Plan> list() {
        return null;
    }

    @RequestMapping("/save")
    public boolean save(@RequestBody Plan plan) {
        planMapper.insert(plan);
        return false;
    }
}
