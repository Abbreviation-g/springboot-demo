package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.mail.MailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailServiceTest {
    @Resource
    private MailService mailService;

    @Test
    public void sendTextMailMessage() {
        mailService.sendTextMailMessage(List.of("1398106199@qq.com"), "测试邮件", "测试邮件内容");
    }
}
