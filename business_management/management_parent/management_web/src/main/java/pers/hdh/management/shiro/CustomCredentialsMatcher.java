package pers.hdh.management.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import pers.hdh.management.utils.Encrypt;

/**
 * @ClassName	CustomCredentialsMatcher	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/02 20:31:11
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	@Override
	// 密码验证，token代表来自用户在界面输入的用户名和密码，info代表从数据库中得到的密文
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		// 将来自用户在界面输入的密码加密，这里使用用户名作盐
		Object pwdFromClient = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());
		
		// 取出数据库中的密文
		Object pwdFromDb = info.getCredentials();
		return this.equals(pwdFromClient, pwdFromDb);
	}
}
