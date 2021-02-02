package com.sunyue.shiro.config;

import com.sunyue.shiro.entity.TestUser;
import com.sunyue.shiro.server.TestUserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private TestUserServer testUserServer;

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        /*获取当前登录的用户信息*/
        Subject subject = SecurityUtils.getSubject();
        TestUser testUser = (TestUser) subject.getPrincipal();
        //设置角色,多个角色
        /*Set<String> rolesSet = new HashSet<>();
        rolesSet.add(testUser.getRole());*/
        //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(rolesSet);
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*可以在以下list加入多个权限*/
        /*List<String> roles = new ArrayList<>();
        roles.add(testUser.getPerms());
        info.addRoles(roles);*/
        //设置权限
        info.addRole(testUser.getRole());
        //需要判断权限是否为空值（null是没有地址，""是有地址但是里面的内容是空的）
        if (testUser.getPerms() != null && !testUser.getPerms().equals("")) {
            info.addStringPermission(testUser.getPerms());
        }
        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        /*获取令牌*/
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) authenticationToken;
        //取出用户名并且判断用户名是否和数据库一致
        TestUser testUser = testUserServer.selectOneByName(passwordToken.getUsername());
        if (testUser != null) {
            //进行认证，将正确数据给shiro处理
            //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
            /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
             * 第二个参数必须放密码，
             * 第三个参数放 当前realm的名字，因为可能有多个realm*/
            //若密码不正确则返回IncorrectCredentialsException异常
            return new SimpleAuthenticationInfo(testUser, testUser.getPassword(), this.getName());
        }
        //若用户名不存在则返回UnknownAccountException异常
        return null;
    }
}
