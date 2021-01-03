package com.cmj.example.fund;

import com.cmj.example.strategy.FundAndTypeImportDataInitializer;
import com.cmj.example.strategy.ImportDataInitializer;
import com.cmj.example.strategy.reader.JSONTextDataReader;
import org.springframework.stereotype.Service;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class FundServiceImpl implements FundService {

    @Override
    public void addFund(String path) {
        ImportDataInitializer initializer = new FundAndTypeImportDataInitializer(new JSONTextDataReader());
        initializer.ImportData(path);
    }
}
