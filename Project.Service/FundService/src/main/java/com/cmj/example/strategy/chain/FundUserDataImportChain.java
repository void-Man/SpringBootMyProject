package com.cmj.example.strategy.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.FundTypeBase;
import com.cmj.example.base.FundTypeBaseExample;
import com.cmj.example.base.FundUserBase;
import com.cmj.example.mapper.FundTypeBaseMapper;
import com.cmj.example.mapper.FundUserBaseMapper;
import com.cmj.example.strategy.reader.DataReader;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public void saveData(List<FundUserBase> date) {
        if (CollectionUtils.isNotNullAndEmpty(date)) {
            CollectionUtils.subList(date, 200)
                    .forEach(fundUserBases -> fundUserBaseMapper.batchInsertSelective(fundUserBases, FundUserBase.Column.name, FundUserBase.Column.beginTime));
        }
    }

//    @Override
//    protected List<FundTypeBase> parseFromTarget(String content) {
//        // 所有基金类型
//        List<FundTypeBase> allTypeList = fundTypeBaseMapper.selectByExample(new FundTypeBaseExample().createCriteria()
//                .andIsDeleteEqualTo(0)
//                .example());
//        try {
//            JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");
//            List<FundTypeBase> fundTypeBaseList = new ArrayList<>(10);
//            for (int i = 0; i < diffList.size(); i++) {
//                JSONObject diffVo = diffList.getJSONObject(i);
//                String fundType = diffVo.getString("jjlx");
//
//                // 数据基金类型已存在，跳过
//                if (allTypeList.stream().anyMatch(fbt -> fbt.getName().equals(fundType))) {
//                    continue;
//                }
//                // 当前基金类型已经在保存列表中，跳过
//                if (fundTypeBaseList.stream().anyMatch(fbt -> fbt.getName().equals(fundType))) {
//                    continue;
//                }
//
//                FundTypeBase addBase = FundTypeBase.builder()
//                        .name(fundType)
//                        .build();
//                fundTypeBaseList.add(addBase);
//            }
//            return fundTypeBaseList;
//        } catch (Exception e) {
//            logger.error("FundTypeDataImportChain.parseFromTarget.exception.is----->", e);
//            throw new RuntimeException("基金类型数据转换异常");
//        }
//    }
//
//    @Override
//    public void saveData(List<FundTypeBase> date) {
//        if (CollectionUtils.isNotNullAndEmpty(date)) {
//            CollectionUtils.subList(date, 200)
//                    .forEach(fundTypeBases -> fundTypeBaseMapper.batchInsertSelective(fundTypeBases, FundTypeBase.Column.name));
//        }
//    }
}
