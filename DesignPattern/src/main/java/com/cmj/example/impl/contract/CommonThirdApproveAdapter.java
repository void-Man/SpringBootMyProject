package com.cmj.example.impl.contract;

import com.cmj.example.service.contract.IThirdFlowStrategy;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/20
 */
public class CommonThirdApproveAdapter extends AbstractThirdApproveAdapter{
    public CommonThirdApproveAdapter(IThirdFlowStrategy thirdFlowStrategy, AbstractContractApproveStrategy contractApproveStrategy) {
        super(thirdFlowStrategy, contractApproveStrategy);
    }

    @Override
    public ResVo approve() {
        return super.approve();
    }
}
