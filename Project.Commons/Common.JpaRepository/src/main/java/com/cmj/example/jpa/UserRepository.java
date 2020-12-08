package com.cmj.example.jpa;

import com.cmj.example.vo.UserRepositoryVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRepositoryVo, Long> {
}
