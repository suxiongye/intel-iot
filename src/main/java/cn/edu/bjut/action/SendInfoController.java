package cn.edu.bjut.action;

import cn.edu.bjut.bean.Flame;
import cn.edu.bjut.bean.Pir;
import cn.edu.bjut.bean.Press;
import cn.edu.bjut.bean.Temp;
import cn.edu.bjut.dao.FlameDao;
import cn.edu.bjut.dao.PirDao;
import cn.edu.bjut.dao.PressDao;
import cn.edu.bjut.dao.TempDao;
import cn.edu.bjut.util.TimeUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ray on 2016/9/14.
 */
@WebServlet(urlPatterns = "/allInfo")
public class SendInfoController  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long now =  System.currentTimeMillis();

        Map<String, Object> map = new HashMap<String, Object>();

        Flame flame = FlameDao.slectLastFlameRow();
        Temp temp = TempDao.slectLastTempRow();
        Pir pir = PirDao.slectLastPirRow();
        Press press = PressDao.slectLastPressRow();

        int flameMin = TimeUtil.getMinte(now - flame.getCreateTime().getTime());
        int pirMin = TimeUtil.getMinte(now - pir.getCreateTime().getTime());
        int pressMin = TimeUtil.getMinte(now - press.getCreateTime().getTime());

        if(flameMin <=1 )
             map.put("flame", flame);
        else if(pirMin <= 1)
            map.put("pir", pir);
        else if(pressMin <=1)
            map.put("press", press);
        map.put("temp", temp);

        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSON(map).toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
