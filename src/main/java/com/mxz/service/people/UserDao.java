package com.mxz.service.people;

import com.mxz.service.people.model.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Select("select * from Pe where id= #{id}")
    People findById(Integer id);
}