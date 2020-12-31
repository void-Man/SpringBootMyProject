package com.cmj.example.fund;

import java.io.IOException;

public interface FundService {
    /**
     * 添加基金信息
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2021/1/1
     */
    void addFund(String path) throws IOException;
}
