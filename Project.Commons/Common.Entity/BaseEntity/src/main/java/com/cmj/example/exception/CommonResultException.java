package com.cmj.example.exception;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/4
 */
public class CommonResultException extends RuntimeException{
    private int code;

    public CommonResultException(String message) {
        super(message);
    }

    public CommonResultException() {
    }

    public int getCode() {
        return code;
    }
}
