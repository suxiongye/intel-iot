package cn.edu.bjut.dao;

import cn.edu.bjut.bean.Phone;
import cn.edu.bjut.util.DataBaseUtil;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ray on 2016/9/14.
 */
public class PhoneDao {

	public static Phone selectLastPhoneRow() {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "select  *  from iot_phone where id = (select max(id) from iot_phone)";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Phone phone = new Phone();
				phone.setId(results.getInt("id"));
				phone.setPhone(results.getString("phone"));
				phone.setName(results.getString("name"));
				return phone;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Phone selectPhoneByName(String name) {
		Connection connection = DataBaseUtil.getConnection();
		String sql = "select  *  from iot_phone where name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Phone phone = new Phone();
				phone.setId(results.getInt("id"));
				phone.setPhone(results.getString("phone"));
				phone.setName(results.getString("name"));
				return phone;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updatePhone(Phone phone) {

		Connection connection = DataBaseUtil.getConnection();

		String sql = "update iot_phone set name = ?, phone= ? where id = "
				+ phone.getId();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, phone.getName());
			ps.setString(2, phone.getPhone());

			int result = ps.executeUpdate();

			if (result > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
