package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.IOrderCheckInterceptor;
import com.cmj.example.vo.ResVo;

import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderCheckInterceptor implements IOrderCheckInterceptor {

    private IOrderCheckInterceptor next;
    protected ResVo resVo = new ResVo();

    @Override
    public ResVo check() {
        resVo = this.checkParam();
        if (resVo.isSuccess() && Objects.nonNull(next)) {
            resVo = next.check();
        }
        return resVo;
    }

    protected abstract ResVo checkParam();

    public void setNext(IOrderCheckInterceptor next) {
        this.next = next;
    }
}
