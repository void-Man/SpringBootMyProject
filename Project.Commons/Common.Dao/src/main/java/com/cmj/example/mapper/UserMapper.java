package com.cmj.example.mapper;

import com.cmj.example.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserVo> getAllUser();

    void updateCreateTimeById(@Param("date") String date, @Param("idList") List<Integer> idList);
}
