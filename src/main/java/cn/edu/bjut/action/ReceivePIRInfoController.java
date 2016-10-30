package cn.edu.bjut.action;

import cn.edu.bjut.bean.Pir;
import cn.edu.bjut.dao.PirDao;
import cn.edu.bjut.util.Config;

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
@WebServlet(urlPatterns = "/pir")
public class ReceivePIRInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String tempString = req.getParameter("pir");
		Pir pir = new Pir();

		int position = Integer.parseInt(tempString);

		long now = System.currentTimeMillis();
		if (position == 0) {
			pir.setValue(Config.PIR_BATHROOM);
			Config.LAST_PIR = now;
		}
		pir.setCreateTime(new Timestamp(now));

		int result = PirDao.savePir(pir);

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
