package com.cmj.example.base.vo;

import lombok.Data;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/5
 */
@Data
public class UserVo {

    private Long userId;
    private String userName;
    private String password;
    private String outerId;
    private Integer age;

    public static final class UserVoBuilder {
        private Long userId;
        private String userName;
        private String password;
        private String outerId;
        private Integer age;

        private UserVoBuilder() {
        }

        public static UserVoBuilder userVo() {
            return new UserVoBuilder();
        }

        public UserVoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserVoBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserVoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserVoBuilder outerId(String outerId) {
            this.outerId = outerId;
            return this;
        }

        public UserVoBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserVo build() {
            UserVo userVo = new UserVo();
            userVo.setUserId(userId);
            userVo.setUserName(userName);
            userVo.setPassword(password);
            userVo.setOuterId(outerId);
            userVo.setAge(age);
            return userVo;
        }
    }
}
