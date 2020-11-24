package com.cmj.example.tester;

import com.cmj.example.chain.impl.OrderCheckPipeline;
import com.cmj.example.components.OrderCheckInterceptorInitializer;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MyTest {

    @Test
    public void test1() {
        new OrderCheckInterceptorInitializer() {
            @Override
            public void intit(OrderCheckPipeline orderCheckPipeline) {
//                orderCheckPipeline.addLast()
            }
        };
    }

}
