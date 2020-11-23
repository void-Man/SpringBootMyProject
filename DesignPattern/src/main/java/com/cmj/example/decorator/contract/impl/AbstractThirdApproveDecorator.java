package com.cmj.example.decorator.contract.impl;

import com.cmj.example.decorator.contract.service.IContractApproveStrategy;
import com.cmj.example.decorator.contract.service.IThirdFlowStrategy;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description 审批流与审批逻辑桥接类
 * @date 2020/11/20
 */
public abstract class AbstractThirdApproveDecorator implements IContractApproveStrategy {

    protected final IThirdFlowStrategy thirdFlowStrategy;
    protected final AbstractContractApproveStrategy contractApproveStrategy;
    protected ResVo resVo = new ResVo();

    protected AbstractThirdApproveDecorator(IThirdFlowStrategy thirdFlowStrategy, AbstractContractApproveStrategy contractApproveStrategy) {
        this.thirdFlowStrategy = thirdFlowStrategy;
        this.contractApproveStrategy = contractApproveStrategy;
    }

    @Override
    public final ResVo approve() {
        if (localCheck().isSuccess() && doFlow().isSuccess()) {
            contractApproveStrategy.save();
        }
        return resVo;
    }

    /**
     * 本地校验
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/20
     */
    protected ResVo localCheck() {
        resVo = contractApproveStrategy.check();
        return resVo;
    }

    /**
     * 调用审批流接口
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/20
     */
    protected ResVo doFlow() {
        resVo = thirdFlowStrategy.doFlow();
        return resVo;
    }
}
