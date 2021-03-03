package com.cmj.example.fund;

import com.cmj.example.fund.IndustrService;
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
@RequestMapping("/industr")
public class IndustrController {

    private IndustrService industrService;

    @Autowired
    public void setIndustrService(IndustrService industrService) {
        this.industrService = industrService;
    }

    /**
     * 新增行业
     *
     * @param request
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/12/30
     */
    @PostMapping("/addIndustr")
    @ResponseBody
    public String addIndustr(HttpServletRequest request, String path) throws IOException {
        industrService.addIndustr(path);
        return "{}";
    }

    /**
     * 更新股票行业信息
     *
     * @param request
     * @param path
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/12/31
     */
    @PostMapping("/updateStock")
    @ResponseBody
    public String updateStock(HttpServletRequest request, String path) throws IOException {
        industrService.updateStock(path);
        return "{}";
    }
}
