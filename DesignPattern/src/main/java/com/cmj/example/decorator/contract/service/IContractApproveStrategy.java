package com.cmj.example.decorator.contract.service;

import com.cmj.example.vo.ResVo;

public interface IContractApproveStrategy {

    /**
     * 审批合同
     * @author mengjie_chen
     * @date 2020/11/19
     * @param
     * @return com.cmj.example.vo.ResVo
     */
    ResVo approve();

}
