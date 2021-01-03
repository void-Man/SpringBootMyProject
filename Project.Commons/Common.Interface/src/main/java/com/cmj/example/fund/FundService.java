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

    /**
     * 添加基金经理和关系信息
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2021/1/3
     */
    void addFundHasUser(String path);
}
