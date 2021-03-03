package com.cmj.example.fund;

import java.io.IOException;

public interface StockService {
    /**
     * 根据路径保存股票信息
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2020/12/30
     */
    void addStock(String path) throws IOException;
}
