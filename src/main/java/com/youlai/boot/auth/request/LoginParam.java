package com.youlai.boot.auth.request;


import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
    private boolean remember;
}
