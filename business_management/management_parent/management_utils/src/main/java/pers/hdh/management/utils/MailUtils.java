package pers.hdh.management.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @ClassName	MailUtils	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/06 15:49:03
 */
public class MailUtils {

	/**
	 * 发送邮件
	 * @param to 接收方的email
	 * @param subject 邮件主题
	 * @param text 邮件内容
	 * @throws Exception
	 */
	public static void sendMessage(String to, String subject, String text) throws Exception {
		Properties props = new Properties();
		// 指定邮件的发送服务器地址
		props.put("mail.smtp.host", "smtp.qq.com");
		// 服务器验证用户的身份信息
		props.put("mail.smtp.auth", "true");
		// 使用qq邮箱发送需要开启ssl加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
		
		// 开启一个会话
		Session session = Session.getInstance(props);
		// 启用debug模式，可在控制台输出smtp应答的过程
		session.setDebug(true);
		
		// 创建一个MimeMessage格式的邮件
		MimeMessage message = new MimeMessage(session);
		// 设置发送方
		Address fromAddr = new InternetAddress("799108252@qq.com");
		message.setFrom(fromAddr);
		// 指定接收方
		Address toAddr = new InternetAddress(to);
		message.setRecipient(RecipientType.TO, toAddr);
		// 设置邮件主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setText(text);
		// 保存右键
		message.saveChanges();
		
		// 得到邮件传输者
		Transport transport = session.getTransport("smtp");
		// 连接到服务器
		transport.connect("smtp.qq.com", "799108252@qq.com", "qkbylzyzvwmsbgah");
		// 发送
		transport.sendMessage(message, message.getAllRecipients());
		//关闭通道
		transport.close();
	}
}
