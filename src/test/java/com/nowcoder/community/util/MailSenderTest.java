package com.nowcoder.community.util;

import com.nowcoder.community.CommunityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class MailSenderTest {
    @Autowired
    MailClient mailClient;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void mailSenderTest(){
        mailClient.sendMail("xuea111@126.com","subjecct","content");
    }

    @Test
    public void sendHtmlMail(){
        Context context = new Context();
        context.setVariable("username","Xiaobo");

        String content = templateEngine.process("/mail/activation", context);
        System.out.println(content);

        mailClient.sendMail("xuea111@126.com","htmlSubjecct","htmlContent");
    }
}
