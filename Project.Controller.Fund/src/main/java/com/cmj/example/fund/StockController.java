package com.cmj.example.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * 新增股票
     *
     * @param request
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/12/31
     */
    @PostMapping("/addStock")
    @ResponseBody
    public String addStock(HttpServletRequest request, String path) throws IOException {
        stockService.addStock(path);
        return "{}";
    }
}
