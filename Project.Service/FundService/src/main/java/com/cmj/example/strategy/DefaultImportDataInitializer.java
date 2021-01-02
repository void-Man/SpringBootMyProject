package com.cmj.example.strategy;

import com.cmj.example.strategy.chain.FundDataImportChain;
import com.cmj.example.strategy.chain.FundTypeDataImportChain;
import com.cmj.example.strategy.chain.FundUserDataImportChain;
import com.cmj.example.strategy.reader.DataReader;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public class DefaultImportDataInitializer extends AbstractImportDataInitializer {
    public DefaultImportDataInitializer(DataReader reader) {
        super.addChain(new FundTypeDataImportChain(reader))
                .addChain(new FundUserDataImportChain(reader))
                .addChain(new FundDataImportChain(reader));
    }
}
