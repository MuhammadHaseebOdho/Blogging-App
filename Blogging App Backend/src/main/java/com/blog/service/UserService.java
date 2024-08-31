package com.blog.service;

import com.blog.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUser(Integer id);

    List<UserDto> getUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Integer id);
}
