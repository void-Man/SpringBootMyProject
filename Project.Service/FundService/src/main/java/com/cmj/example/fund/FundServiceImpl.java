package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.*;
import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundDao;
import com.cmj.example.mapper.FundPositionEntryBaseMapper;
import com.cmj.example.mapper.StockBaseMapper;
import com.cmj.example.strategy.FundAndTypeImportDataInitializer;
import com.cmj.example.strategy.FundEntryImportDataInitializer;
import com.cmj.example.strategy.FundHasUserImportDataInitializer;
import com.cmj.example.strategy.ImportDataInitializer;
import com.cmj.example.strategy.reader.JSONTextDataReader;
import com.cmj.example.utils.HttpsUtils;
import com.cmj.example.utils.StringUtils;
import com.cmj.example.vo.IndustryPositionVo;
import com.cmj.example.vo.StockIndustryResultVo;
import com.cmj.example.vo.StockRateVo;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class FundServiceImpl implements FundService {
    private static final Logger logger = LoggerFactory.getLogger(FundServiceImpl.class);

    @Autowired
    private FundBaseMapper fundBaseMapper;
    @Autowired
    private StockBaseMapper stockBaseMapper;
    @Autowired
    private FundPositionEntryBaseMapper fundPositionEntryBaseMapper;
    @Autowired
    private FundDao fundDao;

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

    @Override
    public void addTop10Stock(String fundNumber) throws IOException {
        FundBase fundBase = fundBaseMapper.selectOneByExample(new FundBaseExample().createCriteria()
                .andIsDeleteEqualTo(0)
                .andNumberEqualTo(fundNumber)
                .example());
        List<StockRateVo> stockRateVoList = this.requestStockRateInfo(fundNumber);
        List<FundPositionEntryBase> addList = new ArrayList<>(10);
        for (StockRateVo stockRateVo : stockRateVoList) {
            StockBase stockBase = stockBaseMapper.selectOneByExample(new StockBaseExample().createCriteria()
                    .andIsDeleteEqualTo(0)
                    .andNumberEqualTo(stockRateVo.getCode())
                    .example());
            FundPositionEntryBase addBase = FundPositionEntryBase.builder()
                    .fundId(fundBase.getId())
                    .stockId(stockBase.getId())
                    .stockQuantity(stockRateVo.getCount())
                    .amount(stockRateVo.getTotal_assets())
                    .stockRate(stockRateVo.getRatio())
                    .build();
            addList.add(addBase);
        }
        fundPositionEntryBaseMapper.batchInsertSelective(addList, FundPositionEntryBase.Column.fundId, FundPositionEntryBase.Column.stockId, FundPositionEntryBase.Column.amount, FundPositionEntryBase.Column.stockQuantity, FundPositionEntryBase.Column.stockRate);
    }

    @Override
    public List<IndustryPositionVo> getStockIndustryInfo(String fundNumbers) {
        List<String> fundNUmberList = Arrays.asList(fundNumbers.split(","));
        List<StockIndustryResultVo> stockIndustryResultVoList = fundDao.getStockIndustryInfo(fundNUmberList);
        BigDecimal totalAmount = stockIndustryResultVoList.stream().filter(Objects::nonNull).map(StockIndustryResultVo::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        List<IndustryPositionVo> industryPositionVoList = new ArrayList<>(10);
        stockIndustryResultVoList.stream()
                .collect(Collectors.groupingBy(StockIndustryResultVo::getIndustryName))
                .forEach((industryName, list) -> {
                    BigDecimal industryTotalAmount = list.stream().filter(Objects::nonNull).map(StockIndustryResultVo::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                    IndustryPositionVo industryPositionVo = new IndustryPositionVo();
                    industryPositionVo.setIndustryName(industryName);
                    industryPositionVo.setAmount(industryTotalAmount);
                    industryPositionVoList.add(industryPositionVo);
                });
        for (int i = 0; i < industryPositionVoList.size(); i++) {
            IndustryPositionVo industryPositionVo = industryPositionVoList.get(i);
            if (industryPositionVoList.size() - 1 == i) {
                industryPositionVo.setRate(new BigDecimal("100").subtract(industryPositionVoList.stream().map(IndustryPositionVo::getRate).filter(Objects::nonNull).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));
                break;
            }
            industryPositionVo.setRate(industryPositionVo.getAmount().divide(totalAmount, 20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        return industryPositionVoList;
    }

    /**
     * 请求获取股票持仓数量
     *
     * @param
     * @param fundNumber
     * @return java.util.List<com.cmj.example.vo.StockRateVo>
     * @author mengjie_chen
     * @date 2021/3/6
     */
    private List<StockRateVo> requestStockRateInfo(String fundNumber) throws IOException {
        String url = "https://web.ifzq.gtimg.cn/fund/newfund/fundInvesting/getInvesting";
        List<BasicNameValuePair> paramList = new ArrayList<>(10);
        paramList.add(new BasicNameValuePair("app", "web"));
        paramList.add(new BasicNameValuePair("symbol", "jj" + fundNumber));
        paramList.add(new BasicNameValuePair("_callback", "jQuery111102605675353179122_1615003191043"));
        paramList.add(new BasicNameValuePair("_", "1615003191044"));
        String result = HttpsUtils.get(url, paramList);
        result = this.patternStr(result);
        return this.toStockRateVo(result);
    }

    /**
     * 将json串解析为对象
     *
     * @param content
     * @return java.util.List<com.cmj.example.vo.StockRateVo>
     * @author mengjie_chen
     * @date 2021/3/6
     */
    private List<StockRateVo> toStockRateVo(String content) {
        JSONObject jsonObject = JSONObject.parseObject(content);
        String top10Str = jsonObject.getJSONObject("data").getJSONObject("data").getString("zhongcang");
        return JSONArray.parseArray(top10Str, StockRateVo.class);
    }

    /**
     * 正则匹配获取json串
     *
     * @param content
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/3/6
     */
    private String patternStr(String content) {
        // 替换前面多余的字符串
        String preStr = "jQuery(.*\\()";
        Matcher preMacher = Pattern.compile(preStr).matcher(content);
        if (preMacher.find()) {
            String noPre = preMacher.replaceAll("");
            if (StringUtils.isEmpty(noPre)) {
                throw new RuntimeException("解析JSON错误");
            }
            // 替换后面多余的字符串
            String afterStr = "\\)";
            Matcher afterMatcher = Pattern.compile(afterStr).matcher(noPre);
            if (afterMatcher.find()) {
                String noAfter = afterMatcher.replaceAll("");
                if (StringUtils.isEmpty(noAfter)) {
                    throw new RuntimeException("解析JSON错误");
                }
                return noAfter;
            }
        } else {
            throw new RuntimeException("解析JSON错误");
        }
        return null;
    }

    /**
     * 获取基金信息
     *
     * @param current
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/3/6
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
