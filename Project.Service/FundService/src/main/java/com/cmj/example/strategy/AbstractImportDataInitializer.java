package com.cmj.example.strategy;


import com.cmj.example.strategy.chain.DataImportChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public abstract class AbstractImportDataInitializer<T extends DataImportChain> implements ImportDataInitializer {

    private List<T> chains = new ArrayList<>(10);

    @Override
    public void ImportData(String path) {
        for (DataImportChain chain : chains) {
            List insertData = chain.getInsertData(path);
            chain.saveData(insertData);
        }
    }

    /**
     * 添加处理器
     *
     * @param chain
     * @return void
     * @author mengjie_chen
     * @date 2021/1/3
     */
    public AbstractImportDataInitializer<T> add(T chain) {
        chains.add(chain);
        return this;
    }
}
