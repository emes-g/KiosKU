package konkuk.swarchitecture.team6;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	// 오류 발생하면 실행부로 오류 패스
	public static Connection getConnection() throws Exception{
		// 1. 드라이버 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 2. DB 연결
		String url = String.format("jdbc:mysql://%s/?userSSL=false&user=%s&password=%s",
				DBInfo.IP, DBInfo.USER, DBInfo.PASSWORD);
		return DriverManager.getConnection(url);
	}
}
