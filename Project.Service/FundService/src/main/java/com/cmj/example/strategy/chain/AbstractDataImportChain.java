package com.cmj.example.strategy.chain;

import com.cmj.example.strategy.reader.DataReader;

import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/1
 */
public abstract class AbstractDataImportChain<T> implements DataImportChain<T> {

    /**
     * 读取器
     */
    protected final DataReader reader;

    public AbstractDataImportChain(DataReader reader) {
        this.reader = reader;
    }

    @Override
    public List<T> getInsertData(String source) {
        String content = reader.read(source);
        List<T> data = this.parseFromTarget(content);
        return data;
    }

    /**
     * 解析数据
     *
     * @param content
     * @return java.util.List<T>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    protected abstract List<T> parseFromTarget(String content);
}
