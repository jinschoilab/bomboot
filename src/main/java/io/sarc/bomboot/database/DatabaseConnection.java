package io.sarc.bomboot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
	private static Logger log = LoggerFactory.getLogger(DatabaseConnection.class);

	public List<String[]> exec() {
		final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "aaa.bbb.svc.cluster.local";
		final String DB_PORT = "3306";
		final String DB_NAME = "bomdb";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, "bomusername", "bompassword");
			if (conn != null) {
				log.info("DB Connection");
			}
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		String[] result = new String[3];
		List<String[]> resultList = new ArrayList<String[]>();
		
		try {
			String sql = "SELECT aaa, bbb, ccc ";
			sql += "  FROM ddd ";
			sql += "  ORDER BY eee DESC";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				
				resultList.add(result);
			}
		} catch (SQLException e) {
			log.error("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultList;
	}
}