package com.cmj.example.chain.service;

import com.cmj.example.vo.ResVo;
import com.cmj.example.vo.SubmitOrderBaseParamVo;

public interface OrderInvokeHandler<T extends SubmitOrderBaseParamVo> {

    ResVo invoke(T param);

}
