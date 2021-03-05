package com.cmj.example;

import com.cmj.example.vo.UserVo;

import java.util.List;

public interface UserService {

    List<UserVo> getFirst100User();

}
