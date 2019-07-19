package com.gdglc.tedu.rest.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private String uid;
    private String author;
    private String password;
    private String phone;
    private String email;
    private int emailActivate;
    private String address;
    private Date birthday;
    private String text;
}
