package pers.hdh.web.servlet;

import pers.hdh.beans.Record;
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
}
