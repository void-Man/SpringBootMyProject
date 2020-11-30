package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuySubmitInterceptor;
import com.cmj.example.chain.impl.MerchantSubmitInterceptor;
import com.cmj.example.chain.impl.ProductSubmitInterceptor;
import com.cmj.example.chain.impl.abs.interceptor.AbstractOrderSubmitInterceptor;
import com.cmj.example.chain.impl.abs.pileline.DefaultOrderSubmitPipeline;
import com.cmj.example.chain.service.OrderInvokeHandle;
import com.cmj.example.components.DefaultSubmitOrderInterceptorInitializer;
import com.cmj.example.vo.DefaultSubmitOrderParamVo;
import com.cmj.example.vo.OrderResultVo;
import com.cmj.example.vo.SubmitOrderContext;
import org.junit.Test;

import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        DefaultSubmitOrderInterceptorInitializer initializer = new DefaultSubmitOrderInterceptorInitializer() {
            @Override
            public void intit(DefaultOrderSubmitPipeline orderCheckPipeline) {
                orderCheckPipeline.addLast(new MerchantSubmitInterceptor())
                        .addLast(new ProductSubmitInterceptor())
                        .addLast(new LimitBuySubmitInterceptor());
            }
        };
        AbstractOrderSubmitInterceptor<DefaultSubmitOrderParamVo> head = initializer.getHead();
        OrderResultVo check = head.check(new SubmitOrderContext<>());
        List<OrderInvokeHandle> handles = head.getHandles();

    }

}
