package com.cmj.example;

import com.cmj.example.vo.EbuyProductForBuy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description date 2020/12/3
 */
public class StreamReduceTester {

    @Test
    public void test(){
        List<EbuyProductForBuy> ebuyProductForBuyList = new ArrayList<>(10);
        ebuyProductForBuyList.add(EbuyProductForBuy.EbuyProductForBuyBuilder.ebuyProductForBuy().price(new BigDecimal("10")).buyQty(1).build());
        ebuyProductForBuyList.add(EbuyProductForBuy.EbuyProductForBuyBuilder.ebuyProductForBuy().price(new BigDecimal("20")).buyQty(2).build());
        System.out.println(ebuyProductForBuyList.stream().reduce(BigDecimal.ZERO, (init, vo) -> init.add(vo.getPrice().multiply(new BigDecimal(vo.getBuyQty()))), BigDecimal::add));;
    }

}
