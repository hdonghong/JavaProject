package team.webstore.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.User;
import team.webstore.service.UserService;
import team.webstore.utils.CookieUtils;
import team.webstore.utils.SecretUtil;

/**
 * 用户模块控制器
 * @author hdonghong 
 * @version 创建时间：2017年11月20日 上午9:28:41 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = -6411418075593780419L;

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 注册功能，实现网站的新用户注册
	 * @return 跳转到登录页面
	 */
	public String regist() {
		userService.save(user);
		return LOGIN;
	}
	
	/**
	 * 防止重用用户名，注册时通过ajax发送异步请求进行检查
	 * @return
	 */
	public String checkRepeatedUser() {
		User repeatedUser = userService.checkRepeatedUser(user);
		if (repeatedUser != null) { // 如果用户名重复，返回"no"标志
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("no");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return NONE;
	}
	
	// 自动登录
	private String autoLogin;
	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	// 记住用户名
	private String rememberUserName;
	public void setRememberUserName(String rememberUserName) {
		this.rememberUserName = rememberUserName;
	}

	/**
	 * 用户登录功能
	 * @return 跳转到首页
	 */
	public String login() {
		// 密码会在业务逻辑层进行加密，在此先保存一份，为选了记住用户名的用户做准备
		String password = user.getPassword();
		// 获取request和response两个域对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		// 验证码校验功能
		// 获取客户端验证码
        String clientCode = request.getParameter("code").trim();
        // 获取服务器端验证码
        String serverCode = (String)request.getSession().getAttribute("serverCode");
        //确保验证码的一次性，用完后从session中移除
        request.getSession().removeAttribute("serverCode");
        if (!serverCode.trim().equalsIgnoreCase(clientCode)) {
        	request.getSession().setAttribute("message", "验证码错误");
            return LOGIN;
        }
		
        // 调用业务层处理业务逻辑
		User existedUser = userService.login(user);
		if (existedUser == null) {
			request.getSession().setAttribute("message", "无效的用户名或密码错误");
			return LOGIN;
		} else {
			// 判断是否选择了记住密码、记住用户名		
			// 记住用户名的功能
			if (rememberUserName != null) { // 选择了记住用户名
				// cookie保存到项目根目录下，设定存活时间为24小时
				try {
					CookieUtils.addCookie(new Cookie("save_username", URLEncoder.encode(user.getUsername(), "utf-8") ), 
										  request.getContextPath()+"/", 
										  3600*24, 
										  response);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else { // 没有勾选记住用户名，假设原来存在cookie，设置存活时间为0，实现移除的效果
				Cookie cookie = CookieUtils.getCookieByName("save_username", request.getCookies());
				if (cookie != null ) {
					cookie.setValue(null);
					CookieUtils.addCookie(cookie,
										  request.getContextPath()+"/", 
										  0, 
										  response);
				}
			}
			
			// 记住密码的功能
			if (autoLogin != null) { // 选择了记住密码
				// cookie保存到项目根目录下，设定存活时间为24小时
				CookieUtils.addCookie(new Cookie("save_password", password), 
									  request.getContextPath()+"/", 
									  3600*24, 
									  response);
			} else { // 没有勾选记住密码，假设原来存在cookie，设置存活时间为0，实现移除的效果
				Cookie cookie = CookieUtils.getCookieByName("save_password", request.getCookies());
				if (cookie != null) {
					cookie.setValue(null);
					CookieUtils.addCookie(cookie,
										  request.getContextPath()+"/", 
										  0,
										  response);
				}
			}
			
			// 清除message
			request.getSession().removeAttribute("message");;
			// 将用户保存到session
			request.getSession().setAttribute("user", existedUser);
		}
		
		return "loginOk";
	}
	
	/**
	 * 生成验证码
	 * @return
	 */
	public String createCode() {
		// 获取request和response
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        // 设置生出的验证码字符数和图片尺寸
        int charNum = 4;
        int width = 20 * 4;
        int height = 30;


        // 1. 创建一张内存图片
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 2.获得绘图对象
        Graphics graphics = bufferedImage.getGraphics();

        // 3、绘制背景颜色
        graphics.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        graphics.fillRect(0, 0, width, height);

        // 4、绘制图片边框
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 5、输出验证码内容
        graphics.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)).brighter());
        graphics.setFont(new Font("宋体", Font.BOLD, 20));

        // 随机输出charNum个字符
        Graphics2D graphics2d = (Graphics2D) graphics;
        String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        //session中要用到
        String msg="";
        int x = 3;
        for (int i = 0; i < charNum; i++) {
            int index = random.nextInt(32);
            String content = String.valueOf(s.charAt(index));
            msg+=content;
            double theta = random.nextInt(45) * Math.PI / 180;
            //让字体扭曲
            graphics2d.rotate(theta, x, 18);
            graphics2d.drawString(content, x, 18);
            graphics2d.rotate(-theta, x, 18);
            x += 20;
        }
        //将验证码放入session中
        request.getSession().setAttribute("serverCode", msg);

        // 6、绘制干扰线
        graphics.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        // 释放资源
        graphics.dispose();

        // 图片输出 ImageIO
        try {
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return NONE;
	}
	
	/**
	 * 用户退出功能
	 * @return 跳转回到首页
	 */
	public String logout() {
		//ServletActionContext.getRequest().getSession().removeAttribute("user");
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	/**
	 * 修改用户个人资料
	 * @return
	 */
	public String update() {
		// 修改用户的个人资料，返回修改好的user
		user = userService.update(user);
		// 成功修改后获取user对象放入session，更新当前会话的用户信息
		if (user != null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "person_info";
		} else {
			return LOGIN;
		}
	}
	
	/**
	 * 重置密码第一步：
	 * 进行身份验证，用于用户使用找回密码前的确认，
	 * 验证成功，就发送验证结果的邮件至申请的邮箱，并在页面提示
	 * 失败则提示验证失败
	 * @return
	 */
	public String authenticate() {
		if (userService.authenticate(user)) {
			ActionContext.getContext().getValueStack().set("msg", "身份验证成功，验证结果已发送到您的邮箱，请前往您的邮箱进行下一步操作。");
		} else {
			ActionContext.getContext().getValueStack().set("msg", "身份验证失败，请重新确认验证信息。");
		}
		return "msg";
	}
	
	private String code;
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 重置密码第二步。
	 * 通过加密后uid获取到user对象，成功获取则跳转到重置密码页面
	 * @return
	 */
	public String loadResetPwdPage() {
		try {
			user = userService.getById(Long.parseLong(SecretUtil.decode(code)));
			ActionContext.getContext().getValueStack().set("updatedUser", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "resetPassword";
	}

	/**
	 * 重置密码第三步。
	 * 更新密码
	 * @return
	 */
	public String resetPassword() {
		if (userService.update(user) != null) {
			ActionContext.getContext().getValueStack().set("msg", "密码重置成功。");
		} else {
			ActionContext.getContext().getValueStack().set("msg", "操作错误，服务器内部异常，请重新操作。");
		}
		return "msg";
	}
}
