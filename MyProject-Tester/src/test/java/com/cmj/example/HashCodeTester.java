package com.cmj.example;

import com.google.common.base.Objects;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/20
 */
public class HashCodeTester {

    public static void main(String[] args) {

    }

}

class TestVo {
    private String userId;
    private String name;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestVo testVo = (TestVo) o;
        return Objects.equal(userId, testVo.userId) &&
                Objects.equal(name, testVo.name) &&
                Objects.equal(password, testVo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, name, password);
    }
}