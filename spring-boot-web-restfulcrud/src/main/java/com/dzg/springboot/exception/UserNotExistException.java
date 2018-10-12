package com.dzg.springboot.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("用户名不存在");
    }
}
