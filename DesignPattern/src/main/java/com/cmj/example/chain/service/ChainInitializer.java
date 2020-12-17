package com.cmj.example.chain.service;

import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

public interface ChainInitializer<T extends OrderSubmitChain<E>, E extends SubmitOrderBaseParamVo> {

    ChainInitializer<T, E> addLast(T validator);

    OrderResultVo invoke(E param);

}
