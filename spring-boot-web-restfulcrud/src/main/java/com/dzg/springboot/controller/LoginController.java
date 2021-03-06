package com.dzg.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
//@RequestMapping("/user")
public class LoginController {
    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username" )String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session) {
    if(!StringUtils.isEmpty(username) && "123456".equals(password)){
        session.setAttribute("loginUser",username);
//        防止表单重复提交，重定向到主页
        return "redirect:/main.html";
    }else {
        map.put("msg", "用户名或密码错误");
        return "login";
    }

    }
}
