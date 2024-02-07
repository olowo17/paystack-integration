package com.michael.thirdpartyapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.thirdpartyapi.model.User;
import com.michael.thirdpartyapi.model.dto.RegistrationResponse;
import com.michael.thirdpartyapi.model.dto.UserRequestDto;
import com.michael.thirdpartyapi.model.dto.UserResponseDto;
import com.michael.thirdpartyapi.repository.UserRepository;
import com.michael.thirdpartyapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers (){
        var users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){
        var user = userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createUser (@RequestBody UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
         return new ResponseEntity<>(new RegistrationResponse( "User Created successfully"), HttpStatus.CREATED);

    }
}
