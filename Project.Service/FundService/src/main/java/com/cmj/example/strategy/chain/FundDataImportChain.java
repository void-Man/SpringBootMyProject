package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.FundBase;
import com.cmj.example.base.FundBaseExample;
import com.cmj.example.base.FundTypeBase;
import com.cmj.example.base.FundTypeBaseExample;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundTypeBaseMapper;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description 基金信息导入处理类
 * @date 2021/1/2
 */
public class FundDataImportChain extends AbstractDataImportChain<FundBase> {
    private static final Logger logger = LoggerFactory.getLogger(FundDataImportChain.class);

    private final FundBaseMapper fundBaseMapper = SpringContextHolder.getBean(FundBaseMapper.class);
    private final FundTypeBaseMapper fundTypeBaseMapper = SpringContextHolder.getBean(FundTypeBaseMapper.class);

    public FundDataImportChain(DataReader reader) {
        super(reader);
    }

    @Override
    protected List<FundBase> parseFromTarget(String content) {
        // 查询出所有已存在的基金
        List<FundBase> allFund = fundBaseMapper.selectByExample(new FundBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        // 所有基金类型
        List<FundTypeBase> allTypeList = fundTypeBaseMapper.selectByExample(new FundTypeBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        try {
            JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");
            List<FundBase> fundBaseList = new ArrayList<>(10);
            for (int i = 0; i < diffList.size(); i++) {
                JSONObject diffVo = diffList.getJSONObject(i);
                String fundName = diffVo.getString("sname");
                String fundNumber = diffVo.getString("symbol");
                String fundType = diffVo.getString("jjlx");

                // 数据基金已存在，跳过
                if (allFund.stream().anyMatch(fundBase -> fundBase.getNumber().equals(fundNumber) && fundBase.getName().equals(fundName))) {
                    continue;
                }

                // 当前基金已经在保存列表中，跳过
                if (fundBaseList.stream().anyMatch(fundBase -> fundBase.getName().equals(fundName) && fundBase.getNumber().equals(fundNumber))) {
                    continue;
                }

                FundBase addBase = FundBase.builder()
                        .name(fundName)
                        .number(fundNumber)
                        .fundTypeId(allTypeList.stream()
                                .filter(fundTypeBase -> fundTypeBase.getName().equals(fundType))
                                .findFirst()
                                .orElse(FundTypeBase.builder().id(-1).build())
                                .getId())
                        .build();
                fundBaseList.add(addBase);
            }
            return fundBaseList;
        } catch (Exception e) {
            logger.error("FundDataImportChain.parseFromTarget.exception.is----->", e);
            throw new RuntimeException("基金数据转换异常");
        }
    }

    @Override
    public void saveData(List<FundBase> date) {
        if (CollectionUtils.isNotNullAndEmpty(date)) {
            CollectionUtils.subList(date, 200)
                    .forEach(fundBases -> fundBaseMapper.batchInsertSelective(fundBases, FundBase.Column.fundTypeId, FundBase.Column.name, FundBase.Column.number));
        }
    }
}
