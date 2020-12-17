package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuySubmitChain;
import com.cmj.example.chain.impl.MerchantSubmitChain;
import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitChain;
import com.cmj.example.chain.impl.DefaultSubmitOrderChainInitializer;
import com.cmj.example.chain.service.ChainInitializer;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        DefaultSubmitOrderChainInitializer defaultSubmitOrderInterceptorInitializer = new DefaultSubmitOrderChainInitializer();
        ChainInitializer<AbstractDefaultOrderSubmitChain, DefaultSubmitOrderParamVo> initializer = defaultSubmitOrderInterceptorInitializer
                .addLast(new LimitBuySubmitChain())
                .addLast(new MerchantSubmitChain());
        DefaultSubmitOrderParamVo defaultSubmitOrderParamVo = new DefaultSubmitOrderParamVo();
        initializer.invoke(defaultSubmitOrderParamVo);
    }
}
