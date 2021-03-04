package com.cmj.example.vo;

import lombok.Data;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/5
 */
@Data
public class UserTestVo {

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

        public UserTestVo build() {
            UserTestVo userTestVo = new UserTestVo();
            userTestVo.setUserId(userId);
            userTestVo.setUserName(userName);
            userTestVo.setPassword(password);
            userTestVo.setOuterId(outerId);
            userTestVo.setAge(age);
            return userTestVo;
        }
    }
}
