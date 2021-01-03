package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.*;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundHasFundUserBaseMapper;
import com.cmj.example.mapper.FundUserBaseMapper;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/3
 */
public class FundHasFundUserDataImportChain extends AbstractDataImportChain<FundHasFundUserBase> {
    private static final Logger logger = LoggerFactory.getLogger(FundHasFundUserDataImportChain.class);

    private final FundHasFundUserBaseMapper fundHasFundUserBaseMapper = SpringContextHolder.getBean(FundHasFundUserBaseMapper.class);
    private final FundUserBaseMapper fundUserBaseMapper = SpringContextHolder.getBean(FundUserBaseMapper.class);
    private final FundBaseMapper fundBaseMapper = SpringContextHolder.getBean(FundBaseMapper.class);

    public FundHasFundUserDataImportChain(DataReader reader) {
        super(reader);
    }

    @Override
    protected List<FundHasFundUserBase> parseFromTarget(String content) {
        // 所有基金经理和基金关系
        List<FundHasFundUserBase> allList = fundHasFundUserBaseMapper.selectByExample(new FundHasFundUserBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        // 所有基金经理
        List<FundUserBase> allUserList = fundUserBaseMapper.selectByExample(new FundUserBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        // 所有基金
        List<FundBase> allFundList = fundBaseMapper.selectByExample(new FundBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        try {
            JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");
            List<FundHasFundUserBase> fundHasFundUserBaseList = new ArrayList<>(10);
            Date now = new Date();
            for (int i = 0; i < diffList.size(); i++) {
                JSONObject diffVo = diffList.getJSONObject(i);
                String fundUserName = diffVo.getString("Name");
                String fundNumber = diffVo.getString("Symbol");
                Date startDate = diffVo.getDate("BDate");
                BigDecimal profitRate = diffVo.getBigDecimal("NavRate").multiply(new BigDecimal("100"));
                BigDecimal averageRate = diffVo.getBigDecimal("NavHYRate").multiply(new BigDecimal("100"));
                Integer ranking = diffVo.getInteger("Rank");

                // 根据基金经理姓名找到基金经理数据
                Optional<FundUserBase> existUserBase = allUserList.stream().filter(fundUserBase -> fundUserBase.getName().equals(fundUserName)).findFirst();
                // 根据基金编号找到基金数据
                Optional<FundBase> existFundBase = allFundList.stream().filter(fundBase -> fundBase.getNumber().equals(fundNumber)).findFirst();

                if (!existUserBase.isPresent() || !existFundBase.isPresent()) {
                    logger.error("基金：" + fundNumber + " 或者 基金经理：" + fundUserName + " 不存在");
                    continue;
                }

                // 数据在数据库已存在，跳过
                if (allList.stream().anyMatch(fundHasFundUserBase -> fundHasFundUserBase.getFundUserId().equals(existUserBase.get().getId()) || fundHasFundUserBase.getFundId().equals(existFundBase.get().getId()))) {
                    continue;
                }
                // 已经在保存列表中，跳过
                if (fundHasFundUserBaseList.stream().anyMatch(fundHasFundUserBase -> fundHasFundUserBase.getFundUserId().equals(existUserBase.get().getId())|| fundHasFundUserBase.getFundId().equals(existFundBase.get().getId()))) {
                    continue;
                }

                FundHasFundUserBase addBase = FundHasFundUserBase.builder()
                        .fundId(existFundBase.get().getId())
                        .fundUserId(existUserBase.get().getId())
                        .startTime(startDate)
                        .profitRate(profitRate)
                        .averageRate(averageRate)
                        .ranking(ranking)
                        .createTime(now)
                        .build();
                fundHasFundUserBaseList.add(addBase);
            }
            return fundHasFundUserBaseList;
        } catch (Exception e) {
            logger.error("FundHasFundUserDataImportChain.parseFromTarget.exception.is----->", e);
            throw new RuntimeException("基金经理和基金数据转换异常");
        }
    }

    @Override
    public void saveData(List<FundHasFundUserBase> date) {
        if (CollectionUtils.isNotNullAndEmpty(date)) {
            CollectionUtils.subList(date, 200)
                    .forEach(fundHasFundUserBases -> fundHasFundUserBaseMapper.batchInsertSelective(fundHasFundUserBases
                            , FundHasFundUserBase.Column.fundId
                            , FundHasFundUserBase.Column.fundUserId
                            , FundHasFundUserBase.Column.startTime
                            , FundHasFundUserBase.Column.profitRate
                            , FundHasFundUserBase.Column.averageRate
                            , FundHasFundUserBase.Column.ranking
                            , FundHasFundUserBase.Column.createTime));
        }
    }
}
