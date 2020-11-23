package com.cmj.example.decorator.contract.impl;

import com.cmj.example.decorator.contract.service.IContractApproveStrategy;
import com.cmj.example.vo.ResVo;

/**
 * @author mengjie_chen
 * @description date 2020/11/19
 */
public abstract class AbstractContractApproveStrategy implements IContractApproveStrategy {

    protected final ResVo resVo = new ResVo();

    @Override
    public ResVo approve() {
        if (check().isSuccess()) {
            save();
        }
        return resVo;
    }

    /**
     * 检验参数
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/19
     */
    protected abstract ResVo check();

    /**
     * 保存数据
     *
     * @param
     * @return com.cmj.example.vo.ResVo
     * @author mengjie_chen
     * @date 2020/11/19
     */
    protected abstract void save();


}
