package com.cmj.example.fund;

import lombok.Data;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/2
 */
@Data
public class FundVo {

    private Integer id;
    /**
     * 基金类型ID
     */
    private Integer fundTypeId;
    /**
     * 基金类型ID
     */
    private String number;
    /**
     * 基金名称
     */
    private String name;

}
