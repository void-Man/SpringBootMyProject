package com.cmj.example.vo;

import com.cmj.example.vo.EbuyDeliveryOrderVo;
import com.cmj.example.vo.EbuyOrderHasProductVo;
import com.cmj.example.vo.EbuyOrderVo;

import java.util.List;

/**
 * @author mengjie_chen
 * @description 提交订单上下文类
 * @date 2020/11/28
 */
public class SubmitOrderContext<T extends SubmitOrderBaseParamVo> {

    /**
     * 订单信息
     */
    private EbuyOrderVo ebuyOrderVo;
    /**
     * 订单商品信息
     */
    private List<EbuyOrderHasProductVo> ebuyOrderHasProductVoList;
    /**
     * 运单信息
     */
    private EbuyDeliveryOrderVo ebuyDeliveryOrderVo;


    private T param;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public EbuyOrderVo getEbuyOrderVo() {
        return ebuyOrderVo;
    }

    public void setEbuyOrderVo(EbuyOrderVo ebuyOrderVo) {
        this.ebuyOrderVo = ebuyOrderVo;
    }

    public List<EbuyOrderHasProductVo> getEbuyOrderHasProductVoList() {
        return ebuyOrderHasProductVoList;
    }

    public void setEbuyOrderHasProductVoList(List<EbuyOrderHasProductVo> ebuyOrderHasProductVoList) {
        this.ebuyOrderHasProductVoList = ebuyOrderHasProductVoList;
    }

    public EbuyDeliveryOrderVo getEbuyDeliveryOrderVo() {
        return ebuyDeliveryOrderVo;
    }

    public void setEbuyDeliveryOrderVo(EbuyDeliveryOrderVo ebuyDeliveryOrderVo) {
        this.ebuyDeliveryOrderVo = ebuyDeliveryOrderVo;
    }
}
