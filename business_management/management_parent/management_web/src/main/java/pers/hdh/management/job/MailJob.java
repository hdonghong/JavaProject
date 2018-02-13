package pers.hdh.management.job;

import java.util.Date;

/**
 * @ClassName	MailJob	
 * @Description	自动发送邮件
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/13 13:01:22
 */
public class MailJob {

	public void send() {
		System.out.println("执行了：" + new Date());
	}
	
}
