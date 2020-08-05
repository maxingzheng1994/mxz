package com.mxz.service.plan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxz.service.plan.mapper.PlanMapper;
import com.mxz.service.plan.model.Plan;
import com.mxz.service.plan.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author mxz on 2020/07/25 17:29
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {

    @Override
    public int batchInsert(List<Plan> list) {
        return baseMapper.batchInsert(list);
    }
}
