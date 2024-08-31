package com.blog.mapper;

import com.blog.entity.User;
import com.blog.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class UserMapper {


    @Autowired
    ModelMapper modelMapper;

    public User userDtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public  UserDto userToUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public List<User> userDtoListToUser(List<UserDto> userDtoList){
        return userDtoList.stream().map(
                userDto -> modelMapper.map(userDto,User.class)
        ).collect(Collectors.toList());
    }
    public List<UserDto> userListToUserDto(List<User> userList){
        return userList.stream().map(
                user -> modelMapper.map(user, UserDto.class)
        ).collect(Collectors.toList());
    }
}
