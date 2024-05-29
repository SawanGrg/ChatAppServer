package com.web.chatAppBackend.controller;

import com.web.chatAppBackend.DTO.AllUserDTO;
import com.web.chatAppBackend.Response.ApiResponse;
import com.web.chatAppBackend.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/api/user")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse<?>> getAllUser(){
        try{

            List<AllUserDTO> getAllUserDTO = userService.allUser();
            return ResponseEntity.status(200).body(new ApiResponse<>(200, "success", getAllUserDTO));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "error", "Internal server error"));
        }
    }



}
