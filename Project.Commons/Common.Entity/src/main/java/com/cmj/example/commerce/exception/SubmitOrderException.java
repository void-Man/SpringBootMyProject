package com.cmj.example.commerce.exception;

/**
 * @author mengjie_chen
 * @description date 2020/12/4
 */
public class SubmitOrderException extends RuntimeException{

    private int code;

    public SubmitOrderException(String message) {
        super(message);
    }

    public SubmitOrderException() {
    }

    public int getCode() {
        return code;
    }
}
