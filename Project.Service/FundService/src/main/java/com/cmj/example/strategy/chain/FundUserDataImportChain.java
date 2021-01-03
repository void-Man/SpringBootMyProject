package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.FundUserBase;
import com.cmj.example.base.FundUserBaseExample;
import com.cmj.example.mapper.FundUserBaseMapper;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mengjie_chen
 * @description 基金经理导入处理类
 * @date 2021/1/2
 */
public class FundUserDataImportChain extends AbstractDataImportChain<FundUserBase> {
    private static final Logger logger = LoggerFactory.getLogger(FundUserDataImportChain.class);

    private final FundUserBaseMapper fundUserBaseMapper = SpringContextHolder.getBean(FundUserBaseMapper.class);

    public FundUserDataImportChain(DataReader reader) {
        super(reader);
    }

    @Override
    protected List<FundUserBase> parseFromTarget(String content) {
        // 所有基金类型
        List<FundUserBase> allUserList = fundUserBaseMapper.selectByExample(new FundUserBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example());
        try {
            JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");
            List<FundUserBase> fundUserBaseList = new ArrayList<>(10);
            for (int i = 0; i < diffList.size(); i++) {
                JSONObject diffVo = diffList.getJSONObject(i);
                String fundUserName = diffVo.getString("Name");
                Date beginTime = diffVo.getDate("RHDate");

                // 数据基金经理已存在，跳过
                if (allUserList.stream().anyMatch(fundUserBase -> fundUserBase.getName().equals(fundUserName))) {
                    continue;
                }
                // 当前基金经理已经在保存列表中，跳过
                if (fundUserBaseList.stream().anyMatch(fundUserBase -> fundUserBase.getName().equals(fundUserName))) {
                    continue;
                }

                FundUserBase addBase = FundUserBase.builder()
                        .name(fundUserName)
                        .beginTime(beginTime)
                        .build();
                fundUserBaseList.add(addBase);
            }
            return fundUserBaseList;
        } catch (Exception e) {
            logger.error("FundUserDataImportChain.parseFromTarget.exception.is----->", e);
            throw new RuntimeException("基金经理数据转换异常");
        }
    }

    @Override
    public void saveData(List<FundUserBase> date) {
        if (CollectionUtils.isNotNullAndEmpty(date)) {
            CollectionUtils.subList(date, 200)
                    .forEach(fundUserBases -> fundUserBaseMapper.batchInsertSelective(fundUserBases, FundUserBase.Column.name, FundUserBase.Column.beginTime));
        }
    }
}
