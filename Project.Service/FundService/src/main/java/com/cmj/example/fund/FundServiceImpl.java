package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.FundBase;
import com.cmj.example.base.StockBase;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundBaseMapper fundBaseMapper;
    @Autowired
    private FundEntryBaseMapper fundEntryBaseMapper;

    @Override
    public void addFund(String path) {

    }

    /**
     * 从文件中获取基金信息
     *
     * @param content
     * @return java.util.List<com.cmj.example.base.FundBase>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private List<FundBase> getFundByFile(String content) {
        JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");
        List<FundBase> fundBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            FundBase.builder()
                    .name(diffVo.getString("sname"))
                    .number(diffVo.getString("symbol"))
                    .build();
        }
        return fundBaseList;
    }
}
