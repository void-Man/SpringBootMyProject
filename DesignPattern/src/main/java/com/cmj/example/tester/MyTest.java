package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuySubmitInterceptor;
import com.cmj.example.chain.impl.MerchantSubmitInterceptor;
import com.cmj.example.chain.impl.abs.pileline.AbstractOrderSubmitPipeline;
import com.cmj.example.chain.impl.ProductSubmitInterceptor;
import com.cmj.example.components.DefaultSubmitOrderInterceptorInitializer;
import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderContext;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        DefaultSubmitOrderInterceptorInitializer initializer = new DefaultSubmitOrderInterceptorInitializer() {
            @Override
            public void intit(AbstractOrderSubmitPipeline orderCheckPipeline) {
                orderCheckPipeline.addLast(new MerchantSubmitInterceptor())
                        .addLast(new ProductSubmitInterceptor())
                        .addLast(new LimitBuySubmitInterceptor());
            }
        };
        ResVo check = initializer.getHead().check(new SubmitOrderContext<>());

    }

}
