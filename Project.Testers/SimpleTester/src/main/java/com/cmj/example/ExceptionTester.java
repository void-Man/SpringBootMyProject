package com.cmj.example;

import com.cmj.example.exception.CommonResultException;
import org.junit.Test;

/**
 * @author mengjie_chen
 * @description date 2020/12/4
 */
public class ExceptionTester {

    @Test
    public void test() {
        try {
            System.out.println(111);
            test01();
        } catch (CommonResultException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

    private void test01() {
        throw new CommonResultException("提交订单失败");
    }

}
