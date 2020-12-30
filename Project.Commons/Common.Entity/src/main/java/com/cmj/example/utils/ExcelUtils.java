package com.cmj.example.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mengjie_chen
 * @description Excel工具类
 * @date 2020/12/30
 */
public class ExcelUtils {

    /**
     * 根据路径解析Excel
     *
     * @param path
     * @return java.util.List<org.apache.poi.ss.usermodel.Sheet>
     * @author mengjie_chen
     * @date 2020/12/30
     */
    public static List<Sheet> parseExcel(String path) throws Exception {
        File excel = new File(path);
        if (!excel.isFile() || !excel.exists()) {
            throw new RuntimeException("文件不存在！");
        }

        // .是特殊字符，需要转义！！！！！
        String[] split = excel.getName().split("\\.");
        Workbook wb;
        // 根据文件后缀（xls/xlsx）进行判断
        if ("xls".equals(split[1])) {
            FileInputStream fis = new FileInputStream(excel);
            wb = new HSSFWorkbook(fis);
        } else if ("xlsx".equals(split[1])) {
            wb = new XSSFWorkbook(excel);
        } else {
            throw new RuntimeException("文件格式不支持！");
        }

        //开始解析
        Iterator<Sheet> iterator = wb.sheetIterator();
        List<Sheet> sheetList = new ArrayList<>(10);
        while (iterator.hasNext()) {
            Sheet sheet = iterator.next();
            sheetList.add(sheet);
        }
        return sheetList;
    }

}
