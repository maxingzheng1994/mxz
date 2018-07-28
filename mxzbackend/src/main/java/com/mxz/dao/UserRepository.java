package com.mxz.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mxz.model.User;

/**
*@Description  
*@author mxz
*2018年7月28日
**/
public interface UserRepository extends CrudRepository<User, Long>{
    
}
