package com.web.chatAppBackend.service;

import com.web.chatAppBackend.DTO.AllUserDTO;

import java.util.List;

public interface UserService {

    boolean authenticateUser(String username, String password);
    long getUserId(String username);

    List<AllUserDTO> allUser();
}
