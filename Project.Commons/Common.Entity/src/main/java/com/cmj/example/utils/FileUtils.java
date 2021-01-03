package com.cmj.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String strLine;
        while (null != (strLine = bufferedReader.readLine())) {
            stringBuilder.append(strLine);
        }
//        logger.info("com.cmj.example.utils.FileUtils.readFile.content.is----->{}", stringBuilder.toString());
        return stringBuilder.toString();
    }

}
