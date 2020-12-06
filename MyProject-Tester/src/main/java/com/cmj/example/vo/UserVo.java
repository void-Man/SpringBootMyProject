package com.cmj.example.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/5
 */
@Entity
@Table(name = "t_base_user")
public class UserVo implements UserDetails {

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
