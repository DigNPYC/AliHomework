package com.ebanma.cloud.usertestall.domain.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

    private static final long serialVersionUID = -9067058367551596824L;
    /*
     * 用户名
     */
    private String username;
    /*
     * 密码
     */
    private String password;
    /*
     * 邮箱
     */
    private String email;
    /*
     * 手机号
     */
    private String phone;
    /*
     * 年龄
     */
    private Integer age;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
