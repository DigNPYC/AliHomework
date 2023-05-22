package com.ebanma.cloud.usertestall.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 肖露
 * @version $ Id: UserDTO, v 0.1 2023/03/17 10:21 banma-0241 Exp $
 */
public class UserDTO implements Serializable {
    private static final Long serialVersionUID=67284979646862L;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String phone;
    private Long version;
    private LocalDateTime created;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
