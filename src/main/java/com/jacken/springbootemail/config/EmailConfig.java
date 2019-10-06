package com.jacken.springbootemail.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailConfig {
    @Value("${spring.mail.username}")
    private  String emailFrom;
    @Value("${spring.mail.sendTo}")
    private  String emailTo;

    @Value("${spring.mail.title}")
    private  String emailTitle;

    @Value("${spring.mail.content}")
    private  String emailContent;
}
