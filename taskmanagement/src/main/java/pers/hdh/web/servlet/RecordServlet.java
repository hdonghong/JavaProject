package pers.hdh.web.servlet;

import com.sun.org.apache.regexp.internal.RE;
import pers.hdh.beans.PageBean;
import pers.hdh.beans.Record;
import pers.hdh.beans.User;
import pers.hdh.service.RecordService;
import pers.hdh.utils.BeanFactory;
import pers.hdh.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 操作record表的用户端servlet
 */
public class RecordServlet extends BaseServlet {

    /**
     * 更新用户对于指定任务的状态
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取传递的数据
        String state = request.getParameter("state");
        String uid = request.getParameter("uid");
        String tid = request.getParameter("tid");

        // 封装数据
        Record record = new Record();
        record.setState(Integer.parseInt(state));
        record.getUser().setUid(uid);
        record.getTask().setTid(tid);

        try {
            // 从容器中获取service处理业务逻辑
            RecordService service = (RecordService) BeanFactory.getBean("RecordService");
            // 返回的是成功操作db的记录数
            int flag = service.update(record);
            if (flag == 0) { // 更新失败->记录中没有操作的记录->添加
                record.setRid(UUIDUtils.setId());
                service.add(record);
            }
        } catch (SQLException e) {
            logger.error("record表修改记录或添加记录失败");
            throw e;
        }

        // 页面重定向
        response.sendRedirect(request.getContextPath()+"/task?method=getTasks&currPage=1&category=&desc=&state=");
        return null;
    }

    /**
     * 查询管理员审核用户任务的记录作为消息提醒
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String getMessages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "请先登录");
            return "/jsp/message.jsp";
        }
        // 当前页码
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        // 页面展示的记录数
        int pageSize = 10;

        try {
            // 调用service层处理业务逻辑
            PageBean<Record> pageBean =
                    ((RecordService) BeanFactory.getBean("RecordService")).getRecords(currPage, pageSize, user);
            // 放入request域中
            request.setAttribute("pageBean", pageBean);
        } catch (SQLException e) {
            logger.error("record表查询记录失败");
            throw e;
        }

        // 请求转发
        return "/jsp/user_msg.jsp";
    }

    /**
     * 信息已读
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String haveRead(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String rid = request.getParameter("rid");

        try {
            // 调用service层处理业务逻辑
            RecordService service = ((RecordService) BeanFactory.getBean("RecordService"));
            // 先查询到记录
            Record record = service.getRecord(rid);
            // 再更新
            record.setIs_read(1);
            service.update(record);
        } catch (SQLException e) {
            logger.error("record表查询一条记录失败");
            throw e;
        }

        response.sendRedirect(request.getContextPath()+"/record?method=getMessages&currPage=1");
        return null;
    }


    public String haveReadAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg", "请先登录");
            return "/jsp/message.jsp";
        }

        try {
            // 调用service层处理业务逻辑
            RecordService service = ((RecordService) BeanFactory.getBean("RecordService"));
            List<Record> list= service.getRecords(user);

            for (Record record: list) { // 只能更新任务记录是管理操作的(通过任务、拒绝任务)

                if ((record.getIs_read() == null || record.getIs_read()==0)
                        && (record.getState() == 3 || record.getState() == 4)) {
                    record.setIs_read(1);
                    service.update(record);
                }
            }
        } catch (SQLException e) {
            logger.error("record表查询多条记录失败");
            throw e;
        }


        response.sendRedirect(request.getContextPath()+"/record?method=getMessages&currPage=1");
        return null;
    }

}
