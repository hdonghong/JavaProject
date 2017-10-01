package pers.hdh.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;;


/**
 * 通用的servlet，重写service方法
 * 用作其它servlet的父类
 */
//@WebServlet(urlPatterns = {"/base"})
public class BaseServlet extends HttpServlet {
    protected Logger logger = LogManager.getLogger(this.getClass());
    protected String msg = "方法名为空或者其它情况操作";

    /**
     * 通过反射执行指定的方法，返回响应
     * @param req
     * @param resp
     * @throws ServletException
     * @throws Exception
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取指定的方法名
        String methodName = req.getParameter("method");
        logger.debug("执行的方法：" + methodName);

        // 获取方法对象
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 执行方法，获取返回值(返回值是内部路径)
            if (method != null) {
                String result = (String) method.invoke(this, req, resp);
                if (result != null) { // 请求转发
                    req.getRequestDispatcher(result).forward(req, resp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(msg);
            req.setAttribute("msg", "操作错误");
            req.getRequestDispatcher("jsp/message.jsp").forward(req, resp);
        }
    }
}
