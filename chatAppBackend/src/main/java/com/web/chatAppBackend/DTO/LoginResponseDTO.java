package com.web.chatAppBackend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {
    private int status;
    private String token;
    private String username;
    private String userId;

    // Default constructor
    public LoginResponseDTO() {}

    // Parameterized constructor
    public LoginResponseDTO(int status, String token, String username, String userId) {
        this.status = status;
        this.token = token;
        this.username = username;
        this.userId = userId;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
