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
		flame.setCreateTime(new Timestamp(System.currentTimeMillis()));
		int result = FlameDao.saveFlame(flame);

		// 若着火
		if (value > 200) {
			// 发送短信
			if (Config.LAST_FLAME == 0 ) {
				System.out.println("fire");
				lastFireTime = flame.getCreateTime();
				// 发送短信
				Map<String, String> sms = new HashMap<String, String>();
				sms.put("name", phone.getName());
				sms.put("detail", "被触发");
				SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_fire,
						JSON.toJSONString(sms));
				System.out.println(phone.getPhone());
			} else {
				long times = flame.getCreateTime().getTime()
						- lastFireTime.getTime();
				System.out.println("times:"+times);
				int min = TimeUtil.getMinte(times);
				System.out.println("min:" + min);
				// 若超过五分钟则提示，否则不重复发送
				if (min > 1) {
					// 发送短信
					Map<String, String> sms = new HashMap<String, String>();
					sms.put("name", phone.getName());
					sms.put("detail", "被触发");
					SMSUtil.sendSms(phone.getPhone(), SMSUtil.template_fire,
							JSON.toJSONString(sms));
					lastFireTime = flame.getCreateTime();
				}
			}
		}

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
