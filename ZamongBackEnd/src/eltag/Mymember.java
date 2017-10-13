package eltag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mymember {
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	
	public static boolean isMember(String user, String pass){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin://127.0.0.1:1521:orcl";
			//계정 정보
			String use="jsp";
			//계정 패스워드
			String password="JSP";
			conn = DriverManager.getConnection(url,use,password);
			
			String sql = "select * from MEMBER where ID='"+user+"' and PWD='"+pass+"'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				System.out.println("된듯");
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	
}//class
