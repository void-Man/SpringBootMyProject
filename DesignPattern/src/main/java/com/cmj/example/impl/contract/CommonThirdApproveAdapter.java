package com.cmj.example.impl.contract;

import com.cmj.example.service.contract.IThirdFlowStrategy;
import com.cmj.example.vo.ResVo;
import org.apache.log4j.Logger;

/**
 * @author mengjie_chen
 * @description date 2020/11/20
 */
public class CommonThirdApproveAdapter extends AbstractThirdApproveAdapter {

    private static final Logger logger = Logger.getLogger(CommonThirdApproveAdapter.class);

    public CommonThirdApproveAdapter(IThirdFlowStrategy thirdFlowStrategy, AbstractContractApproveStrategy contractApproveStrategy) {
        super(thirdFlowStrategy, contractApproveStrategy);
        logger.info("into CommonThirdApproveAdapter");
    }

    @Override
    public ResVo approve() {
        return super.approve();
    }
}
