package com.cmj.example.fund;

import com.cmj.example.mapper.IndustrBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class IndustrServiceImpl implements IndustrService {

    @Autowired
    private IndustrBaseMapper industrBaseMapper;

}
