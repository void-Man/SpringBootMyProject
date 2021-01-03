package com.cmj.example.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Controller
@RequestMapping("/fund")
public class FundController {

    private FundService fundService;

    @Autowired
    public void setFundService(FundService fundService) {
        this.fundService = fundService;
    }

    /**
     * 添加基金信息
     *
     * @param request
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/1
     */
    @PostMapping("/addFund")
    @ResponseBody
    public String addFund(HttpServletRequest request, String path) throws Exception {
        fundService.addFund(path);
        return "{}";
    }

    /**
     * 添加基金经理和关系信息
     *
     * @param request
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/1
     */
    @PostMapping("/addFundHasUser")
    @ResponseBody
    public String addFundHasUser(HttpServletRequest request, String path) throws Exception {
        fundService.addFundHasUser(path);
        return "{}";
    }

    /**
     * 添加基金持仓明细
     *
     * @param request
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/1
     */
    @PostMapping("/addFundEntry")
    @ResponseBody
    public String addFundEntry(HttpServletRequest request, String path) throws Exception {
        fundService.addFundHasUser(path);
        return "{}";
    }

}
