package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    // 注册时需要发邮件
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public Map<String, Object> register(User user){
        Map<String, Object> map = new HashMap<>();

        // 空值检测
        if(user == null){
            throw new IllegalArgumentException();
        }
        if(user.getUsername().isBlank()){
            map.put("usernameMsg", "用户名不能为空");
            return map;
        }
        if(user.getPassword().isBlank()){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if(user.getEmail().isBlank()){
            map.put("emailMsg", "Email不能为空");
            return map;
        }

        // 用户信息是否已被注册
       if(userMapper.selectByName(user.getUsername()) != null){
           map.put("usernameMsg", "用户名已被注册");
           return map;
       }
       // 邮箱是否已被注册
       if(userMapper.selectByEmail(user.getEmail()) != null){
           map.put("emailMsg", "邮箱已被注册");
           return map;
       }

       // 注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 4));
       // 普通用户
       user.setType(0);
       // 未激活
       user.setStatus(0);
       user.setActivationCode(CommunityUtil.generateUUID());
       user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
       user.setCreateTime(new Date());
       userMapper.insertUser(user);

       // 开发激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // Url example: http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "activation" + user.getId() +user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "Activation", content);

        return map;
    }
}
