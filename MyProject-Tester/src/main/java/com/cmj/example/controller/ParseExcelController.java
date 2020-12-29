package com.cmj.example.controller;

import com.cmj.example.base.FundEntryBase;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Controller
@RequestMapping("/parseExcel")
public class ParseExcelController {

    @Autowired
    private FundBaseMapper fundBaseMapper;
    @Autowired
    private FundEntryBaseMapper fundEntryBaseMapper;


    @PostMapping("/parseAndSave")
    @ResponseBody
    public String parseAndSave(HttpServletRequest request) {

        //excel文件路径
        String excelPath = "C:\\Users\\Safendaga\\Downloads\\新建 Microsoft Excel 工作表.xlsx";

        try {
            request.setCharacterEncoding("UTF-8");
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    return "faild";
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum();   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();

                List<FundEntryBase> fundEntryBaseList = new ArrayList<>(10);
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        FundEntryBase fundEntryBase = new FundEntryBase();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cIndex == 2) {
                                fundEntryBase.setStockNamer(cell.toString());
                            }
                            if (cIndex == 6) {
                                fundEntryBase.setRate(new BigDecimal(cell.toString()).multiply(new BigDecimal("100")));
                            }
                        }
                        fundEntryBaseList.add(fundEntryBase);
                    }
                }
                fundEntryBaseMapper.batchInsertSelective(fundEntryBaseList, FundEntryBase.Column.stockNamer, FundEntryBase.Column.rate);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
