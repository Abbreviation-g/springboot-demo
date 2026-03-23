package com.my.springboot.demo.mail;

import com.my.springboot.demo.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MailService {
    /**
     * 注入邮件工具类
     */
    @Autowired(required = false)
    private JavaMailSender javaMailSender;

    /**
     * 发送者邮箱
     */
    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * receivers: 接收者邮箱
     */
    public void sendTextMailMessage(List<String> receivers, String subject, String content) {
        try {
            log.info("发送：{} -> {}", sendMailer, receivers);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            mimeMessageHelper.setFrom(sendMailer);
            mimeMessageHelper.setTo(receivers.toArray(String[]::new));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(content);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("发送邮件成功：{} -> {}", sendMailer, receivers);
        } catch (Exception e) {
            log.error("发送邮件失败：{} -> {}", sendMailer, receivers);
            log.error("发送邮件失败", e);
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发送邮件失败");
        }
    }
}
