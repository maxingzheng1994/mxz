package com.mxz.dao;

import org.springframework.data.repository.CrudRepository;

import com.mxz.model.UserVO;

/**
*@Description  
*@author mxz
*2018年7月28日
**/
public interface UserRepository extends CrudRepository<UserVO, Long>{
    
}
