package com.cmj.example.fund;

import java.io.IOException;
import java.util.List;

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

    /**
     * 添加基金持仓明细
     *
     * @param pathList
     * @return void
     * @author mengjie_chen
     * @date 2021/1/4
     */
    void addFundEntry(List<String> pathList);

    /**
     * 更新基金成立时间
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/7
     */
    void updateFundCreateTime() throws IOException;

    /**
     * addTop10Stock
     *
     * @param fundNumber
     * @return void
     * @author mengjie_chen
     * @date 2021/3/6
     */
    void addTop10Stock(String fundNumber) throws IOException;
}
