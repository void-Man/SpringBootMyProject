package com.cmj.example.chain.service;

import com.cmj.example.chain.impl.AbstractOrderCheckInterceptor;

public interface OrderPipeline {

    OrderPipeline addLast(AbstractOrderCheckInterceptor orderCheckInterceptor);

}
