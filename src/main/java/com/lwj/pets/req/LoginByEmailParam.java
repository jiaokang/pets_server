package com.lwj.pets.req;


import lombok.Data;

@Data
public class LoginByEmailParam {
    private String email;
    private String code;
    private boolean remember;
}
