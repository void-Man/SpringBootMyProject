package com.cmj.example.strategy.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/2
 */
public class UrlResultDataReader extends AbstractDataReader {

    private static final Logger logger = LoggerFactory.getLogger(UrlResultDataReader.class);

    @Override
    public String read(String source) {
        return source;
    }
}
