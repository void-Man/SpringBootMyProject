package com.cmj.example.tester;

import com.cmj.example.chain.impl.LimitBuyCheckInterceptor;
import com.cmj.example.chain.impl.MerchantCheckInterceptor;
import com.cmj.example.chain.impl.OrderCheckPipeline;
import com.cmj.example.chain.impl.ProductCheckInterceptor;
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
            public void intit(OrderCheckPipeline orderCheckPipeline) {
                orderCheckPipeline.addLast(new MerchantCheckInterceptor())
                        .addLast(new ProductCheckInterceptor())
                        .addLast(new LimitBuyCheckInterceptor());
            }
        };
        ResVo check = initializer.getHead().check();
    }

}
