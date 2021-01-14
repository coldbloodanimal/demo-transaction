package com.example.users;

import com.example.sys.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/***
 * 后面全局异常处理，会使用到
 */
@RestController
public class UsersController {


    @RequestMapping(value = "/throwException", method = RequestMethod.GET)
    public String throwException() throws Exception {
        CommonUtil.throwException();
        return "error";
    }

    @RequestMapping(value = "/throwSubClassOfException", method = RequestMethod.GET)
    public String SubClassOfException() throws Exception {
        CommonUtil.throwSubClassOfException();
        return "error";
    }

    @GetMapping(value = "/throwRuntimeException")
    public String throwRuntimeException() throws Exception {
        CommonUtil.throwRuntimeException();
        return "error";
    }

    @RequestMapping(value = "/throwSubClassOfRuntimeException", method = RequestMethod.GET)
    public String throwSubClassOfRuntimeException() throws Exception {
        CommonUtil.throwSubClassOfRuntimeException();
        return "error";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }


}
