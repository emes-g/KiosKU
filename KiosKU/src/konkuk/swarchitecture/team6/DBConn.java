package konkuk.swarchitecture.team6;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	// 오류 발생하면 실행부로 오류 패스
	public static Connection getConnection() throws Exception{
		// 1. 드라이버 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. DB 연결
		String url = "jdbc:mysql://127.0.0.1/?userSSL=false&user=root&password=0000";
		return DriverManager.getConnection(url);
	}
}
