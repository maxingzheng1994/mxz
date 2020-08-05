package com.mxz.service.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxz.service.plan.model.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 15:37
 */
public interface PlanMapper  extends BaseMapper<Plan> {
    int insert(Plan record);

    int batchInsert(@Param("list") List<Plan> list);
}