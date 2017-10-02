package pers.hdh.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pers.hdh.beans.Admin;
import pers.hdh.beans.PageBean;
import pers.hdh.beans.User;
import pers.hdh.service.UserService;
import pers.hdh.utils.BeanFactory;
import pers.hdh.utils.DataSourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminUserServlet extends BaseServlet {

    /**
     * 展示所有用户
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        // 获取数据
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        String stuid = request.getParameter("stuid");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        // 分页查询，设置页面显示记录数
        int pageSize = 15;

        // 调用service处理业务逻辑
        UserService service = (UserService) BeanFactory.getBean("UserService");
        PageBean<User> pageBean;
        try {
            pageBean = service.getUsers(stuid, name, category, currPage, pageSize);
        } catch (SQLException e) {
            logger.error("查询user表所有记录失败");
            throw e;
        }

        // 将参数放入request域中
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("stuid", stuid);
        request.setAttribute("name", name);
        request.setAttribute("category", category);

        // 页面跳转
        return "/admin/user/list.jsp";
    }

    /**
     * 通过uid获取user
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getByUid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getParameter("uid");

        UserService service = (UserService) BeanFactory.getBean("UserService");
        User user = null;
        try {
            user = service.getByUid(uid);
        } catch (SQLException e) {
            logger.error("通过uid查询user表失败");
            throw e;
        }
        // user放入域中后请求转发到编辑页面
        request.setAttribute("user", user);
        return "/admin/user/edit.jsp";
    }

    /**
     * 修改用户信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        // 封装数据
        User user = new User();
        BeanUtils.populate(user, request.getParameterMap());

        // 获取service对象处理业务逻辑
        UserService service = (UserService) BeanFactory.getBean("UserService");
        try {
            service.update(user);
        } catch (SQLException e) {
            logger.error("管理员:修改user表数据失败");
            throw e;
        }

        // 页面重定向到用户管理页面
        response.sendRedirect(request.getContextPath()+"/adminUser?method=getUsers&currPage=1&stuid=&name=&category=");
        return null;
    }

    /**
     * 注销用户
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        String uid = request.getParameter("uid"); // 获取参数
        UserService service = (UserService) BeanFactory.getBean("UserService");
        try {
            service.delete(uid);
        } catch (SQLException e) {
            logger.error("管理员：删除user表记录失败");
            throw e;
        }

        // 页面重定向到用户管理页面
        response.sendRedirect(request.getContextPath()+"/adminUser?method=getUsers&currPage=1&stuid=&name=&category=");
        return null;
    }
    /**
     * 管理员登录,这里不专门写它的service层和dao层了，直接在这里完成
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取管理员账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 编写sql语句，通过QueryRunner操作sql
        String sql = "select aid, username, password from admin where username=? and password=? ";
        Admin admin = new QueryRunner(DataSourceUtils.getDataSource()).query(sql, new BeanHandler<>(Admin.class), username, password);

        if (admin != null) { // 查询到管理员用户，登录后台管理系统
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
        } else { // 查询不到管理员用户，回到登录页面
            response.sendRedirect(request.getContextPath() + "/admin");
        }
        return null;
    }

}
