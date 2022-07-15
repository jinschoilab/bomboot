package io.sarc.bomboot.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiLog {
	private static Logger log = LoggerFactory.getLogger(ApiLog.class);

	@Value("${spring.application.name}")
	private String service;

	@Autowired
	private DataSource dataSource;
		
	public void add(String api, String value) {
		String sql = "INSERT INTO api_log ";
		sql += " ( currenttime, service, api, value )";
		sql += " VALUES ( default, ?, ?, ? )";
		
		try (Connection conn = dataSource.getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement(sql);) {
			preparedStmt.setString(1, service);
			preparedStmt.setString(2, api);
			preparedStmt.setString(3, value);
			preparedStmt.execute();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
		}
	}

	public int get(String api) {
		int result = 0;
		
		try (Connection conn = dataSource.getConnection();
			PreparedStatement ps = createPreparedStatement(conn, api);
			ResultSet rs = ps.executeQuery()) {

			while ( rs.next() ) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
		}
		
		return result;
	}
	
	private PreparedStatement createPreparedStatement(Connection conn, String api) throws SQLException {
		String sql = "SELECT COUNT(*)";
		sql += "   FROM api_log";
		sql += "  WHERE service = ?";
		sql += "    AND api = ?";

	    PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, service);
		ps.setString(2, api);
	    return ps;
	}
}