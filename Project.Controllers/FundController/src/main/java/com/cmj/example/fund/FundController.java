package com.cmj.example.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String addFundHasUser(HttpServletRequest request, String path) {
        fundService.addFundHasUser(path);
        return "{}";
    }

    /**
     * 添加基金持仓明细
     *
     * @param request
     * @param paths
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/1
     */
    @PostMapping("/addFundEntry")
    @ResponseBody
    public String addFundEntry(HttpServletRequest request, String paths) {
        List<String> pathList = Stream.of(paths.split(",")).collect(Collectors.toList());
        fundService.addFundEntry(pathList);
        return "{}";
    }

    /**
     * 更新基金成立时间
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/7
     */
    @PostMapping("/updateFundCreateTime")
    @ResponseBody
    public String updateFundCreateTime() throws IOException {
        fundService.updateFundCreateTime();
        return "{}";
    }

}
