package com.cmj.example.fund;

import com.cmj.example.mapper.StockBaseMapper;
import com.cmj.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
public class StockServiceImpl implements StockService{

    @Autowired
    private StockBaseMapper stockBaseMapper;

    @Override
    public void addStock(String path) throws IOException {
        String content = FileUtils.readFile(path);
    }
}
