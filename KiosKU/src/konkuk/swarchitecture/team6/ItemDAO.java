package konkuk.swarchitecture.team6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAO {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public static ArrayList<Item> getItemFromItemDB() {
		ArrayList<Item> itemList = new ArrayList<Item>();

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);

			String itemTB = "select * from items";
			rs = stmt.executeQuery(itemTB);
			while(rs.next()) {
				String title = rs.getString("title");
				int cost = rs.getInt("cost");	

				itemList.add(new Item(title, cost));
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
		return itemList;
	}

	public static boolean addItem(ArrayList<Item> itemList, Item item) {
		boolean successed = false;

		if(isExistedItem(itemList, item.getTitle()))
			return false;

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);

			String itemTB = "INSERT INTO items VALUES(?, ?)";
			pstmt = conn.prepareStatement(itemTB);
			pstmt.setString(1, item.getTitle());
			pstmt.setInt(2, item.getCost());
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

	public static boolean editItem(ArrayList<Item> itemList, Item oldItem, Item newItem) {
		boolean successed = false;

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);

			// 1. edit title
			if(oldItem.getTitle() != newItem.getTitle()) {
				String updateTitle = "UPDATE items set title=? where cost=?";
				pstmt = conn.prepareStatement(updateTitle);
				pstmt.setString(1, newItem.getTitle());
				pstmt.setInt(2, oldItem.getCost());
				pstmt.executeUpdate();
			}

			// 2. edit cost
			if(oldItem.getCost() != newItem.getCost()) {
				String updateCost = "UPDATE items set cost=? where title=?";
				pstmt = conn.prepareStatement(updateCost);
				pstmt.setInt(1, newItem.getCost());
				pstmt.setString(2, newItem.getTitle());
				pstmt.executeUpdate();
			}
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

	public static boolean deleteItem(ArrayList<Item> itemList, Item item) {
		boolean successed = false;

		if(!isExistedItem(itemList, item.getTitle()))
			return false;

		try {
			// 드라이버 로딩, DB 연결, SQL문 실행을 위한 Statement 객체 획득
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			// SQL문 작성 및 실행
			String kioskDB = "use kiosk";
			stmt.executeUpdate(kioskDB);

			String itemTB = "DELETE FROM items WHERE title=?";
			pstmt = conn.prepareStatement(itemTB);
			pstmt.setString(1, item.getTitle());
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

	public static boolean isExistedItem(ArrayList<Item> itemList, String title) {
		for(Item it : itemList) {
			if(it.getTitle().equals(title))
				return true;
		}
		return false;
	}
}
