package cn.edu.bjut.dao;

import cn.edu.bjut.bean.Press;
import cn.edu.bjut.util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ray on 2016/9/14.
 */
public class PressDao {

	public static int savePress(Press press) {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "insert into iot_press (value, createTime) values(?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, press.getValue());
			ps.setTimestamp(2, press.getCreateTime());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Press selectLastPressRow() {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "select  *  from iot_press where createTime = (select max(createTime) from iot_press)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Press press = new Press();

				press.setId(results.getInt("id"));
				press.setValue(results.getString("value"));
				press.setCreateTime(results.getTimestamp("createTime"));

				return press;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
