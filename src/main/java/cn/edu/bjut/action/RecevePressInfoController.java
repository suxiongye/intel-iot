package cn.edu.bjut.action;

import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.bean.Press;
import cn.edu.bjut.dao.PhoneDao;
import cn.edu.bjut.dao.PressDao;
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
@WebServlet(urlPatterns = "/press")
public class RecevePressInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Phone phone = PhoneDao.selectLastPhoneRow();

		String str = req.getParameter("press");

		Press press = new Press();
		long now = System.currentTimeMillis();
		press.setCreateTime(new Timestamp(now));

		// 根据类型设置短信
		Map<String, String> sms = new HashMap<String, String>();

		int position = Integer.parseInt(str);

		if (position == 0) {
			press.setValue(Config.PRESS_BEDROOM);
			sms.put("button", "卧室触摸按钮");
			sms.put("position", "");
		} else if (position == 1) {
			press.setValue(Config.PRESS_HAND);
			sms.put("button", "手边按钮");
			// 判断位置
			if (TimeUtil.nearTime(Config.LAST_PIR, Config.MUST_TIME)) {
				sms.put("position", "位置在卫生间，");
			} else if (TimeUtil.nearTime(Config.LAST_PIR, Config.MAYBE_TIME)) {
				sms.put("position", "位置较大可能在卫生间，");
			} else {
				sms.put("position", "位置未知，");
			}
		} else {
			return;
		}
		int result = PressDao.savePress(press);

		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(result + "");

		if (phone == null) {
			return;
		}
		sms.put("name", phone.getName());
		// 若一分钟内发送过则不再发送
		if (Config.LAST_PRESS == 0
				|| !TimeUtil.nearTime(Config.LAST_PRESS, Config.SMS_INTERNEL)) {
			Config.LAST_PRESS = now;
			SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_press,
					JSON.toJSONString(sms));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}

}
