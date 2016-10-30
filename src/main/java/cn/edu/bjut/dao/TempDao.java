package cn.edu.bjut.dao;

import cn.edu.bjut.bean.Temp;
import cn.edu.bjut.util.DataBaseUtil;

import java.sql.*;

/**
 * Created by ray on 2016/9/14.
 */
public class TempDao {

    public static int saveTemp(Temp temp) {

        Connection connection = DataBaseUtil.getConnection();

        String sql = "insert into iot_temp (value, createTime) values(?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, temp.getValue());
            ps.setTimestamp(2, temp.getCreateTime());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Temp selectLastTempRow() {

        Connection connection = DataBaseUtil.getConnection();

        String sql = "select  *  from iot_temp where createTime = (select max(createTime) from iot_temp)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Temp temp = new Temp();

                temp.setId(results.getInt("id"));
                temp.setValue(results.getString("value"));
                temp.setCreateTime(results.getTimestamp("createTime"));

                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
