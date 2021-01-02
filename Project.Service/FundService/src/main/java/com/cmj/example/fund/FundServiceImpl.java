package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.*;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import com.cmj.example.mapper.FundTypeBaseMapper;
import com.cmj.example.mapper.FundUserBaseMapper;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private FundUserBaseMapper fundUserBaseMapper;
    @Autowired
    private FundTypeBaseMapper fundTypeBaseMapper;
    @Autowired
    private FundEntryBaseMapper fundEntryBaseMapper;

    @Override
    public void addFund(String path) throws IOException {
        String content = FileUtils.readFile(path);
        JSONArray diffList = JSONObject.parseObject(content).getJSONArray("data");

        List<FundTypeBase> fundTypeBaseList = this.getFundTypeByJSONArray(diffList);
        this.saveFundTypeBase(fundTypeBaseList);


        List<FundBase> fundBaseList = this.getFundByJSONArray(diffList);
        this.saveFundBase(fundBaseList);


        List<FundUserBase> fundUserBaseList = this.getFundUserByJSONArray(diffList);
        this.saveFundUserBase(fundUserBaseList);

    }

    /**
     * 保存基金经理
     *
     * @param fundUserBaseList
     * @return void
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private void saveFundUserBase(List<FundUserBase> fundUserBaseList) {
        if (CollectionUtils.isNotNullAndEmpty(fundUserBaseList)) {
            CollectionUtils.subList(fundUserBaseList, 200)
                    .forEach(fundUserBases -> fundUserBaseMapper.batchInsertSelective(fundUserBases, FundUserBase.Column.name));
        }
    }

    /**
     * 保存基金
     *
     * @param fundBaseList
     * @return void
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private void saveFundBase(List<FundBase> fundBaseList) {
        if (CollectionUtils.isNotNullAndEmpty(fundBaseList)) {
            CollectionUtils.subList(fundBaseList, 200)
                    .forEach(fundBases -> fundBaseMapper.batchInsertSelective(fundBases, FundBase.Column.name, FundBase.Column.number, FundBase.Column.fundTypeId));
        }
    }

    /**
     * 保存基金类型
     *
     * @param fundTypeBaseList
     * @return void
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private void saveFundTypeBase(List<FundTypeBase> fundTypeBaseList) {
        if (CollectionUtils.isNotNullAndEmpty(fundTypeBaseList)) {
            CollectionUtils.subList(fundTypeBaseList, 200)
                    .forEach(fundTypeBases -> fundTypeBaseMapper.batchInsertSelective(fundTypeBases, FundTypeBase.Column.name));
        }
    }

    /**
     * 从文件中获取基金经理信息
     *
     * @param diffList
     * @return java.util.List<com.cmj.example.base.FundUserBase>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private List<FundUserBase> getFundUserByJSONArray(JSONArray diffList) {
        List<FundUserBase> fundUserBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            String fundManager = diffVo.getString("fund_manager");
            String[] split = fundManager.split("、");
            if (split.length > 1) {
                for (String manager : split) {
                    this.add2List(fundUserBaseList, manager);
                }
            } else {
                this.add2List(fundUserBaseList, fundManager);
            }
        }
        return fundUserBaseList;
    }

    private void add2List(List<FundUserBase> fundUserBaseList, String manager) {
        // 基金经理是否已存在
        FundUserBase existBase = fundUserBaseMapper.selectOneByExample(new FundUserBaseExample().createCriteria()
                .andNameEqualTo(manager)
                .andIsDeleteEqualTo(0)
                .example());
        // 已存在一下则跳过
        if (Objects.nonNull(existBase)) {
            return;
        }
        // 已存在一下则跳过
        if (fundUserBaseList.stream().anyMatch(fub -> fub.getName().equals(manager))) {
            return;
        }
        FundUserBase fundUserBase = FundUserBase.builder()
                .name(manager)
                .build();
        fundUserBaseList.add(fundUserBase);
    }

    /**
     * 从文件中获取基金信息
     *
     * @param diffList
     * @return java.util.List<com.cmj.example.base.FundBase>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private List<FundBase> getFundByJSONArray(JSONArray diffList) {
        List<FundBase> fundBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            String sname = diffVo.getString("sname");
            String symbol = diffVo.getString("symbol");

            // 基金是否已存在
            FundBase existBase = fundBaseMapper.selectOneByExample(new FundBaseExample().createCriteria()
                    .andNameEqualTo(sname)
                    .andNumberEqualTo(symbol)
                    .andIsDeleteEqualTo(0)
                    .example());
            // 已存在一下则跳过
            if (Objects.nonNull(existBase)) {
                continue;
            }
            // 已存在一下则跳过
            if (fundBaseList.stream().anyMatch(fb -> fb.getName().equals(sname) && fb.getNumber().equals(symbol))) {
                continue;
            }
            FundBase fundBase = FundBase.builder()
                    .name(sname)
                    .number(symbol)
                    .fundTypeId(fundTypeBaseMapper.selectOneByExample(new FundTypeBaseExample()
                            .createCriteria()
                            .andNameEqualTo(diffVo.getString("jjlx"))
                            .andIsDeleteEqualTo(0)
                            .example())
                            .getId())
                    .build();
            fundBaseList.add(fundBase);
        }
        return fundBaseList;
    }

    /**
     * 从文件中获取基金类型
     *
     * @param diffList
     * @return java.util.List<com.cmj.example.base.FundTypeBase>
     * @author mengjie_chen
     * @date 2021/1/1
     */
    private List<FundTypeBase> getFundTypeByJSONArray(JSONArray diffList) {
        List<FundTypeBase> fundTypeBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            String jjlx = diffVo.getString("jjlx");

            // 类型是否已存在
            FundTypeBase existBase = fundTypeBaseMapper.selectOneByExample(new FundTypeBaseExample().createCriteria()
                    .andNameEqualTo(jjlx)
                    .andIsDeleteEqualTo(0)
                    .example());
            // 已存在一下则跳过
            if (Objects.nonNull(existBase)) {
                continue;
            }
            // 已存在一下则跳过
            if (fundTypeBaseList.stream().anyMatch(fbt -> fbt.getName().equals(jjlx))) {
                continue;
            }

            FundTypeBase fundTypeBase = FundTypeBase.builder()
                    .name(jjlx)
                    .build();
            fundTypeBaseList.add(fundTypeBase);
        }
        return fundTypeBaseList;
    }

    public static void main(String[] args) {
    }
}
