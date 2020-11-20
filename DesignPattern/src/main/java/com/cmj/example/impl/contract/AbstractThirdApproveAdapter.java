package com.cmj.example.impl.contract;

import com.cmj.example.service.contract.IContractApproveStrategy;
import com.cmj.example.service.contract.IThirdFlowStrategy;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description 审批流与审批逻辑桥接类
 * @date 2020/11/20
 */
public abstract class AbstractThirdApproveAdapter implements IContractApproveStrategy {

    protected final IThirdFlowStrategy thirdFlowStrategy;
    protected final AbstractContractApproveStrategy contractApproveStrategy;
    protected ResVo resVo = new ResVo();

    protected AbstractThirdApproveAdapter(IThirdFlowStrategy thirdFlowStrategy, AbstractContractApproveStrategy contractApproveStrategy) {
        this.thirdFlowStrategy = thirdFlowStrategy;
        this.contractApproveStrategy = contractApproveStrategy;
    }

    @Override
    public ResVo approve() {
        if (localCheck().isSuccess()&& doFlow().isSuccess()){
            contractApproveStrategy.save();
        }
        return resVo;
    }

    protected ResVo localCheck() {
        resVo = contractApproveStrategy.check();
        return resVo;
    }

    protected ResVo doFlow(){
        resVo = thirdFlowStrategy.doFlow();
        return resVo;
    }
}
