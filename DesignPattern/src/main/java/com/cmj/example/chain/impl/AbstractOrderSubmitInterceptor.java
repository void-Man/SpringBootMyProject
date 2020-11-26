package com.cmj.example.chain.impl;

import com.cmj.example.chain.service.IOrderInvokeHandle;
import com.cmj.example.chain.service.IOrderSubmitInterceptor;
import com.cmj.example.vo.ResVo;

import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/24
 */
public abstract class AbstractOrderSubmitInterceptor implements IOrderSubmitInterceptor {

    private IOrderSubmitInterceptor next;
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

    public void setNext(IOrderSubmitInterceptor next) {
        this.next = next;
    }

    @Override
    public List<IOrderInvokeHandle> getHandleList() {
        return null;
    }
}
