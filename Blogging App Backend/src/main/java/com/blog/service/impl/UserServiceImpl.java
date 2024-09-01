package com.blog.service.impl;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.UserMapper;
import com.blog.payload.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUser(Integer id) {
        return userMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(
                ()-> new ResourceNotFoundException(id,"Requested User With Id: "+id+" can not be found."))
        );
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer id) {
        userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id,"Requested User With Id: "+id+" can not be found."));
        userDto.setId(id);
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id,"Requested User With Id: "+id+" can not be found."));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.userListToUserDto(userRepository.findAll());
    }
}
