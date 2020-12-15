package com.cmj.example.mapper;

import com.cmj.example.base.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserVo> getAllUser();

}
