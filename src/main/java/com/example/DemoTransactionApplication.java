package com.example;

import com.example.users.Users;
import com.example.users.UsersMapper;
import com.example.users.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTransactionApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            UsersMapper usersMapper = (UsersMapper) ctx.getBean("usersMapper");
            UsersService usersService = (UsersService) ctx.getBean("usersService");
            //推测，此时应该是对象初始化阶段，还没有开始组装，所以service中的mapper是空的，
            usersService.setMapper(usersMapper);
/***
 * 这里主要测试异常和事务的几种关系
 * 1.添加事务，正常数据                                                      正常
 * 2.添加事务，异常数据，不使用try catch，抛出RuntimeExcepiton                  正常，数据回滚
 * 3.添加事务，异常数据，不使用try catch，抛出RuntimeExcepiton子类
 * 4.添加事务，异常数据，使用try catch捕获，不抛出异常
 * 5.添加事务，异常数据，使用try catch捕获，抛出RuntimeException或其子类
 * 6.添加事务，异常数据，使用try catch捕获，抛出非RuntimeException或其子类
 * */

            //清空历史数据
            usersService.cleanData();

            //下面方法会抛出异常，但是我们不想要线程终止，所以try catch，为了执行后续代码

            usersService.insert1(Users.getGoodUser(), Users.getGoodUser());

            try {
                usersService.insert2(Users.getGoodUser(), Users.getBadUser());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            //方法会抛出异常，但是我们不想要线程终止，所以try catch，为了执行后续代码
            try {
                usersService.insert3(Users.getGoodUser(), Users.getBadUser());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            usersService.insert4(Users.getGoodUser(), Users.getBadUser());

            try {
                usersService.insert5(Users.getGoodUser(), Users.getBadUser());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            try {
                usersService.insert6(Users.getGoodUser(), Users.getBadUser());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            try {
                usersService.insert7(Users.getGoodUser(), Users.getBadUser());
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        };
    }

}
