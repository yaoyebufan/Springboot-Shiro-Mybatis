package com.sunyue.shiro.controller;

import com.sunyue.shiro.entity.TestUser;
import com.sunyue.shiro.server.TestUserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class indexController {
    @Autowired
    private TestUserServer testUserServer;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    private String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        TestUser testUser = testUserServer.selectOneByName(username);
        if (testUser != null) {
            //根据salt值和用户输入的密码计算加密后的密码
            String salt = testUser.getSalt();
            password = new SimpleHash("md5", password, salt, 2).toString();
            System.out.println(password);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //UsernamePasswordToken token = new UsernamePasswordToken(username, testUser.getPassword());(不加密写法)
        try {
            //将用户名和密码通过token传给shiro进行认证
            subject.login(token);
            TestUser user = (TestUser) subject.getPrincipal();
            subject.getSession().setAttribute("testUser", user);
            return "index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码有误");
            return "login";
        }

    }

    @ResponseBody
    @GetMapping("/unauthor")
    public String unauthor() {
        return "权限不足，无法访问";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @PostMapping("/register")
    public String register(TestUser testUser, Model model) {
        String username = testUser.getUsername();
        String password = testUser.getPassword();
        if (username == null || username.equals("")) {
            model.addAttribute("msg", "用户名不能为空");
            return "register";
        } else if (password == null || password.equals("")) {
            model.addAttribute("msg", "密码不能为空");
            return "register";
        } else if (testUserServer.selectOneByName(username) != null) {
            model.addAttribute("msg", "用户名已被占用");
            return "register";
        } else {
            testUserServer.insert(testUser);
            return "login";
        }
    }
}
