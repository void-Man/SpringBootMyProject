package com.cmj.example.dao;

import com.cmj.example.vo.LoginUserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<LoginUserVo, Long> {
}
