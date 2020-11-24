package com.cmj.example.components;

import com.cmj.example.chain.service.Pipeline;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractInterceptorInitializer<T extends Pipeline>{
    protected AbstractInterceptorInitializer() {
        T pipeLine = getPipeLine();
        intit(pipeLine);
    }

    public abstract void intit(T t);

    protected abstract T getPipeLine();

}
