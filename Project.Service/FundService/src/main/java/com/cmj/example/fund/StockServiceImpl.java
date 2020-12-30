package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.StockBase;
import com.cmj.example.mapper.StockBaseMapper;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockBaseMapper stockBaseMapper;

    @Override
    public void addStock(String path) throws IOException {
        String content = FileUtils.readFile(path);
        List<StockBase> stockBaseList = this.convertVo(content);
//        CollectionUtils.subList(stockBaseList, 200)
//                .forEach(stockBases -> stockBaseMapper.batchInsertSelective(stockBases, StockBase.Column.name, StockBase.Column.number));
    }

    /**
     * 转换数据
     *
     * @param content
     * @return java.util.List<com.cmj.example.base.StockBase>
     * @author mengjie_chen
     * @date 2020/12/30
     */
    private List<StockBase> convertVo(String content) {
        JSONArray diffList = JSONObject.parseObject(content).getJSONObject("data").getJSONArray("diff");
        List<StockBase> stockBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            StockBase stockBase = StockBase.builder()
                    .number(diffVo.getString("f12"))
                    .name(diffVo.getString("f14"))
                    .build();
            stockBaseMapper.insertSelective(stockBase);
            stockBaseList.add(stockBase);
        }
        return stockBaseList;
    }
}
