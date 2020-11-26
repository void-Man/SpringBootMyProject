package com.cmj.example.chain.service;

import com.cmj.example.vo.ResVo;

import java.util.List;

public interface IOrderSubmitInterceptor {

    ResVo check();

    List<IOrderInvokeHandle> getHandleList();
}
