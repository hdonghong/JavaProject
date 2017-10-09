package pers.hdh.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.service.TaskService;
import pers.hdh.utils.BeanFactory;
import pers.hdh.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 管理Task的Servlet
 */
public class AdminTaskServlet extends BaseServlet {

    /**
     * 获取所有任务
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getTasks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        // 获取查询条件
        String category = request.getParameter("category"); // 任务类型
        String desc = request.getParameter("desc"); // 任务内容
        int currPage = Integer.parseInt(request.getParameter("currPage")); // 当前页码
        int pageSize = 15; // 设置页面显示记录数

        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        PageBean<Task> pageBean = null;
        try {
          pageBean = service.getTasks(category, desc, currPage, pageSize);
        } catch (SQLException e) {
            logger.error("管理员：查询task表所有记录失败");
            throw e;
        }

        // 将参数放入request域中后请求转发
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("category", category);
        request.setAttribute("desc", desc);

        return "/admin/task/list.jsp";
    }

    /**
     * 获取一个任务
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getByTid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        String tid = request.getParameter("tid");

        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        Task task = null;
        try {
            task = service.getByTid(tid);
        } catch(SQLException e) {
            logger.error("管理员: 通过tid获取任务失败");
            throw e;
        }

        // task放入域中后请求转发到编辑页面
        request.setAttribute("task", task);
        return "/admin/task/edit.jsp";
    }

    /**
     * 修改任务
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
        Task task = new Task();
        BeanUtils.populate(task, request.getParameterMap());

        // 获取service对象处理业务逻辑
        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        try {
            service.update(task);
        } catch (SQLException e) {
            logger.error("管理员:修改task表数据失败");
            throw e;
        }

        // 重定向到所有任务页面
        response.sendRedirect(request.getContextPath()+"/adminTask?method=getTasks&currPage=1&category=&desc=");
        return null;
    }

    /**
     * 跳转到任务发布页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }
        return "/admin/task/add.jsp";
    }

    /**
     * 发布任务
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 封装数据
        Task task = new Task();
        task.setTid(UUIDUtils.setId());
        BeanUtils.populate(task, request.getParameterMap());

        // 调用service发布任务
        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        try {
            service.add(task);
        } catch (SQLException e) {
            logger.error("管理员:添加记录到task表失败");
            throw e;
        }

        // 重定向到所有任务页面
        response.sendRedirect(request.getContextPath()+"/adminTask?method=getTasks&currPage=1&category=&desc=");
        return null;
    }

    /**
     * 删除任务
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

        String tid = request.getParameter("tid"); // 获取参数
        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        try {
            service.delete(tid);
        } catch (SQLException e) {
            logger.error("管理员：删除task表记录失败");
            throw e;
        }

        // 重定向到所有任务页面
        response.sendRedirect(request.getContextPath()+"/adminTask?method=getTasks&currPage=1&category=&desc=");
        return null;
    }

    /**
     * 批量删除任务
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String deleteTasks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        String tids_str = request.getParameter("tids_str"); // 获取参数
        String[] tids = tids_str.trim().split(" ");

        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        try {
            service.delete(tids);
        } catch (SQLException e) {
            logger.error("管理员：批量删除task表记录失败");
            throw e;
        }

        // 重定向到所有任务页面
        response.sendRedirect(request.getContextPath()+"/adminTask?method=getTasks&currPage=1&category=&desc=");
        return null;
    }
}
