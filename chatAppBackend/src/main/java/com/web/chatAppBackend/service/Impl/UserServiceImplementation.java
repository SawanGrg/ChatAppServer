package com.web.chatAppBackend.service.Impl;

import com.web.chatAppBackend.DAO.UserRepository;
import com.web.chatAppBackend.DTO.AllUserDTO;
import com.web.chatAppBackend.model.User;
import com.web.chatAppBackend.service.UserService;
import com.web.chatAppBackend.util.AES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    AES256 aes256;

    @Autowired
    public UserServiceImplementation(
            @Qualifier("userRepository") UserRepository userRepository,
            AES256 aes256
    ) {
        this.userRepository = userRepository;
        this.aes256 = aes256;
    }
    @Override
    public boolean authenticateUser(String username, String password) {
        return userRepository.userExists(username, password);
    }

    @Override
    public long getUserId(String username) {
        return userRepository.getUserId(username);
    }

    @Override
    public List<AllUserDTO> allUser() {
        List<User> users = userRepository.allUser();
        List<AllUserDTO> allUserDTO = new ArrayList<>();  // Initialize the list

        for (User user : users) {
            String token = aes256.encrypt(user.getUserName());
            AllUserDTO allUserDTO1 = new AllUserDTO(user.getUserName(), token);
            allUserDTO.add(allUserDTO1);
        }

        return allUserDTO;
    }

}
