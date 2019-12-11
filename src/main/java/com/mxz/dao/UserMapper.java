package com.mxz.dao;
/**
*@Description
*@autor:mxz
*2018-07-29
**/

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mxz.model.UserVO;


@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM users WHERE userName like #{userName}")
	public UserVO findUserByUserName(String userName);
}
