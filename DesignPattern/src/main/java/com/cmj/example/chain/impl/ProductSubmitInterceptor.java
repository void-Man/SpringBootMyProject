package com.cmj.example.chain.impl;

import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/24
 */
public class ProductSubmitInterceptor extends AbstractOrderSubmitInterceptor {
    @Override
    protected ResVo checkParam() {
        System.out.println("into ProductCheckInterceptor");
        resVo.setSuccess(false);
        return resVo;
    }
}
