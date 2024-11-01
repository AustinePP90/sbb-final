package com.mysite.sbbfinal.service;

import org.springframework.stereotype.Service;

import com.mysite.sbbfinal.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
    private final UserMapper userMapper;

    public int getUserIdByUsername(String username) {
        return userMapper.getUserIdByUsername(username);
    }
}
