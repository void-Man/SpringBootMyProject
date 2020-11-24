package com.cmj.example.chain.service;

import com.cmj.example.chain.impl.AbstractOrderCheckInterceptor;

public interface Pipeline<T> {

    Pipeline addLast(T t);

}
