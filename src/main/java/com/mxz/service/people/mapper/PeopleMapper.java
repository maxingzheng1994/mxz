package com.mxz.service.people.mapper;

import com.mxz.service.people.model.People;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/05 16:29
 */
public interface PeopleMapper {
    int deleteByPrimaryKey(Integer useId);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer useId);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}