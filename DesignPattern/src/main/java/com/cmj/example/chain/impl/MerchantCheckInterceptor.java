package com.cmj.example.chain.impl;

import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class MerchantCheckInterceptor extends AbstractOrderCheckInterceptor {
    @Override
    protected ResVo checkParam() {
        System.out.println("into MerchantCheckInterceptor");
        return new ResVo();
    }
}
