package com.cmj.example;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author mengjie_chen
 * @description date 2020/12/14
 */
public class MathTest {

    @Test
    public void bigDecimal2IntTest(){
        System.out.println(new BigDecimal("1.156").intValue());
        System.out.println(new BigDecimal("1.356").intValue());
        System.out.println(new BigDecimal("1.456").intValue());
        System.out.println(new BigDecimal("1.556").intValue());
        System.out.println(new BigDecimal("1.656").intValue());
        System.out.println(new BigDecimal("1.956").intValue());
    }

}
