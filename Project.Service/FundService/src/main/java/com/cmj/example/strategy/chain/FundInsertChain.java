package com.cmj.example.strategy.chain;

import java.util.List;

public interface FundInsertChain<T> {

    /**
     * 获取需要保存的数据
     *
     * @param source
     * @return java.util.List<T>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    List<T> getInsertData(String source);

    /**
     * 保存数据
     *
     * @param date
     * @return void
     * @author mengjie_chen
     * @date 2021/1/1
     */
    void saveData(List<T> date);

}
