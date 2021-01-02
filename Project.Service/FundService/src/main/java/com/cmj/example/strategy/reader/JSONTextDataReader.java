package com.cmj.example.strategy.reader;

import com.cmj.example.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/2
 */
public class JSONTextDataReader extends AbstractDataReader {

    private static final Logger logger = LoggerFactory.getLogger(JSONTextDataReader.class);

    @Override
    public String read(String source) {
        try {
            String file = FileUtils.readFile(source);
            return file;
        } catch (IOException e) {
            logger.error("JSONTextDataReader.read失败----->", e);
            throw new RuntimeException("SONTextDataReader.read失败");
        }
    }
}
