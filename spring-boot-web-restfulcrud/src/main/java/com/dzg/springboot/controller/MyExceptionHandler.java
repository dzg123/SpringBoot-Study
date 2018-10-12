package com.dzg.springboot.controller;

import com.dzg.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
//    浏览器客户端繁华的都是json
//    @ResponseBody
////    @ExceptionHandler(UserNotExistException.class)
////    public Map<String, Object> handleException(Exception e) {
////        Map<String, Object> map = new HashMap<>();
////        map.put("code", "user.notexist");
////        map.put("message", e.getMessage());
////        return map;
////
////    }
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code", "user.notexist");
        map.put("message", "用户出错了");
        request.setAttribute("ext",map);
        return "forward:/error";

    }
}
