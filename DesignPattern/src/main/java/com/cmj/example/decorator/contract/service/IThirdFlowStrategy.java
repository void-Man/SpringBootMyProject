package com.cmj.example.decorator.contract.service;

import com.cmj.example.vo.ResVo;

public interface IThirdFlowStrategy {

    /**
     * 审批流操作
     * @author mengjie_chen
     * @date 2020/11/19
     * @param
     * @return com.cmj.example.vo.ResVo
     */
    ResVo doFlow();

}
