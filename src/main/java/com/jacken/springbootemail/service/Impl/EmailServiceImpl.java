package com.jacken.springbootemail.service.Impl;

import com.jacken.springbootemail.config.EmailConfig;
import com.jacken.springbootemail.service.EmailService;
import com.sun.istack.internal.NotNull;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Override
    public void sendEmial(String sendTo, String title, String content) {
        //简单邮件的发送
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailConfig.getEmailFrom());
        mailMessage.setTo(sendTo);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        mailSender.send(mailMessage);

    }

    @Override
    public void sendAttachmentEmail(String sendTo, String title, String content, File file) {
        //创建发送附件邮箱的对象
        MimeMessage msg = mailSender.createMimeMessage();

        try {
          MimeMessageHelper helper=  new MimeMessageHelper(msg,true);
          helper.setFrom(emailConfig.getEmailFrom());
          helper.setTo(sendTo);
          helper.setSubject(title);

          helper.setText(content);

            FileSystemResource  fileSystemResource=new FileSystemResource(file);
            helper.addAttachment("附件",fileSystemResource);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(msg);

    }

    @Override
    public void sendTemplateEmail(String sendTo, String title, String info) {
//创建发送附件邮箱的对象
        MimeMessage msg = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper=  new MimeMessageHelper(msg,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            //封装模板数据

            HashMap<String, Object> map = new HashMap<>();
            map.put("username","小红");
            //得到模板
            Template template=freeMarkerConfigurer.getConfiguration().getTemplate(info);
            String htmlTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            helper.setText(htmlTemplate,true);


        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

        mailSender.send(msg);
    }
}
