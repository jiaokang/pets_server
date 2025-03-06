package com.lwj.pets.req;


import lombok.Data;

@Data
public class LoginByEmailParam {
    private String username;
    private String password;
    private boolean remember;
}
