package pers.hdh.web.servlet;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Task;
import pers.hdh.beans.User;
import pers.hdh.service.TaskService;
import pers.hdh.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class TaskServlet extends BaseServlet {

    /**
     * 获取任务列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getTasks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "请先登录");
            return "/jsp/message.jsp";
        }

        // 获取查询条件
        String category = request.getParameter("category"); // 任务类型
        String desc = request.getParameter("desc");         // 任务要求
        String state = request.getParameter("state");       // 任务状态
        int currPage = Integer.parseInt(request.getParameter("currPage")); // 当前页码
        // 设置页面显示的记录数
        int pageSize = 10;

        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        PageBean<Task> pageBean = null;
        try {
            pageBean = service.getTasks(user, category, desc, state, currPage, pageSize);
//            logger.debug(pageBean.getTotalPage() + " :" + pageBean.getTotalCount() + ": " + pageBean.getPageSize());
        } catch (SQLException e) {
            logger.error("task表查询记录失败");
            throw e;
        }

        // 将参数放入域中后请求转发
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("category", category);
        request.setAttribute("desc", desc);
        return "/jsp/task_list.jsp";
    }
}
