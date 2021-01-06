package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.*;
import com.cmj.example.fund.FundEntryVo;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import com.cmj.example.mapper.FundTypeBaseMapper;
import com.cmj.example.mapper.StockBaseMapper;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description 基金持仓明细导入处理类
 * @date 2021/1/2
 */
public class FundEntryImportChain extends AbstractDataImportChain<FundEntryBase> {
    private static final Logger logger = LoggerFactory.getLogger(FundEntryImportChain.class);

    private final FundBaseMapper fundBaseMapper = SpringContextHolder.getBean(FundBaseMapper.class);
    private final FundTypeBaseMapper fundTypeBaseMapper = SpringContextHolder.getBean(FundTypeBaseMapper.class);
    private final FundEntryBaseMapper fundEntryBaseMapper = SpringContextHolder.getBean(FundEntryBaseMapper.class);
    private final StockBaseMapper stockBaseMapper = SpringContextHolder.getBean(StockBaseMapper.class);

    public FundEntryImportChain(DataReader reader) {
        super(reader);
    }

    @Override
    protected List<FundEntryBase> parseFromTarget(String content) {
        // 查询出所有已存在的基金
        List<FundBase> allFund = fundBaseMapper.selectByExample(new FundBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        // 所有基金类型
        List<FundTypeBase> allTypeList = fundTypeBaseMapper.selectByExample(new FundTypeBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        try {
            JSONArray diffList = JSONObject.parseObject(content).getJSONObject("rsm").getJSONArray("data");
            List<FundBase> fundBaseList = new ArrayList<>(10);
            List<FundEntryVo> fundEntryVoList = new ArrayList<>(10);
            for (int i = 0; i < diffList.size(); i++) {
                JSONObject diffVo = diffList.getJSONObject(i);
                String createTime = diffVo.getString("date");
                String stockNumber = diffVo.getJSONObject("items").getString("symbol");
                String stockName = diffVo.getJSONObject("items").getString("name");
                Integer stockBuyQuantity = diffVo.getJSONObject("items").getInteger("sh_nums");
                BigDecimal stockBuyAmount = diffVo.getJSONObject("items").getBigDecimal("sh_marketvalue");
                String fundName = diffVo.getJSONObject("items").getString("sh_name");

                FundEntryVo fundEntryVo = FundEntryVo.FundEntryVoBuilder.fundEntryVo()
                        .createTime(createTime)
                        .stockNumber(stockNumber.substring(2))
                        .stockName(stockName)
                        .stockBuyQuantity(stockBuyQuantity)
                        .stockBuyAmount(stockBuyAmount)
                        .fundName(fundName)
                        .build();

                fundEntryVoList.add(fundEntryVo);
            }

            // 所有股票代码
            List<String> stockNumberList = fundEntryVoList.stream()
                    .map(FundEntryVo::getStockNumber)
                    .distinct()
                    .collect(Collectors.toList());
            // key：股票代码     value：base
            Map<String, Optional<StockBase>> stockNumberMap = stockBaseMapper.selectByExample(new StockBaseExample().createCriteria()
                    .andIsDeleteEqualTo(0)
                    .andNumberIn(stockNumberList)
                    .example())
                    .stream()
                    .collect(Collectors.groupingBy(StockBase::getName, Collectors.reducing((t, t2) -> t)));

            /*String fundName = diffVo.getString("sname");
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
                fundBaseList.add(addBase);*/
            return null;
        } catch (Exception e) {
            logger.error("FundDataImportChain.parseFromTarget.exception.is----->", e);
            throw new RuntimeException("基金数据转换异常");
        }
    }

    @Override
    public void saveData(List<FundEntryBase> date) {
//        if (CollectionUtils.isNotNullAndEmpty(date)) {
//            CollectionUtils.subList(date, 200)
//                    .forEach(fundBases -> fundBaseMapper.batchInsertSelective(fundBases, FundBase.Column.fundTypeId, FundBase.Column.name, FundBase.Column.number));
//        }
    }
}
