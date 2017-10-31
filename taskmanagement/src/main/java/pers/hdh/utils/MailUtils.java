package pers.hdh.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    // 发件者的邮箱与密码/授权码
//    private static String[] from={ "huang_dong_hong@163.com", "huangdonhon1020"};
    private static String[] from={ "799108252@qq.com", "hppujupilvpubbii"};
    // 设置邮件服务器主机名
    private static String host = "smtp.qq.com";

    /**
     * 发送邮件的方法
     * @param to 邮箱地址
     * @param msg 发送的信息
     */
    public static void sendMail(String to, String msg) throws Exception {

        // 1.创建连接对象，连接到邮箱服务器
        Properties prop = new Properties();
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", host);
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");

        /*
            注意：使用qq邮箱发送需要开启ssl加密
        */
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.setProperty("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);


        Session session = Session.getInstance(prop,  new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //设置发送人的帐号和密码
                return new PasswordAuthentication(from[0], from[1]);
            }
        });

        // 2.创建邮件对象
        Message message = new MimeMessage(session);
        // 2.1 设置发件者
        message.setFrom(new InternetAddress(from[0]));
        // 2.2 设置收件者
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 2.3 设置邮件主题
        message.setSubject("账号申述——来自任务管理系统网站");
        // 2.4 设置邮件的正文
        message.setContent("<h1>请点击<a href='http://localhost:8080/taskmanagement/user?method=updateUI&uid="+msg+"'>此链接</a>设置新密码</h1>", "text/html;charset=utf-8");

        // 3.发送邮件
        Transport.send(message);
    }
}
