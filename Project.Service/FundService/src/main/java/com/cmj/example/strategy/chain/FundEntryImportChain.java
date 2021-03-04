package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.*;
import com.cmj.example.vo.FundEntryVo;
import com.cmj.example.mapper.*;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.DateTimeUtils;
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
public class FundEntryImportChain extends AbstractDataImportChain<FundPositionEntryBase> {
    private static final Logger logger = LoggerFactory.getLogger(FundEntryImportChain.class);

    private final FundBaseMapper fundBaseMapper = SpringContextHolder.getBean(FundBaseMapper.class);
    private final FundTypeBaseMapper fundTypeBaseMapper = SpringContextHolder.getBean(FundTypeBaseMapper.class);
    private final FundPositionEntryBaseMapper fundPositionEntryBaseMapper = SpringContextHolder.getBean(FundPositionEntryBaseMapper.class);
    private final StockBaseMapper stockBaseMapper = SpringContextHolder.getBean(StockBaseMapper.class);

    public FundEntryImportChain(DataReader reader) {
        super(reader);
    }

    @Override
    protected List<FundPositionEntryBase> parseFromTarget(String content) {
        try {
            JSONArray diffList = JSONObject.parseObject(content).getJSONArray("rsm");
            List<FundEntryVo> fundEntryVoList = new ArrayList<>(10);
            for (int i = 0; i < diffList.size(); i++) {
                JSONObject diffVo = diffList.getJSONObject(i);
                String createTime = diffVo.getString("date");
                String stockNumber = diffVo.getJSONObject("items").getString("symbol");
                String stockName = diffVo.getJSONObject("items").getString("name");
                Integer stockBuyQuantity = diffVo.getJSONObject("items").getBigDecimal("sh_nums").intValue();
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
                    .collect(Collectors.groupingBy(StockBase::getNumber, Collectors.reducing((t, t2) -> t)));
            FundBase fundBase = fundBaseMapper.selectOneByExample(new FundBaseExample().createCriteria()
                    .andIsDeleteEqualTo(0)
                    .andNameEqualTo(fundEntryVoList.get(0).getFundName())
                    .example());
            if (Objects.isNull(fundBase)) {
                throw new RuntimeException("未查询到 " + fundEntryVoList.get(0).getFundName() + " 基金");
            }

            // 查询所有该基金的持仓明细
            List<FundPositionEntryBase> allFundEntry = fundPositionEntryBaseMapper.selectByExample(new FundPositionEntryBaseExample().createCriteria()
                    .andIsDeleteEqualTo(0)
                    .andFundIdEqualTo(fundBase.getId())
                    .example());

            List<FundPositionEntryBase> fundPositionEntryBaseList = new ArrayList<>(10);
            for (FundEntryVo fundEntryVo : fundEntryVoList) {
                Optional<StockBase> stockBase = stockNumberMap.get(fundEntryVo.getStockNumber());
                if (Objects.isNull(stockBase) || !stockBase.isPresent()) {
                    continue;
                }

                // 数据库中已存在该基金通时间的持仓明细
                if (allFundEntry.stream().anyMatch(fundPositionEntryBase -> fundPositionEntryBase.getStockId().equals(stockBase.get().getId()) && fundPositionEntryBase.getCreateTime().equals(DateTimeUtils.parse(fundEntryVo.getCreateTime(), DateTimeUtils.DATETIME_FORMAT2)))) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("stockId", stockBase.get().getId());
                    jsonObject.put("createTime", fundEntryVo.getCreateTime());
                    logger.error("FundEntryImportChain.parseFromTarget----->持仓明细已存在 " + jsonObject.toJSONString());
                    continue;
                }

                FundPositionEntryBase addBase = FundPositionEntryBase.builder()
                        .stockId(stockBase.get().getId())
                        .fundId(fundBase.getId())
                        .stockQuantity(fundEntryVo.getStockBuyQuantity())
                        .amount(fundEntryVo.getStockBuyAmount())
                        .createTime(DateTimeUtils.parse(fundEntryVo.getCreateTime(), DateTimeUtils.DATETIME_FORMAT2))
                        .build();
                fundPositionEntryBaseList.add(addBase);
            }
            return fundPositionEntryBaseList;
        } catch (Exception e) {
            logger.error("FundEntryImportChain.parseFromTarget.exception.is----->", e);
            throw new RuntimeException("基金持仓数据转换异常");
        }
    }

    @Override
    public void saveData(List<FundPositionEntryBase> date) {
        if (CollectionUtils.isNotNullAndEmpty(date)) {
            CollectionUtils.subList(date, 200)
                    .forEach(fundPositionEntryBases -> fundPositionEntryBaseMapper.batchInsertSelective(fundPositionEntryBases, FundPositionEntryBase.Column.stockId
                            , FundPositionEntryBase.Column.fundId
                            , FundPositionEntryBase.Column.stockQuantity
                            , FundPositionEntryBase.Column.amount
                            , FundPositionEntryBase.Column.createTime));
        }
    }
}
