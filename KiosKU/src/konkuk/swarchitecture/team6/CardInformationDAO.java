package konkuk.swarchitecture.team6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CardInformationDAO {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public static ArrayList<CardInformation> getCardInfoFromCardDB() {
		ArrayList<CardInformation> CardDatabase = new ArrayList<CardInformation>();

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);

			String cardTB = "select * from cards";
			rs = stmt.executeQuery(cardTB);
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
	
	public static boolean updatePreAuthorized(String cardID, int preAuthorized, boolean isAdd) {
		boolean successed = false;
		int oldPreAuthorized = 0;

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);
			
			// 현재 가승인 금액 불러오기
			String cardTB = "select * from cards where cardID=" + cardID;
			rs = stmt.executeQuery(cardTB);		
			while(rs.next())
				oldPreAuthorized = rs.getInt("preAuthorized");	// cardID는 무조건 존재

			// DB 가승인 금액 갱신
			cardTB = "UPDATE cards set preAuthorized=? where cardID=?";
			pstmt = conn.prepareStatement(cardTB);
			if(isAdd)
				pstmt.setInt(1, oldPreAuthorized + preAuthorized);
			else
				pstmt.setInt(1, oldPreAuthorized - preAuthorized);
			pstmt.setString(2, cardID);
			pstmt.executeUpdate();

			successed = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) try {conn.close();} catch(SQLException se) {}
			if(stmt != null) try {stmt.close();} catch(SQLException se) {}
			if(rs != null) try {rs.close();} catch(SQLException se) {}
		}		
		return successed;
	}
	
	public static boolean updateCurrentUsed(String cardID, int price) {
		boolean successed = false;
		int currentUsed = 0;

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);
			
			// 현재 사용액 불러오기
			String cardTB = "select * from cards where cardID=" + cardID;
			rs = stmt.executeQuery(cardTB);		
			while(rs.next())
				currentUsed = rs.getInt("currentUsed");	// cardID는 무조건 존재

			// 현재 사용액 갱신
			cardTB = "UPDATE cards set currentUsed=? where cardID=?";
			pstmt = conn.prepareStatement(cardTB);
			pstmt.setInt(1, currentUsed + price);
			pstmt.setString(2, cardID);
			pstmt.executeUpdate();

			successed = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null) try {conn.close();} catch(SQLException se) {}
			if(stmt != null) try {stmt.close();} catch(SQLException se) {}
			if(rs != null) try {rs.close();} catch(SQLException se) {}
		}		
		return successed;
	}
}
