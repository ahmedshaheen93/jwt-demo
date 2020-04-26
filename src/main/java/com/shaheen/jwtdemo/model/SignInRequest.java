package com.shaheen.jwtdemo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignInRequest {
    @NotEmpty(message = "you should enter username")
    @NotNull(message = "username can't be null ")
    private String username = "";
    @NotEmpty(message = "you should enter password")
    @NotNull(message = "password can't be null ")
    private String password = "";

    public SignInRequest() {
    }

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
