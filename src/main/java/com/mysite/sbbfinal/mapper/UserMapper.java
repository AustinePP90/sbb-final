package com.mysite.sbbfinal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mysite.sbbfinal.domain.Role;
import com.mysite.sbbfinal.domain.User;

@Mapper
public interface UserMapper {
	User findByUsername(@Param("username") String username);
	
    void save(User user);
    
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
    
    List<User> findAll();
    
    List<Role> findRolesByUserId(@Param("userId") Long userId);
    
    User findById(@Param("id") Long id);
    
    List<Role> getAllRoles();
    
    void deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
