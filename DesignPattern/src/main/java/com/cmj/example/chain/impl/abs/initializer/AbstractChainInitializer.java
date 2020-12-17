package com.cmj.example.chain.impl.abs.initializer;

import com.cmj.example.chain.service.ChainInitializer;
import com.cmj.example.chain.service.OrderInvokeHandler;
import com.cmj.example.chain.service.OrderSubmitChain;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public abstract class AbstractChainInitializer<T extends OrderSubmitChain<E>, E extends SubmitOrderBaseParamVo> implements ChainInitializer<T, E> {
    private final List<T> validatorList = new ArrayList<>(10);

    @Override
    public ChainInitializer<T, E> addLast(T validator) {
        validatorList.add(validator);
        return this;
    }

    /**
     * 检验参数
     *
     * @param param
     * @return void
     * @author mengjie_chen
     * @date 2020/12/17
     */
    private void validate(E param) {
        for (T validator : validatorList) {
            validator.check(param);
        }
    }

    @Override
    public OrderResultVo invoke(E param) {
        this.validate(param);
        for (T validator : validatorList) {
            OrderInvokeHandler<E> handler = validator.create();
            handler.handle(param);
        }
        return new OrderResultVo();
    }

}
