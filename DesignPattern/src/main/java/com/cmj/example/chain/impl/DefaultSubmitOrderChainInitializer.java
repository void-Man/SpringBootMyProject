package com.cmj.example.chain.impl;

import com.cmj.example.chain.impl.abs.initializer.AbstractChainInitializer;
import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitChain;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class DefaultSubmitOrderChainInitializer extends AbstractChainInitializer<AbstractDefaultOrderSubmitChain, DefaultSubmitOrderParamVo> {
    public static final Logger logger = LoggerFactory.getLogger(DefaultSubmitOrderChainInitializer.class);

    @Override
    public OrderResultVo invoke(DefaultSubmitOrderParamVo paramVo) {
        logger.info("into DefaultSubmitOrderInterceptorInitializer.invoke()");
        return super.invoke(paramVo);
    }

}
