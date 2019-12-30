package com.lzw.face.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送组件
 * @author jamesluozhiwei
 * @date 2019/12/30
 */
@Slf4j
@Component
public class EmailMethod {

    @Resource
    private JavaMailSender jms;

    @Value("${spring.mail.username:ccccyc}")
    private String sendFrom;

    /**
     * 发送邮件
     * @param sendTo
     * @param subject
     * @param text
     * @throws MessagingException
     */
    public void sendTextEmail(String sendTo, String subject, String text) throws MessagingException {
        MimeMessage message = jms.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(this.sendFrom);
        helper.setTo(sendTo);
        helper.setSubject(subject);
        helper.setText(text);
        jms.send(message);
    }

}
