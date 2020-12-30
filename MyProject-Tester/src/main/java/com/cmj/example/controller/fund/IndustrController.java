package com.cmj.example.controller.fund;

import com.cmj.example.fund.IndustrService;
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
@RequestMapping("/industr")
public class IndustrController {

    @Autowired
    private IndustrService industrService;

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
    public String addIndustr(HttpServletRequest request) {

        return null;
    }

}
