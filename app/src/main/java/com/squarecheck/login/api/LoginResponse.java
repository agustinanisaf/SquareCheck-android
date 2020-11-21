package com.squarecheck.login.api;

public class LoginResponse {
    public boolean is_success;
    public String token;
    public String message;

    public LoginResponse(boolean is_success, String token, String message) {
        this.is_success = is_success;
        this.token = token;
        this.message = message;
    }
}
