package com.example.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *异常会被与自己"距离"最近（关系最亲密）的异常处理器捕获
 *
 * 以下列举三个异常处理器
 * SysException
 * RuntimeException
 * Exception
 *
 * 它是一个Controller增强器,可对controller中被 @RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
 * https://blog.csdn.net/qq_21492635/article/details/89381204
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 对于参数错误统一处理，负责就需要单个处理，如: ValidateController.delete
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleException(Exception e){
        ErrorDTO errorDTO=new ErrorDTO();
        errorDTO.setCode("500");
        errorDTO.setMessage("Exception");
        return JSONObject.toJSONString(errorDTO);
    }

    /**
     * 对于参数错误统一处理，负责就需要单个处理，如: ValidateController.delete
     * */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    String handleRuntimeException(Exception e){
        ErrorDTO errorDTO=new ErrorDTO();
        errorDTO.setCode("500");
        errorDTO.setMessage("RuntimeException");
        return JSONObject.toJSONString(errorDTO);
    }

    /**
     * 对于参数错误统一处理，负责就需要单个处理，如: ValidateController.delete
     * */
    @ExceptionHandler(SysException.class)
    @ResponseBody
    String handleSysException(Exception e){
        ErrorDTO errorDTO=new ErrorDTO();
        errorDTO.setCode("500");
        errorDTO.setMessage("SysException");
        return JSONObject.toJSONString(errorDTO);
    }
}