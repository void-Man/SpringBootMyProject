package com.cmj.example.strategy;

import com.cmj.example.strategy.chain.FundDataImportChain;
import com.cmj.example.strategy.chain.FundTypeDataImportChain;
import com.cmj.example.strategy.reader.DataReader;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public class DefaultImportDataInitializer extends AbstractImportDataInitializer {

    // 需要一个包含路径和顺序的类

    /**
     * chain的顺序
     *
     * @param reader
     * @return
     * @author mengjie_chen
     * @date 2021/1/3
     */
    public DefaultImportDataInitializer(DataReader reader) {
        super.addChain(new FundTypeDataImportChain(reader))
                .addChain(new FundDataImportChain(reader));
    }
}
