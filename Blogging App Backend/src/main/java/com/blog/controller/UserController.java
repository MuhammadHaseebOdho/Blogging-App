package com.blog.controller;

import com.blog.exception.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getUser(userId),HttpStatus.FOUND);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        return new ResponseEntity<>(userService.updateUser(userDto,userId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted",true),HttpStatus.OK);
    }
}
