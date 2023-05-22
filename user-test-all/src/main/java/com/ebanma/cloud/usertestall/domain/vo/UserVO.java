package com.ebanma.cloud.usertestall.domain.vo;

import java.io.Serializable;

/**
 * @author 肖露
 * @version $ Id: UserVO, v 0.1 2023/03/17 10:22 banma-0241 Exp $
 */
public class UserVO implements Serializable {
    private static final Long serialVersionUID=384822012852377022L;
    private String username;
    private String password;

    private String email;

    private Integer age;
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
