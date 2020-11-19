package com.cmj.example.impl.contract;

import com.cmj.example.service.contract.IThirdFlowStrategy;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description 审批流与审批逻辑桥接类
 * @date 2020/11/20
 */
public abstract class AbstractThirdApproveStrategy extends AbstractContractApproveStrategy implements IThirdFlowStrategy {

    @Override
    public ResVo approve() {
        return super.approve();
    }
}
