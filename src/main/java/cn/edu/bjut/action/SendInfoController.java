package cn.edu.bjut.action;

import cn.edu.bjut.bean.Flame;
import cn.edu.bjut.bean.Pir;
import cn.edu.bjut.bean.Press;
import cn.edu.bjut.bean.Temp;
import cn.edu.bjut.dao.FlameDao;
import cn.edu.bjut.dao.PirDao;
import cn.edu.bjut.dao.PressDao;
import cn.edu.bjut.dao.TempDao;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ray on 2016/9/14.
 */
@WebServlet(urlPatterns = "/allInfo")
public class SendInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map<String, Object> map = new HashMap<String, Object>();

		Flame flame = FlameDao.selectLastFlameRow();
		Temp temp = TempDao.selectLastTempRow();
		Pir pir = PirDao.selectLastPirRow();
		
		Press press = PressDao.selectLastPressRow();

		if (temp != null) {
			map.put("temp", temp);
		}
		if (flame != null) {
			map.put("flame", flame);
		}
		if (pir != null) {
			map.put("pir", pir);
		}
		if (press != null) {
			map.put("press", press);
		}

		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(JSON.toJSON(map).toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
