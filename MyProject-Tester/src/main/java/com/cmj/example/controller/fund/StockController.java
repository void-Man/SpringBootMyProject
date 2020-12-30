package com.cmj.example.controller.fund;

import com.cmj.example.fund.StockService;
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

    @Autowired
    private StockService stockService;

    @PostMapping("/addStock")
    @ResponseBody
    public String addStock(HttpServletRequest request, String path) throws IOException {
        stockService.addStock(path);
        return "{}";
    }
}
