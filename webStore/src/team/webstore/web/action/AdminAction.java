package team.webstore.web.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.Admin;
import team.webstore.service.AdminService;

/**
 * 管理员的控制器
 * @author hdonghong 
 * @version 创建时间：2017年12月10日 下午5:29:00 
 */
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private static final long serialVersionUID = 7318104188160601769L;

	private Admin admin = new Admin();
	@Override
	public Admin getModel() {
		return admin;
	}

	private AdminService adminService;
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String login() {
		admin = adminService.login(admin);
		if (admin == null) {
			return "adminLogin";
		}
		ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
		return "loginOk";
	}
	
	public String logout() {
		ServletActionContext.getRequest().getSession().removeAttribute("admin");
		// 退回到登录页面
		return "adminLogin";
	}
	
}
