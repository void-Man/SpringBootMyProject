package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.IndustrBase;
import com.cmj.example.base.IndustrBaseExample;
import com.cmj.example.base.StockBase;
import com.cmj.example.base.StockBaseExample;
import com.cmj.example.mapper.IndustrBaseMapper;
import com.cmj.example.mapper.StockBaseMapper;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.FileUtils;
import com.cmj.example.vo.IndustrVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class IndustrServiceImpl implements IndustrService {

    private final static Logger logger = LoggerFactory.getLogger(IndustrServiceImpl.class);

    @Autowired
    private IndustrBaseMapper industrBaseMapper;
    @Autowired
    private StockBaseMapper stockBaseMapper;

    @Override
    public void addIndustr(String path) throws IOException {
        String content = FileUtils.readFile(path);
        List<IndustrBase> industrBaseList = this.getAddBase(content);
        CollectionUtils.subList(industrBaseList, 200).forEach(industrBases -> industrBaseMapper.batchInsertSelective(industrBases, IndustrBase.Column.name));
    }

    @Override
    public void updateStock(String path) throws IOException {
        String content = FileUtils.readFile(path);
        List<IndustrVo> industrBaseList = this.getConvertInfo(content);
        List<StockBase> stockBaseList = stockBaseMapper.selectByExample(new StockBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        for (StockBase stockBase : stockBaseList) {
            for (IndustrVo industrVo : industrBaseList) {
                if (stockBase.getNumber().equals(industrVo.getStockNumber())) {
                    IndustrBase industrBase = industrBaseMapper.selectOneByExample(new IndustrBaseExample().createCriteria().andNameEqualTo(industrVo.getIndustrName()).example());
                    if (Objects.isNull(industrBase)) {
                        logger.error("行业 {} 不存在", industrVo.getIndustrName());
                        break;
                    }
                    StockBase updateBase = StockBase.builder()
                            .id(stockBase.getId())
                            .industryId(industrBase.getId())
                            .updateTime(new Date())
                            .build();
                    stockBaseMapper.updateByPrimaryKeySelective(updateBase);
                }
            }
        }
    }

    /**
     * 转换信息
     *
     * @param content
     * @return java.util.List<com.cmj.example.base.IndustrBase>
     * @author mengjie_chen
     * @date 2020/12/31
     */
    private List<IndustrVo> getConvertInfo(String content) {
        JSONArray diffList = JSONObject.parseObject(content).getJSONObject("data").getJSONArray("diff");
        List<IndustrVo> industrVoList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            IndustrVo industrVo = IndustrVo.IndustrVoBuilder.industrVo()
                    .industrName(diffVo.getString("f100"))
                    .stockNumber(diffVo.getString("f12"))
                    .build();
            industrVoList.add(industrVo);
        }
        return industrVoList;
    }

    /**
     * 获取保存信息
     *
     * @param content
     * @return java.util.List<com.cmj.example.base.IndustrBase>
     * @author mengjie_chen
     * @date 2020/12/31
     */
    private List<IndustrBase> getAddBase(String content) {
        JSONArray diffList = JSONObject.parseObject(content).getJSONObject("data").getJSONArray("diff");
        List<IndustrBase> industrBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            String name = diffVo.getString("f100");
            if (industrBaseList.stream().anyMatch(industrBase -> industrBase.getName().equals(name))) {
                continue;
            }
            IndustrBase industrBase = IndustrBase.builder()
                    .name(name)
                    .build();
            industrBaseList.add(industrBase);
        }
        return industrBaseList;
    }
}
