package cn.edu.bjut.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ray on 2016/9/14.
 */
public class DataBaseUtil {

	private static String driver = "com.mysql.jdbc.Driver";

	private static String url = "jdbc:mysql://115.28.160.247:3306/intel_iot";

	private static String user = "root";

	private static String passwd = "root";

	private static Connection conn = null;

	public static Connection getConnection() {

		try {
			if (conn != null && conn.isClosed()!=true)
				return conn;
			else {
				try {

					Class.forName(driver);
					conn = DriverManager
							.getConnection(url, user, passwd);

					return conn;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection(Connection connection) {

		try {

			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//设置数据库
	public static boolean configDatabase(String address, String port, String dbName, String user, String pwd){
		DataBaseUtil.url = "jdbc:mysql://"+address+":"+port+"/"+dbName;
		DataBaseUtil.user = user;
		DataBaseUtil.passwd = pwd;
		//重置数据库
		conn = null;
		if(getConnection()!=null) return true;
		else return false;
	}

}
