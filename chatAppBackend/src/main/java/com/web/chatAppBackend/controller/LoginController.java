package com.web.chatAppBackend.controller;

import com.web.chatAppBackend.DTO.LoginResponseDTO;
import com.web.chatAppBackend.DTO.LoginRequestDTO;
import com.web.chatAppBackend.Response.ApiResponse;
import com.web.chatAppBackend.service.UserService;
import com.web.chatAppBackend.util.AES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/api/user")
public class LoginController {
    public UserService userService;
    public AES256 aes256;
    @Autowired
    public LoginController(UserService userService, AES256 aes256) {
        this.userService = userService;
        this.aes256 = aes256;
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO userLoginDTO) {
        try {
            boolean isAuthenticated = userService.authenticateUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());

            if (isAuthenticated) {

                String token = aes256.encrypt(userLoginDTO.getUsername());
                long userId = userService.getUserId(userLoginDTO.getUsername());
                LoginResponseDTO loginResponseDTO = new LoginResponseDTO(200, token, userLoginDTO.getUsername(), String.valueOf(userId));
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200, "success", loginResponseDTO));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, "error", "Invalid username or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500, "error", "Internal server error"));
        }
    }
}

