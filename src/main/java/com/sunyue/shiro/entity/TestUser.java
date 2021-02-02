package com.sunyue.shiro.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class TestUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    /*权限*/
    private String perms;
    /*角色*/
    private String role;
    /*加盐密码*/
    private String salt;
}
