package cn.edu.bjut.dao;

import cn.edu.bjut.bean.Flame;
import cn.edu.bjut.util.DataBaseUtil;

import java.sql.*;

/**
 * Created by ray on 2016/9/14.
 */
public class FlameDao {

	public static int saveFlame(Flame flame) {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "insert into iot_flame (value, createTime) values(?,?)";

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, flame.getValue());
			ps.setTimestamp(2, flame.getCreateTime());

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Flame selectLastFlameRow() {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "select  *  from iot_flame where createTime = (select max(createTime) from iot_flame)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Flame flame = new Flame();

				flame.setId(results.getInt("id"));
				flame.setValue(results.getString("value"));
				flame.setCreateTime(results.getTimestamp("createTime"));

				return flame;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
