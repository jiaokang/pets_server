package com.lwj.pets.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

/**
 * @Author ：焦康
 * @Date ：Created in 22:02 2025/3/6
 * @Desc ：
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 设置发件人地址
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 发送包含HTML内容和内联图片的电子邮件，包含验证码
     *
     * @param to               收件人地址
     * @param verificationCode 验证码
     * @param expirationTime   验证码过期时间（分钟）
     * @throws MessagingException 如果发送过程中发生错误
     */
    public void sendVerificationEmail(String to, String verificationCode, int expirationTime) throws MessagingException {
        // 准备模板上下文
        Context context = new Context();
        context.setVariable("verificationCode", verificationCode);
        context.setVariable("expirationTime", expirationTime);
        context.setVariable("currentYear", LocalDateTime.now().getYear());

        // 渲染模板
        String htmlContent = templateEngine.process("verification-email-template", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("您的验证码");
        helper.setText(htmlContent, true); // 第二个参数设置为true表示内容为HTML

        // 添加内联图片
        ClassPathResource resource = new ClassPathResource("static/logo.png"); // 假设logo.png在resources/static目录下
        helper.addInline("logoId", resource);

        javaMailSender.send(message);
    }

    /*
     * 发送包含HTML内容和内联图片的电子邮件，包含事件提醒
     */
    public void sendNotifyEmail(String to, String eventType, String petOwnerName, String petName, String date) throws MessagingException {
        // 准备模板上下文
        Context context = new Context();
        context.setVariable("eventType", eventType);
        context.setVariable("petOwnerName", petOwnerName);
        context.setVariable("petName", petName);
        context.setVariable("date", date);
        context.setVariable("currentYear", LocalDateTime.now().getYear());

        // 渲染模板
        String htmlContent = templateEngine.process("event-notify-template", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject("系统通知");
        helper.setText(htmlContent, true); // 第二个参数设置为true表示内容为HTML

        // 添加内联图片
        ClassPathResource resource = new ClassPathResource("static/logo.png"); // 假设logo.png在resources/static目录下
        helper.addInline("logoId", resource);

        javaMailSender.send(message);
    }


}
