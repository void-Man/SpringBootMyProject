package com.cmj.example.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/5
 */
public class UserVo implements UserDetails {

    private String userId;
    private String userName;
    private String password;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * 授予ACCESSER权限
     *
     * @param
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     * @author mengjie_chen
     * @date 2020/12/5
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ACCESSER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 不过期
     *
     * @param
     * @return boolean
     * @author mengjie_chen
     * @date 2020/12/5
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 不加锁
     *
     * @param
     * @return boolean
     * @author mengjie_chen
     * @date 2020/12/5
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 不禁用
     *
     * @param
     * @return boolean
     * @author mengjie_chen
     * @date 2020/12/5
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
