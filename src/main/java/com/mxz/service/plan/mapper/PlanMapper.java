package com.mxz.service.plan.mapper;

import com.mxz.service.plan.model.Plan;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 15:37
 */
public interface PlanMapper {
    int insert(Plan record);

    int batchInsert(@Param("list") List<Plan> list);
}