package com.cmj.example.fund;

import java.io.IOException;

public interface IndustrService {
    /**
     * 添加行业信息
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2020/12/31
     */
    void addIndustr(String path) throws IOException;

    /**
     * 更新股票行业信息
     *
     * @param path
     * @return void
     * @author mengjie_chen
     * @date 2020/12/31
     */
    void updateStock(String path) throws IOException;
}
