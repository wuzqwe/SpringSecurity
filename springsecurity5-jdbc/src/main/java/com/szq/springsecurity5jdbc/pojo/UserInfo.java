package com.szq.springsecurity5jdbc.pojo;

import javax.persistence.*;

//用来表示当前类是一个实体类，表示数据库中的表
//表名默认和类名一样的
@Table(name = "user")
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名称
    private String username;
    //密码
    private String password;
    //角色
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
