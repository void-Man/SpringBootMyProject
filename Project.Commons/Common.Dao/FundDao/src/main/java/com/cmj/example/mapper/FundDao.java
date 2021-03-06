package com.cmj.example.mapper;

import com.cmj.example.vo.StockIndustryResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/6
 */
@Repository
public interface FundDao {
    /**
     * 根据基金代码分析股票行业分布情况
     *
     * @param fundNUmberList
     * @return java.util.List<com.cmj.example.vo.StockIndustryResultVo>
     * @author mengjie_chen
     * @date 2021/3/6
     */
    public List<StockIndustryResultVo> getStockIndustryInfo(@Param("fundNUmberList") List<String> fundNUmberList);
}
