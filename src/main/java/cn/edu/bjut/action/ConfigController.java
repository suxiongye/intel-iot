package cn.edu.bjut.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.bjut.util.DataBaseUtil;

@WebServlet(urlPatterns = "/config")
public class ConfigController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendRedirect("config.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		// TODO Auto-generated method stub
		if (req.getParameter("address") != null
				&& req.getParameter("port") != null
				&& req.getParameter("dbName") != null
				&& req.getParameter("user") != null
				&& req.getParameter("pwd") != null) {
			if(DataBaseUtil.configDatabase(req.getParameter("address"),
					req.getParameter("port"), req.getParameter("dbName"),
					req.getParameter("user"), req.getParameter("pwd")))
			{
				out.write("database config success");
			}
			else{
				out.write("database config fail");
			}
		}
	}
}
