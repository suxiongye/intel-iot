package cn.edu.bjut.action;

import cn.edu.bjut.bean.Flame;
import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.dao.FlameDao;
import cn.edu.bjut.dao.PhoneDao;
import cn.edu.bjut.util.Config;
import cn.edu.bjut.util.SMSUtil;
import cn.edu.bjut.util.TimeUtil;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ray on 2016/9/14.
 */
@WebServlet(urlPatterns = "/flame")
public class ReceiveFlameInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Phone phone = PhoneDao.selectLastPhoneRow();

		String tempString = req.getParameter("flame");
		int value = Integer.parseInt(tempString);
		Flame flame = new Flame(value);
		long now = System.currentTimeMillis();
		flame.setCreateTime(new Timestamp(now));
		int result = FlameDao.saveFlame(flame);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(result + "");
		if (phone == null)
			return;
		// 若着火
		if (value > 200) {
			// 若第一次或者超过1分钟则发送短信
			if (Config.LAST_FLAME == 0
					|| !TimeUtil.nearTime(Config.LAST_FLAME,
							Config.SMS_INTERNEL)) {
				System.out.println("fire");
				// 发送短信
				Map<String, String> sms = new HashMap<String, String>();
				sms.put("name", phone.getName());
				sms.put("detail", "被触发");
				SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_fire,
						JSON.toJSONString(sms));
				// 更新着火时间
				Config.LAST_FLAME = now;
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
