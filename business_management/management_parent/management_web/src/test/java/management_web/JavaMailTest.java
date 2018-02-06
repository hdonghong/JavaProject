package management_web;

import java.io.File;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @ClassName	JavaMailTest	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/06 11:17:31
 */
public class JavaMailTest {

	@Test
	public void testJavaMail() throws Exception {
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
		Address toAddr = new InternetAddress("1229494771@qq.com");
		message.setRecipient(RecipientType.TO, toAddr);
		// 设置邮件主题
		message.setSubject("我是题目");
		// 设置邮件内容
		message.setText("hello,world");
		// 保存右键
		message.saveChanges();
		
		// 得到邮件传输者
		Transport transport = session.getTransport("smtp");
		// 连接到服务器
		transport.connect("smtp.qq.com", "799108252@qq.com", "qkbylzyzvwmsbgah");//qkbylzyzvwmsbgah
		// 发送
//		transport.send(message, message.getAllRecipients());
//		调用这个方法可能被腾讯认为垃圾邮件！Exception：javax.mail.AuthenticationFailedException: failed to connect, no password specified?
		transport.sendMessage(message, message.getAllRecipients());
	}
	
	@Test
	public void testSimpleMailMessage() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		
		// 邮件对象
		SimpleMailMessage message = (SimpleMailMessage) context.getBean("mailMessage");
		// 邮件发送对象
		JavaMailSender sender = (JavaMailSender) context.getBean("mailSender");
		
		message.setSubject("这是主题");
		message.setText("这是内容");
		message.setTo("1229494771@qq.com");
		
		sender.send(message);
	}
	
	@Test
	public void testMimeMailMessage() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		
		// 邮件发送对象
		JavaMailSender sender = (JavaMailSender) context.getBean("mailSender");
		// 创建一个邮件对象
		MimeMessage message = sender.createMimeMessage();
		// 借助工具类来操作邮件对象，注意第二个参数multipart设为true，否则会报出IllegalStateException
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		// 设置邮件基本内容
		messageHelper.setFrom("799108252@qq.com");
		messageHelper.setTo("1229494771@qq.com");
		messageHelper.setSubject("主题");
		// cid为固定的，后面的image是自己定义的，true表示可以解析html代码
		messageHelper.setText("<html><head></head><body><h1>你好呀</h1><img src=cid:image/></body></html>", true);
		
		// 添加图片
		FileSystemResource imageResource = new FileSystemResource(new File("C:\\Users\\Lenovo\\Pictures\\wallhaven-577977.jpg"));
		messageHelper.addInline("image", imageResource);// image与上面定义的一致
		// 添加附件
		FileSystemResource docResource = new FileSystemResource(new File("C:\\Users\\Lenovo\\Desktop\\新建 Microsoft Word 文档.docx"));
		messageHelper.addAttachment("doc", docResource);
		
		// 发送邮件
		sender.send(message);
	}
}
