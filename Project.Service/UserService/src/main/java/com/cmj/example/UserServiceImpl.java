package com.cmj.example;

import com.cmj.example.mapper.UserMapper;
import com.cmj.example.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getFirst100User() {
        return userMapper.getFirst100User();
    }
}
