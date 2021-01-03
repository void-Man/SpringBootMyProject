package com.cmj.example.strategy;

import com.cmj.example.strategy.chain.FundDataImportChain;
import com.cmj.example.strategy.chain.FundTypeDataImportChain;
import com.cmj.example.strategy.reader.DataReader;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public class FundAndTypeImportDataInitializer extends AbstractImportDataInitializer {

    /**
     * chain的顺序
     *
     * @param reader
     * @return
     * @author mengjie_chen
     * @date 2021/1/3
     */
    public FundAndTypeImportDataInitializer(DataReader reader) {
        super.addChain(new FundTypeDataImportChain(reader))
                .addChain(new FundDataImportChain(reader));
    }
}
