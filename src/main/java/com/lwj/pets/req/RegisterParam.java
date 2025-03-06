package com.lwj.pets.req;


import lombok.Data;

@Data
public class RegisterParam {
    private String username;
    private String email;
    private String password;
    private String code;
}
