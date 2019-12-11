package com.mxz.mapper;

import com.mxz.model.Employee;

import java.util.List;

/**
 * @Description
 * @Date 2019/12/10 21:02
 * @Author mxz
 */
public interface EmployeesMapper {

    public List<Employee> selectByPrimaryKey(Integer employeeId);
}
