package com.sunyue.shiro.server.serverimpl;


import com.sunyue.shiro.dao.TestUserMapper;
import com.sunyue.shiro.entity.TestUser;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunyue.shiro.server.TestUserServer;

import java.util.List;


/**
 * @description: TODO
 * @param: null
 * @return: {@link null}
 * @author sunyue
 * @date: 2022/6/16 16:42
 */
@Service
public class TestUserServerImpl implements TestUserServer {
    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public List<TestUser> selectAll() {
        return testUserMapper.findAll();
    }

    @Override
    public TestUser selectByOne(Integer id) {
        return testUserMapper.selectOne(id);
    }

    @Override
    public TestUser selectOneByName(String name) {
        return testUserMapper.selectOneByName(name);
    }

    @Override
    public void insert(TestUser testUser) {
        //加密写法
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password= new SimpleHash("md5",testUser.getPassword(),salt,2).toString();
        testUser.setPassword(password);
        testUser.setSalt(salt);
        testUserMapper.insert(testUser);
    }

    @Override
    public void delete(Integer id) {
        testUserMapper.delete(id);
    }

    @Override
    public void update(TestUser testUser) {
        testUserMapper.update(testUser);
    }
}
