package com.mysite.sbbfinal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mysite.sbbfinal.domain.User;

@Mapper
public interface UserMapper {
	User findByUsername(@Param("username") String username);
	
    void save(User user);
    
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
