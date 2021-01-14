package com.example.sys;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("b1","null");
        map.put("b2","");
        map.put("b3",null);
        map=ProxyMap.getInstance(map);
        ProxyMap proxyMap=new ProxyMap(map);
        System.out.println(proxyMap.isValueEmpty("b3"));
        System.out.println(proxyMap.getString("b1"));
        System.out.println(proxyMap.getString("b2"));
        System.out.println(proxyMap.getString("b3"));
        System.out.println(proxyMap.get("b3"));
    }
}
