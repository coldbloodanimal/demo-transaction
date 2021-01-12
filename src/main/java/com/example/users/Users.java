package com.example.users;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Users {
    private String id;

    private String username;

    private String password;

    private Date createDate;

    public static Users getGoodUser(){
        Users gu=new Users();
        gu.setId(UUID.randomUUID().toString());
        gu.setUsername(UUID.randomUUID().toString().substring(0,5));
        gu.setCreateDate(new Date());
        return gu;
    }

    public static Users getBadUser(){
        Users bu= getGoodUser();
        bu.setUsername(UUID.randomUUID().toString());
        return bu;
    }
}