package com.nowcoder.community;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class MapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectById(){
        User temp = userMapper.selectById(11);
        System.out.println(temp);
    }

    @Test
    public void testInsertUser(){
        User tempUser = new User();
        tempUser.setUsername("das");
        tempUser.setPassword("123");
        tempUser.setSalt("salt");
        userMapper.insertUser(tempUser);
    }

    @Test
    public void updateStatusTest(){
        int status = 1;
        userMapper.updateStatus(150, 0);
    }
}
