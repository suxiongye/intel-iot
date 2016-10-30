package cn.edu.bjut.action;

import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.dao.PhoneDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ray on 2016/9/14.
 */
@WebServlet(urlPatterns = "/setting")
public class SettingController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");

		Phone phone = PhoneDao.selectPhoneByName(name);
		boolean result = false;
		// 若存在则更新号码，不存在则不管
		if (phone != null) {
			phone.setPhone(req.getParameter("phone"));
			result = PhoneDao.updatePhone(phone);
		}

		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("Update: " + result);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
