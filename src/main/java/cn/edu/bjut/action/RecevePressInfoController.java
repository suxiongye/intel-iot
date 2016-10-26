package cn.edu.bjut.action;

import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.bean.Pir;
import cn.edu.bjut.bean.Press;
import cn.edu.bjut.bean.Temp;
import cn.edu.bjut.dao.PhoneDao;
import cn.edu.bjut.dao.PirDao;
import cn.edu.bjut.dao.PressDao;
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Phone phone = PhoneDao.slectLastPhoneRow();

		String str = req.getParameter("press");

		Press press = new Press();
		int position = Integer.parseInt(str);

		if (position == 0) {
			press.setValue(Press.BED_ROOM);
			Map<String, String> sms = new HashMap<String, String>();

			sms.put("name", phone.getName());
			sms.put("button", "卧室触摸按钮");
			sms.put("position", "");
			SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_press,
					JSON.toJSONString(sms));
		} else if (position == 1) {
			press.setValue(Press.HAND);
			press.setCreateTime(new Timestamp(System.currentTimeMillis()));
			Pir lastPir = PirDao.slectLastPirRow();
			int mint = 10;
			if (lastPir != null) {
				long s = press.getCreateTime().getTime()
						- lastPir.getCreateTime().getTime();

				mint = TimeUtil.getMinte(s);
			}
			if (mint <= 1) {
				Map<String, String> sms = new HashMap<String, String>();
				sms.put("name", phone.getName());
				sms.put("button", "手边按钮");
				sms.put("position", "位置在卫生间，");
				SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_press,
						JSON.toJSONString(sms));
			} else if (mint > 1 && mint <= 5) {
				Map<String, String> sms = new HashMap<String, String>();
				sms.put("name", phone.getName());
				sms.put("button", "手边按钮");
				sms.put("position", "位置较大可能在卫生间，");
				SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_press,
						JSON.toJSONString(sms));
			} else {
				Map<String, String> sms = new HashMap<String, String>();
				sms.put("name", phone.getName());
				sms.put("button", "手边按钮");
				sms.put("position", "位置未知，");
				SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_press,
						JSON.toJSONString(sms));
			}
		}

		press.setCreateTime(new Timestamp(System.currentTimeMillis()));

		int result = PressDao.savePress(press);

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
