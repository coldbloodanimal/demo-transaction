package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class UsersController {

    @Autowired
    UsersService service;


    @Resource
    UsersMapper usersMapper;



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        Logger.getAnonymousLogger().info("hello");
        return "hello";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Object select() {
        List<Map<String,String>> result=usersMapper.selectMap();
        Map<String,String> resultMap=new HashMap<>();
        result.stream().forEach(t->resultMap.put(t.get("id"),t.get("username")));
        return  resultMap;
    }


    @RequestMapping(value = "/insert1", method = RequestMethod.GET)
    public void insert1() {
        service.insert1(Users.getGoodUser(),Users.getGoodUser());
    }

}
