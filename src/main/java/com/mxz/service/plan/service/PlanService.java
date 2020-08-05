package com.mxz.service.plan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxz.service.plan.model.Plan;

import java.util.List;

/**
* TODO: 注释.
*
* @author mxz on 2020/07/25 17:29
*/
public interface PlanService extends IService<Plan>{


int batchInsert(List<Plan> list);

}
