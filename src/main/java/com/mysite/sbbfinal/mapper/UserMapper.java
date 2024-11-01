package com.mysite.sbbfinal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mysite.sbbfinal.domain.User;

@Mapper
public interface UserMapper {
	User findByUsername(@Param("username") String username);
}
