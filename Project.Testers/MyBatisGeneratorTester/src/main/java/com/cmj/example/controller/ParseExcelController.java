package com.cmj.example.controller;

import com.cmj.example.base.FundEntryBase;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import com.cmj.example.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public String parseAndSave(HttpServletRequest request) throws Exception {

        //excel文件路径
        String excelPath = "C:\\Users\\Safendaga\\Downloads\\新建 Microsoft Excel 工作表.xlsx";

        List<Sheet> sheetList = ExcelUtils.parseExcel(excelPath);
        for (Sheet sheet : sheetList) {
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();

            List<FundEntryBase> fundEntryBaseList = new ArrayList<>(lastRowIndex - firstRowIndex);
            //遍历行
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                Row row = sheet.getRow(rIndex);
                if (Objects.isNull(row)) {
                    continue;
                }
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                FundEntryBase fundEntryBase = new FundEntryBase();

                //遍历列
                for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
                    Cell cell = row.getCell(cIndex);
                    if (Objects.isNull(cell)) {
                        continue;
                    }
                    if (cIndex == 2) {
                        fundEntryBase.setStockNamer(cell.toString());
                    }
                    if (cIndex == 6) {
                        fundEntryBase.setRate(new BigDecimal(cell.toString()).multiply(new BigDecimal("100")));
                    }
                }
                fundEntryBaseList.add(fundEntryBase);
            }
            fundEntryBaseMapper.batchInsertSelective(fundEntryBaseList, FundEntryBase.Column.stockNamer, FundEntryBase.Column.rate);
        }
        return "success";
    }


}
