package com.sunyue.shiro.dao;


import com.sunyue.shiro.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestUserMapper {
    List<TestUser> findAll();

    TestUser selectOne(Integer id);

    TestUser selectOneByName(String username);

    void insert(TestUser testUser);

    void update(TestUser testUser);

    void delete(Integer id);
}
