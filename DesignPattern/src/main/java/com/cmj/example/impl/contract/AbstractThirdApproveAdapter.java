package com.cmj.example.impl.contract;

import com.cmj.example.service.contract.IThirdFlowStrategy;
import com.cmj.example.vo.ContractApproveBaseParamVo;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description 审批流与审批逻辑桥接类
 * @date 2020/11/20
 */
public abstract class AbstractThirdApproveAdapter extends AbstractContractApproveStrategy {

    protected final ContractApproveBaseParamVo contractApproveBaseParamVo;
    protected final IThirdFlowStrategy thirdFlowStrategy;

    protected AbstractThirdApproveAdapter(ContractApproveBaseParamVo contractApproveBaseParamVo, IThirdFlowStrategy thirdFlowStrategy) {
        this.contractApproveBaseParamVo = contractApproveBaseParamVo;
        this.thirdFlowStrategy = thirdFlowStrategy;
    }

    @Override
    public ResVo approve() {
        if (check().isSuccess() && thirdFlowStrategy.doFlow().isSuccess()) {
            save();
        }
        return resVo;
    }

    @Override
    protected ResVo check() {
        return null;
    }

    @Override
    protected void save() {

    }
}
