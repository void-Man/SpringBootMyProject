package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuySubmitInterceptor;
import com.cmj.example.chain.impl.MerchantSubmitInterceptor;
import com.cmj.example.chain.impl.abs.interceptor.AbstractDefaultOrderSubmitInterceptor;
import com.cmj.example.components.AbstractInterceptorInitializer;
import com.cmj.example.components.DefaultSubmitOrderInterceptorInitializer;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        DefaultSubmitOrderInterceptorInitializer defaultSubmitOrderInterceptorInitializer = new DefaultSubmitOrderInterceptorInitializer();
        AbstractInterceptorInitializer<DefaultSubmitOrderParamVo, AbstractDefaultOrderSubmitInterceptor> initializer = defaultSubmitOrderInterceptorInitializer
                .addLast(new LimitBuySubmitInterceptor())
                .addLast(new MerchantSubmitInterceptor());
        initializer.invoke(new DefaultSubmitOrderParamVo());
    }
}
