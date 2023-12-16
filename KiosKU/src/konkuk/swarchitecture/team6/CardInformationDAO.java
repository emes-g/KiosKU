package konkuk.swarchitecture.team6;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CardInformationDAO {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static ArrayList<CardInformation> generateCardDatabase() {
		ArrayList<CardInformation> CardDatabase = new ArrayList<CardInformation>();
		
		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			
			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);
			
			String cardInfoTB = "select * from cardInformation";
			rs = stmt.executeQuery(cardInfoTB);
			while(rs.next()) {
				String cardID = rs.getString("cardID");
				int cardLimit = rs.getInt("cardLimit");	
				int preAuthorized = rs.getInt("preAuthorized");
				int currentUsed = rs.getInt("currentUsed");
				
				CardDatabase.add(new CardInformation(cardID, cardLimit, preAuthorized, currentUsed));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) try {conn.close();} catch(SQLException se) {}
			if(stmt != null) try {stmt.close();} catch(SQLException se) {}
			if(rs != null) try {rs.close();} catch(SQLException se) {}
		}
		return CardDatabase;
	}
}
