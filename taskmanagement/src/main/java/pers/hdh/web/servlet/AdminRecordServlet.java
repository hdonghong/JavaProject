package pers.hdh.web.servlet;

import pers.hdh.beans.PageBean;
import pers.hdh.beans.Record;
import pers.hdh.service.RecordService;
import pers.hdh.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 管理Record的Servlet
 */
public class AdminRecordServlet extends BaseServlet {

    /**
     * 获取任务记录
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getRecords(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("admin") == null) {
            // 强行进入管理平台，提示先登录
            return "/admin/welcome.jsp";
        }

        // 获取查询条件
        String category = request.getParameter("category"); // 任务类型
        String desc = request.getParameter("desc");         // 任务要求
        String state = request.getParameter("state");       // 任务状态
        String stuid = request.getParameter("stuid");       // 账号
        int currPage = Integer.parseInt(request.getParameter("currPage")); // 当前页码
        // 设置页面数据量
        int pageSize = 15;

        RecordService service = (RecordService) BeanFactory.getBean("RecordService");
        PageBean<Record>  pageBean = null;
        try {
            pageBean = service.getRecords(category, desc, state, stuid, currPage, pageSize);
        } catch (SQLException e){
            logger.error("管理员：查询record表失败");
            throw e;
        }

        // 将参数放入域中
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("category", category);
        request.setAttribute("desc", desc);
        request.setAttribute("state", state);
        request.setAttribute("stuid", stuid);

        return "/admin/record/list.jsp";
    }

    /**
     * 更新用户任务状态
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取数据
        int state = Integer.parseInt(request.getParameter("state"));
        String rid = request.getParameter("rid");

        // 封装
        Record record = new Record();
        record.setRid(rid);
        record.setState(state);

        RecordService service = (RecordService) BeanFactory.getBean("RecordService");
        try {
            service.update(record);
        } catch (SQLException e) {
            logger.error("管理员: 修改record表数据失败");
            throw e;
        }

        response.sendRedirect(request.getContextPath()+"/adminRecord?method=getRecords&currPage=1&category=&desc=&state="+state+"&stuid=");
        return null;
    }
}
