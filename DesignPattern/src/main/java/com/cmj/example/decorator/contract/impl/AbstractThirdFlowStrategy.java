package com.cmj.example.decorator.contract.impl;

import com.cmj.example.decorator.contract.service.IThirdFlowStrategy;
import com.cmj.example.vo.ContractApproveBaseParamVo;
import com.cmj.example.vo.ResVo;

public abstract class AbstractThirdFlowStrategy implements IThirdFlowStrategy {

    protected final ContractApproveBaseParamVo contractApproveBaseParamVo;

    protected AbstractThirdFlowStrategy(ContractApproveBaseParamVo contractApproveBaseParamVo) {
        this.contractApproveBaseParamVo = contractApproveBaseParamVo;
    }

    @Override
    public ResVo doFlow() {
        requestParam();
        return doInvoke();
    }

    /**
     * 参数组装
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/19
     */
    protected abstract void requestParam();

    /**
     * 执行流程
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/19
     */
    protected abstract ResVo doInvoke();
}
