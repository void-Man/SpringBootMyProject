package com.cmj.example.fund;

import com.cmj.example.mapper.FundBaseMapper;
import com.cmj.example.mapper.FundEntryBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private FundEntryBaseMapper fundEntryBaseMapper;

}
