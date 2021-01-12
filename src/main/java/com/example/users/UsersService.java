package com.example.users;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/***
 * 这里主要测试异常和事务的几种关系
 * 1.添加事务，正常数据                                                      正常，用于参照
 * 2.添加事务，异常数据，不使用try catch，抛出RuntimeExcepiton或其子类           正常，数据回滚
 * 3.添加事务，异常数据，不使用try catch，抛出非RuntimeException或其子类         异常，数据不回滚
 * 4.添加事务，异常数据，使用try catch捕获，不抛出异常                           异常，数据不回滚
 * 5.添加事务，异常数据，使用try catch捕获，抛出RuntimeException                正常，数据回滚
 * 6.添加事务，异常数据，使用try catch捕获，抛出RuntimeException的子类           正常，数据回滚
 * 7.添加事务，异常数据，使用try catch捕获，抛出非RuntimeException或其子类        异常，数据不回滚
 * */
@Slf4j
@Service
public class UsersService {
    //此处推测 autowired 可能没有聪明到找到mapper的实现类，所以会害怕mapper是null
    @Setter
    @Resource
    UsersMapper mapper;

    /**
     * 1.添加事务，正常数据
     * */
    @Transactional
    public void insert1(Users user1,Users user2){
        user1.setPassword("方法1");
        user2.setPassword("方法1");
        mapper.insert(user1);
        log.info(user1.toString());
        mapper.insert(user2);
        log.info(user2.toString());
    }

    /**
     * 2.添加事务，异常数据，不使用try catch，抛出RuntimeExcepiton或其子类
     * */
    @Transactional
    public void insert2(Users user1,Users user2){
        user1.setPassword("方法2");
        user2.setPassword("方法2");
        mapper.insert(user1);
        log.info(user1.toString());
        mapper.insert(user2);
        log.info(user2.toString());
    }

    /**
     * 3.添加事务，异常数据，不使用try catch，抛出非RuntimeExcepiton或其子类
     * */
    @Transactional
    public void insert3(Users user1,Users user2) throws Exception {
        user1.setPassword("方法3");
        user2.setPassword("方法3");
        mapper.insert(user1);
        log.info(user1.toString());
        //用于抛出异常
        throwException();
        mapper.insert(user2);
        log.info(user2.toString());
    }

    /**
     * 4.添加事务，异常数据，不使用try catch，抛出非RuntimeExcepiton或其子类
     * */
    @Transactional
    public void insert4(Users user1,Users user2){

        try{
            user1.setPassword("方法4");
            user2.setPassword("方法4");
            mapper.insert(user1);
            log.info(user1.toString());
            mapper.insert(user2);
            log.info(user2.toString());
        }catch (Exception e){
            log.error("方法4出错");
            log.error(e.getMessage());
        }

    }


    /**
     * 5.添加事务，异常数据，使用try catch捕获，抛出RuntimeException
     * */
    @Transactional
    public void insert5(Users user1,Users user2){

        try{
            user1.setPassword("方法5");
            user2.setPassword("方法5");
            mapper.insert(user1);
            log.info(user1.toString());
            mapper.insert(user2);
            log.info(user2.toString());
        }catch (Exception e){
            log.error("方法5出错");
            log.error(e.getMessage());
            throw new RuntimeException();
        }

    }

    /**
     * 6.添加事务，异常数据，使用try catch捕获，抛出RuntimeException的子类
     * */
    @Transactional
    public void insert6(Users user1,Users user2){

        try{
            user1.setPassword("方法6");
            user2.setPassword("方法6");
            mapper.insert(user1);
            log.info(user1.toString());
            mapper.insert(user2);
            log.info(user2.toString());
        }catch (Exception e){
            log.error("方法6出错");
            log.error(e.getMessage());
            throw new SysException();
        }

    }

    /**
     * 7.添加事务，异常数据，使用try catch捕获，抛出非RuntimeException或其子类
     * */
    @Transactional
    public void insert7(Users user1,Users user2) throws Exception {
        try{
            user1.setPassword("方法7");
            user2.setPassword("方法7");
            mapper.insert(user1);
            log.info(user1.toString());
            mapper.insert(user2);
            log.info(user2.toString());
        }catch (Exception e){
            log.error("方法7出错");
            log.error(e.getMessage());
            throwException();
        }
    }


    public void cleanData(){
        List<Map<String,String>> list=mapper.selectMap();
        for (Map<String, String> stringStringMap : list) {
            mapper.deleteByPrimaryKey(stringStringMap.get("id"));
        }
    }


    public void throwException() throws Exception {
        throw new Exception("数据校验错误");
    }
}
