package com.jacken.springbootemail.controller;

import com.jacken.springbootemail.config.EmailConfig;
import com.jacken.springbootemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailConfig emailConfig;

    /**
     * 发送简单的邮箱
     * @return
     */
    @RequestMapping("/sendEmail")
    @ResponseBody
    public  String sendEmail(){
        emailService.sendEmial("hzj011ning@163.com","你好,想要睡觉觉","明天去你家玩，哈哈哈哈");
        return "email send  success";
    }

    /**
     * 发送带附件的邮箱
     * @return
     */
    @RequestMapping(value = "/attachment",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public  String sendAttachment(){
        File file = new File("C:\\Users\\wangqiang\\Desktop\\code\\springboot-email\\src\\main\\resources\\static\\学习资料.txt");
        emailService.sendAttachmentEmail(emailConfig.getEmailTo(),emailConfig.getEmailTitle(),emailConfig.getEmailContent(),file);
        return "Attachmentmail send  success  !!!";
    }
}
