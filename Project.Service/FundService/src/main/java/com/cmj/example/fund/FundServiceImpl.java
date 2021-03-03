package com.cmj.example.fund;

import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.FundBase;
import com.cmj.example.base.FundBaseExample;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.strategy.FundAndTypeImportDataInitializer;
import com.cmj.example.strategy.FundEntryImportDataInitializer;
import com.cmj.example.strategy.FundHasUserImportDataInitializer;
import com.cmj.example.strategy.ImportDataInitializer;
import com.cmj.example.strategy.reader.JSONTextDataReader;
import com.cmj.example.utils.HttpsUtils;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class FundServiceImpl implements FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundServiceImpl.class);

    private FundBaseMapper fundBaseMapper;

    @Autowired
    public void setFundBaseMapper(FundBaseMapper fundBaseMapper) {
        this.fundBaseMapper = fundBaseMapper;
    }

    @Override
    public void addFund(String path) {
        ImportDataInitializer initializer = new FundAndTypeImportDataInitializer(new JSONTextDataReader());
        initializer.ImportData(path);
    }

    @Override
    public void addFundHasUser(String path) {
        ImportDataInitializer initializer = new FundHasUserImportDataInitializer(new JSONTextDataReader());
        initializer.ImportData(path);
    }

    @Override
    public void addFundEntry(List<String> pathList) {
        for (String path : pathList) {
            ImportDataInitializer initializer = new FundEntryImportDataInitializer(new JSONTextDataReader());
            initializer.ImportData(path);
        }
    }

    @Override
    public void updateFundCreateTime() throws IOException {
        // 查询所有基金，按照代码分组
        Map<String, Optional<FundBase>> numberMap = fundBaseMapper.selectByExample(new FundBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .example())
                .stream()
                .collect(Collectors.groupingBy(FundBase::getNumber, Collectors.reducing((o, o2) -> o)));
        for (int i = 1; i < 100; i++) {
            String content = this.getFundInfo(i);
            Integer count = JSONObject.parseObject(content).getJSONObject("rsm").getJSONObject("count").getInteger("nums");
            if ((i + 1) * 100 - count >= 100) {
                break;
            }
            logger.info(content);
        }
    }

    /**
     * 获取基金信息
     *
     * @param
     * @param i
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/7
     */
    private String getFundInfo(int current) throws IOException {
        String url = "https://www.touzid.com/index.php?/fund/ajax/metrics/";

        List<BasicHeader> basicHeaderList = new ArrayList<>(10);
        basicHeaderList.add(new BasicHeader("accept", "application/json, text/plain, */*"));
        basicHeaderList.add(new BasicHeader("accept-encoding", "gzip, deflate, br"));
        basicHeaderList.add(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
        basicHeaderList.add(new BasicHeader("content-type", "application/json;charset=UTF-8"));
        basicHeaderList.add(new BasicHeader("cookie", "rwe__Session=9pm0tj1ra8qp84l4ub2f4hh6c6; rwe__user_login=RJ5aWTut2LTOeNVOizltjFFax4W7JrESNRN7WU9taOzRF36y9siqI6d0oPkuGfKCPjCd6nAyKymybKj2kXdcJuB5fCkpUBUWo3pbt%2BKm85iAZCpEBHjWu%2FHKWysl2hO4; Hm_lvt_c0219d21ca52d4d5bb3d64c64189ef01=1609906121,1609906127,1609934388,1609934396; Hm_lpvt_c0219d21ca52d4d5bb3d64c64189ef01=1609989266"));
        basicHeaderList.add(new BasicHeader("origin", "https://www.touzid.com"));
        basicHeaderList.add(new BasicHeader("referer", "https://www.touzid.com/fund/index.html"));
        basicHeaderList.add(new BasicHeader("sec-fetch-dest", "empty"));
        basicHeaderList.add(new BasicHeader("sec-fetch-mode", "cors"));
        basicHeaderList.add(new BasicHeader("sec-fetch-site", "same-origin"));
        basicHeaderList.add(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36"));
        basicHeaderList.add(new BasicHeader("x-requested-with", "XMLHttpRequest"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("st", "2011");
        jsonObject.put("et", "2021");
        jsonObject.put("type", "0");
        jsonObject.put("symbol", "");
        jsonObject.put("indice_type", "0");
        jsonObject.put("valuetype", 0);
        jsonObject.put("year", "10");
        jsonObject.put("etf_no", 1);
        JSONObject o2 = new JSONObject();
        o2.put("prop", "asset");
        o2.put("order", "desc");
        jsonObject.put("sort", o2);
        jsonObject.put("offset", current);
        jsonObject.put("pagesize", 100);
        return HttpsUtils.post(url, basicHeaderList, jsonObject.toJSONString());
    }
}
