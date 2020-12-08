package com.cmj.example.vo;

import javax.persistence.*;

/**
 * @author mengjie_chen
 * @description date 2020/12/8
 */
@Entity
@Table(name = "t_base_user")
public class UserRepositoryVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FId",length = 11)
    private Long userId;
    @Column(name = "FName",length = 32)
    private String userName;
    @Column(name = "FPassword",length = 32)
    private String password;
    @Column(name = "FOuterId",length = 32)
    private String outerId;
    @Column(name = "FAge",length = 11)
    private Integer age;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public static final class UserRepositoryVoBuilder {
        private Long userId;
        private String userName;
        private String password;
        private String outerId;
        private Integer age;

        private UserRepositoryVoBuilder() {
        }

        public static UserRepositoryVoBuilder userRepositoryVo() {
            return new UserRepositoryVoBuilder();
        }

        public UserRepositoryVoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserRepositoryVoBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserRepositoryVoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRepositoryVoBuilder outerId(String outerId) {
            this.outerId = outerId;
            return this;
        }

        public UserRepositoryVoBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserRepositoryVo build() {
            UserRepositoryVo userRepositoryVo = new UserRepositoryVo();
            userRepositoryVo.setUserId(userId);
            userRepositoryVo.setUserName(userName);
            userRepositoryVo.setPassword(password);
            userRepositoryVo.setOuterId(outerId);
            userRepositoryVo.setAge(age);
            return userRepositoryVo;
        }
    }
}
