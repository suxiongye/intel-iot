package cn.edu.bjut.action;

import cn.edu.bjut.bean.Temp;
import cn.edu.bjut.dao.TempDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Created by ray on 2016/9/14.
 */

@WebServlet(urlPatterns = "/temp")
public class ReceiveTempInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String tempString = req.getParameter("temp");

		Temp temp = new Temp();

		temp.setValue(tempString);
		temp.setCreateTime(new Timestamp(System.currentTimeMillis()));

		int result = TempDao.saveTemp(temp);

		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(result + "");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}
}
