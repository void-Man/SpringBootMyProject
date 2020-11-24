package com.cmj.example.chain.service;

public interface Pipeline<T> {

    Pipeline addLast(T t);

    T getHead();
}
