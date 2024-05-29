package com.web.chatAppBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllUserDTO {

    private String username;
    private String token;

    public AllUserDTO() {
    }

    public AllUserDTO( String username, String token) {
        this.username = username;
        this.token = token;
    }


}
