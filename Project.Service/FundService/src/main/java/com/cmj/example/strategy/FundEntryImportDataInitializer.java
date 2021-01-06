package com.cmj.example.strategy;

import com.cmj.example.strategy.chain.FundEntryImportChain;
import com.cmj.example.strategy.chain.FundHasFundUserDataImportChain;
import com.cmj.example.strategy.chain.FundUserDataImportChain;
import com.cmj.example.strategy.reader.DataReader;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public class FundEntryImportDataInitializer extends AbstractImportDataInitializer {

    /**
     * chain的顺序
     *
     * @param reader
     * @return
     * @author mengjie_chen
     * @date 2021/1/3
     */
    public FundEntryImportDataInitializer(DataReader reader) {
        super.addChain(new FundEntryImportChain(reader));
    }
}
