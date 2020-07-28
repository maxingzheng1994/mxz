package com.mxz.service.plan.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 15:37
 */
/**
    * 计划
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    private Integer id;

    private String name;

    private Date beginTime;

    private Date endTime;
}