package cn.edu.bjut.action;

import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.dao.PhoneDao;
import com.alibaba.fastjson.JSON;

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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String phone = req.getParameter("phone");
        String name = req.getParameter("name");


        Phone lastPhone = PhoneDao.slectLastPhoneRow();

        lastPhone.setName(name);
        lastPhone.setPhone(phone);

        boolean result = PhoneDao.updatePhone(lastPhone);

        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(result+"");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req,resp);
    }
}
