package com.sunyue.shiro.server;

import com.sunyue.shiro.entity.TestUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestUserServer {
    /*查询所有*/
    List<TestUser> selectAll();

    /*查询一个用户*/
    TestUser selectByOne(Integer id);

    /*通过名字查询一个用户*/
    TestUser selectOneByName(String name);

    /*增加一个用户*/
    void insert(TestUser testUser);

    /*删除一个用户*/
    void delete(Integer id);

    /*更新一个用户*/
    void update(TestUser testUser);
}
