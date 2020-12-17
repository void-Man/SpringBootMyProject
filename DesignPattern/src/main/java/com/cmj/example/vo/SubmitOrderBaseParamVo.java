package com.cmj.example.vo;

import java.util.List;

/**
 * @author mengjie_chen
 * @description 提交订单基础传参
 * @date 2020/11/28
 */
public class SubmitOrderBaseParamVo {

     private EbuyOrderBase ebuyOrderBase;
     private List<EbuyOrderHasProductBase> ebuyOrderHasProductBaseList;
    // 地址base


    public EbuyOrderBase getEbuyOrderBase() {
        return ebuyOrderBase;
    }

    public void setEbuyOrderBase(EbuyOrderBase ebuyOrderBase) {
        this.ebuyOrderBase = ebuyOrderBase;
    }

    public List<EbuyOrderHasProductBase> getEbuyOrderHasProductBaseList() {
        return ebuyOrderHasProductBaseList;
    }

    public void setEbuyOrderHasProductBaseList(List<EbuyOrderHasProductBase> ebuyOrderHasProductBaseList) {
        this.ebuyOrderHasProductBaseList = ebuyOrderHasProductBaseList;
    }
}
