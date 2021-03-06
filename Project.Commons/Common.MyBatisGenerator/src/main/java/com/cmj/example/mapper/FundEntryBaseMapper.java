package com.cmj.example.mapper;

import com.cmj.example.base.FundEntryBase;
import com.cmj.example.base.FundEntryBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundEntryBaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    long countByExample(FundEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int insert(FundEntryBase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int insertSelective(@Param("record") FundEntryBase record, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    FundEntryBase selectOneByExample(FundEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    FundEntryBase selectOneByExampleSelective(@Param("example") FundEntryBaseExample example, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    List<FundEntryBase> selectByExampleSelective(@Param("example") FundEntryBaseExample example, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    List<FundEntryBase> selectByExample(FundEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    FundEntryBase selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    FundEntryBase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") FundEntryBase record, @Param("example") FundEntryBaseExample example, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") FundEntryBase record, @Param("example") FundEntryBaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(@Param("record") FundEntryBase record, @Param("selective") FundEntryBase.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FundEntryBase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int batchInsert(@Param("list") List<FundEntryBase> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_base_fundentry
     *
     * @mbg.generated
     */
    int batchInsertSelective(@Param("list") List<FundEntryBase> list, @Param("selective") FundEntryBase.Column ... selective);
}