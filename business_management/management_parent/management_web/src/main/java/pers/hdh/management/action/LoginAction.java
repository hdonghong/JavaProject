package pers.hdh.management.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import pers.hdh.management.domain.User;
import pers.hdh.management.utils.SysConstant;
import pers.hdh.management.utils.UtilFuns;

/**
 * @Description: 登录和退出类
 * @Author:		
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	/**
	 * 采用shiro安全框架登录认证
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
//		SSH传统登录方式
//		if(true){
//			String msg = "登录错误，请重新填写用户名密码!";
//			this.addActionError(msg);
//			throw new Exception(msg);
//		}
//		User user = new User(username, password);
//		User login = userService.login(user);
//		if (login != null) {
//			ActionContext.getContext().getValueStack().push(user);
//			session.put(SysConstant.CURRENT_USER_INFO, login);	//记录session
//			return SUCCESS;
//		}
//		return "login";
		if (UtilFuns.isEmpty(username)) return "login";
		
		try {
			// 得到Subject
			Subject subject = SecurityUtils.getSubject();
			// 调用登录方法
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token); // 执行此语句时，跳入到AuthRealm中的认证方法
			// 登录成功，从Shiro中获取用户的登录信息
			User user = (User) subject.getPrincipal();
			// 将用户放入session域中
			session.put(SysConstant.CURRENT_USER_INFO, user);
		} catch (Exception e) {
			e.printStackTrace();
			request.put("errorInfo", "对不起，用户名或密码错误");
			return "login";
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 退出
	 * @return
	 */
	public String logout(){
		session.remove(SysConstant.CURRENT_USER_INFO);		//删除session
		
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

