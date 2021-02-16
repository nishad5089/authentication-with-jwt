package com.commonauthmodule.commonauthmodule.model;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String username;
    private String password;

    public AuthenticateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticateRequest() {
    }
}
