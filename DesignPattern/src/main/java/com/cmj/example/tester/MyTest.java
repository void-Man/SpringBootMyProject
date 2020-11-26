package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuySubmitInterceptor;
import com.cmj.example.chain.impl.MerchantSubmitInterceptor;
import com.cmj.example.chain.impl.OrderCheckOrderSubmitPipeline;
import com.cmj.example.chain.impl.ProductSubmitInterceptor;
import com.cmj.example.components.OrderCheckInterceptorInitializer;
import com.cmj.example.vo.ResVo;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        OrderCheckInterceptorInitializer initializer = new OrderCheckInterceptorInitializer() {
            @Override
            public void intit(OrderCheckOrderSubmitPipeline orderCheckPipeline) {
                orderCheckPipeline.addLast(new MerchantSubmitInterceptor())
                        .addLast(new ProductSubmitInterceptor())
                        .addLast(new LimitBuySubmitInterceptor());
            }
        };
        ResVo check = initializer.getHead().check();
    }

}
