package com.cmj.example.strategy;


import com.cmj.example.strategy.chain.DataImportChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public abstract class AbstractImportDataInitializer implements ImportDataInitializer {

    private List<DataImportChain<?>> chains = new ArrayList<>(10);

    @Override
    public void ImportData(String path) {
        for (DataImportChain<?> chain : chains) {
            List insertData = chain.getInsertData(path);
            chain.saveData(insertData);
        }
    }

    /**
     * 添加处理器
     *
     * @param chain
     * @return com.cmj.example.strategy.ImportDataInitializer
     * @author mengjie_chen
     * @date 2021/1/3
     */
    protected AbstractImportDataInitializer addChain(DataImportChain<?> chain) {
        chains.add(chain);
        return this;
    }
}
