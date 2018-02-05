package pers.hdh.management.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import pers.hdh.management.domain.Module;
import pers.hdh.management.domain.Role;
import pers.hdh.management.domain.User;
import pers.hdh.management.service.UserService;

/**
 * @ClassName	AuthRealm	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/02 20:30:52
 */
public class AuthRealm extends AuthorizingRealm {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 授权，jsp页面使用shiro标签时，执行此方法
	 * 获取当前用户所拥有的角色，在获取其角色所有模块，将模块名加入权限中，视为该用户拥有的权限
	 * @param pc
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		User user = (User) pc.fromRealm(this.getName()).iterator().next();// 根据realm的名字去找对应的realm
		
		Set<Role> roles = user.getRoles();// 对象导航
		List<String> permissions = new ArrayList<>();
		for (Role role : roles) {
			// 遍历每个角色
			Set<Module> modules = role.getModules();// 获取每个角色下的模块集合
			for (Module module : modules) {
				// 根据模块名授权
				permissions.add(module.getName());
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);// 添加用户的模块（权限）
		return info;
	}
	
	/**
	 * 认证
	 * token代表来自用户在界面输入的用户名和密码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 强转
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 调用业务方法，根据用户名查询
		String hql = "from User where userName=?";
		List<User> list = userService.find(hql, User.class, new String[] {upToken.getUsername()});
		if (list != null && list.size() > 0) { // 如果存在该用户名的用户
			User user = list.get(0);
			/*
			 * SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
			 * Object principal		认为是当前用户对象
			 * Object credentials	这里是密码
			 * String realmName		realm的名字
			 */
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
			return info;// 返回info后进入密码比较器，即CustomCredentialsMatcher
		}
		return null;// 触发异常
	}
}
