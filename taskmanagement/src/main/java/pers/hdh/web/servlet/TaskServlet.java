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

public class TaskServlet extends BaseServlet {

    public String getTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        // 获取查询条件
        String category = request.getParameter("category"); // 任务类型
        String desc = request.getParameter("desc"); // 任务要求
        int currPage = Integer.parseInt(request.getParameter("currPage")); // 当前页码
        // 设置页面显示的记录数
        int pageSize = 10;

        TaskService service = (TaskService) BeanFactory.getBean("TaskService");
        PageBean<Task> pageBean = service.getTasks(user, category, desc, currPage, pageSize);

        // 将参数放入域中后请求转发？？？
        request.setAttribute("pageBean", pageBean);
        return "/jsp/task_list.jsp";
    }
}
