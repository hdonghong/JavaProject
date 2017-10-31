package pers.hdh.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import pers.hdh.beans.User;
import pers.hdh.service.UserService;
import pers.hdh.service.impl.UserServiceImpl;
import pers.hdh.utils.BeanFactory;
import pers.hdh.utils.MD5Utils;
import pers.hdh.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends BaseServlet {

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @return
     */
    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "jsp/index.jsp";
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stuid = request.getParameter("stuid");
        String password = request.getParameter("password");

        User user = null;
        try {
            UserService service = (UserService) BeanFactory.getBean("UserService");
            user = service.getByStuidAndPWD(stuid, MD5Utils.md5(password));
        } catch (SQLException e){
            logger.error("user表查询记录失败");
            // 登录失败，跳转回到登录页面
            return "/";
        }

        if (user == null) { // 用户为空，查询不到用户
            request.setAttribute("msg", "不存在的账户或密码错误");
            return "/";
        } else { // 非空
            String[] saveArr = request.getParameterValues("save"); // 获取保存用户信息的请求
            if (saveArr != null ) { // 非空说明要求保存
                switch (saveArr.length) {
                    case 2: // 2个参数代表保存账号和密码
                        Cookie c = new Cookie("savePwd", password);
                        c.setPath(request.getContextPath()+"/");
                        c.setMaxAge(3600*24);
                        response.addCookie(c);
                    case 1: // 1个参数代表保存账号
                        c = new Cookie("saveStuid", stuid);
                        c.setPath(request.getContextPath()+"/");
                        c.setMaxAge(3600*24); // 保存一天
                        response.addCookie(c);

                }
            }

            // user放入session域中
            request.getSession().setAttribute("user", user);
            // 登录成功，跳转到主页面——任务列表，同时查询数据库，展示所有任务
            response.sendRedirect(request.getContextPath() + "/task?method=getTasks&currPage=1&category=&desc=&state=");
            return null;
        }
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @return
     */
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "jsp/regist.jsp";
    }

    /**
     * 用户注册，添加一条记录到user表中
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        // 获取表单数据，封装结果集
        BeanUtils.populate(user, request.getParameterMap());
        user.setUid(UUIDUtils.setId());
        // 密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));

        try {
            UserService service = (UserService) BeanFactory.getBean("UserService");
            service.add(user);
        } catch (SQLException e){
            e.printStackTrace();
            logger.error("user表添加记录失败");
            // 重定向，提示注册失败
            response.sendRedirect(request.getContextPath() + "/jsp/registError.jsp");
            return null;
        }
        // 重定向，提示注册成功
        response.sendRedirect(request.getContextPath() + "/jsp/registSuccess.jsp");
        return null;
    }

    /**
     * 退出用户
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 销毁session
        request.getSession().invalidate();
        // 重定向回到登录页面
        response.sendRedirect(request.getContextPath());
        return null;
    }

    /**
     * 修改用户信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 封装数据
        User user = new User();
        BeanUtils.populate(user, request.getParameterMap());
        // 密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));

        // 调用service处理业务逻辑
        try {
            UserService service = (UserService) BeanFactory.getBean("UserService");
            service.update(user);
        } catch (SQLException e) {
            logger.error("user表修改记录失败");
            throw e;
        }

        // 将新的user bean对象放入域中，更新数据
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/jsp/user_info.jsp");
        return null;
    }

    public String applyUpdatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stuid = request.getParameter("stuid");
        String email = request.getParameter("email");

        // 调用service处理业务逻辑
        try {
            UserService service = (UserService) BeanFactory.getBean("UserService");
            User user = service.getByStuidAndEmail(stuid, email);
            if (user == null) {
                request.setAttribute("message", "申述失败，请输入正确的账号和绑定的邮箱");
                return "jsp/apply_msg.jsp";
            }
        } catch (SQLException e) {
            logger.error("user表查询记录失败");
            throw e;
        }

        // 信息提示
        request.setAttribute("message", "申述成功，请前往您的邮箱查看申述结果");
        return "jsp/apply_msg.jsp";
    }

    /**
     * 跳转到修改密码页面，用于忘记密码时的申述
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String updateUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("uid",request.getParameter("uid"));
        return "jsp/update.jsp";
    }

    /**
     * 申述后修改密码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String updatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 调用service处理业务逻辑
        try {
            UserService service = (UserService) BeanFactory.getBean("UserService");

            System.out.print(request.getParameter("uid"));

            User user = service.getByUid(request.getParameter("uid"));
            // 密码加密
            user.setPassword(MD5Utils.md5(request.getParameter("password")));
            service.update(user);
        } catch (SQLException e) {
            logger.error("user表修改记录失败");
            throw e;
        }

        // 信息提示
        request.setAttribute("message", "密码修改成功，您可以使用新密码登录账号。");
        return "jsp/apply_msg.jsp";
    }

}
