package com.jacken.springbootemail.service;

import java.io.File;

public interface EmailService {
    /**
     * 发送简单的邮件
     * @param sendTo
     * @param title
     * @param content
     */
    void sendEmial(String sendTo,String title,String content);

    /**
     * 发送代附件的邮箱
     * @param sendTo
     * @param title
     * @param content
     * @param file
     */
    void sendAttachmentEmail(String sendTo, String title, String content, File file);
}
