package com.lwj.pets.req;


import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
    private boolean remember;
}
