package cn.edu.bjut.dao;

import cn.edu.bjut.bean.Pir;
import cn.edu.bjut.bean.Press;
import cn.edu.bjut.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ray on 2016/9/14.
 */
public class PirDao {

    public static int savePir(Pir pir) {

        Connection connection = DataBaseUtil.getConnection();

        String sql = "insert into iot_pir (value, createTime) values(?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pir.getValue());
            ps.setTimestamp(2, pir.getCreateTime());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Pir slectLastPirRow() {

        Connection connection = DataBaseUtil.getConnection();

        String sql = "select  *  from iot_pir where createTime = (select max(createTime) from iot_pir)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Pir pir = new Pir();

                pir.setId(results.getInt("id"));
                pir.setValue(results.getString("value"));
                pir.setCreateTime(results.getTimestamp("createTime"));

                return pir;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
