package com.cmj.example.mapper;

import com.cmj.example.base.FundPositionEntryBase;
import com.cmj.example.base.FundPositionEntryBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundPositionEntryBaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    long countByExample(FundPositionEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int insert(FundPositionEntryBase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") FundPositionEntryBase record, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    FundPositionEntryBase selectOneByExample(FundPositionEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    FundPositionEntryBase selectOneByExampleSelective(@Param("example") FundPositionEntryBaseExample example, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    List<FundPositionEntryBase> selectByExampleSelective(@Param("example") FundPositionEntryBaseExample example, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    List<FundPositionEntryBase> selectByExample(FundPositionEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    FundPositionEntryBase selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    FundPositionEntryBase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") FundPositionEntryBase record, @Param("example") FundPositionEntryBaseExample example, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") FundPositionEntryBase record, @Param("example") FundPositionEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") FundPositionEntryBase record, @Param("selective") FundPositionEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FundPositionEntryBase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int batchInsert(@Param("list") List<FundPositionEntryBase> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundpositionentry
     *
     * @mbg.generated
     */
    int batchInsertSelective(@Param("list") List<FundPositionEntryBase> list, @Param("selective") FundPositionEntryBase.Column ... selective);
}