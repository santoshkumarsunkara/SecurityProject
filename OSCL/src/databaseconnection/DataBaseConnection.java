package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.CutomerBean;

public class DataBaseConnection {

	boolean flag = false;
	Connection getDbConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
		con.commit();
		return con;
	}
	public int customerRegistration(CutomerBean obj) throws ClassNotFoundException, SQLException {
			int status=0;
			
		Connection con=getDbConnection();	
		PreparedStatement ps=con.prepareStatement("INSERT INTO CUSTOMER_TABLE VALUES(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1,obj.getEmail());
		ps.setString(2,obj.getFname());
		ps.setString(3,obj.getLname());
		ps.setString(4,obj.getMobileno());
		ps.setString(5,obj.getGender());
		ps.setString(6,obj.getCity());
		ps.setString(7,obj.getDob());
		ps.setString(8,obj.getPincode());
		ps.setString(9,obj.getHouseno());
		ps.setString(10,obj.getPassword());
		status=ps.executeUpdate();
		
		return status;
	}
	public boolean customerLogin(String username, String password) throws ClassNotFoundException, SQLException {
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER_TABLE");
		while (rs.next()) {
			String usernamedb = rs.getString(1);
			String passworddb = rs.getString(10);
			if (usernamedb!=null && passworddb!=null &&usernamedb.trim().equals(username.trim()) && passworddb.trim().equals(password.trim())) 
				{
					flag = true;
				}
			
		}
		con.close();
		return flag;
		
	}
	public int updateMarks(String email, int i) throws ClassNotFoundException, SQLException {
		Connection con=getDbConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO MARKS_TABLE VALUES(?,?)");
		ps.setString(1, email);
		ps.setInt(2, i);
		return ps.executeUpdate();
	}
	public int paymentDetails(String cname, String cnumber, String cvvnumber, String mobnumber, String city,
			String pincode, String hno,String email) throws ClassNotFoundException, SQLException {
		
		Connection con=getDbConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO CARD_DETAILS VALUES(?,?,?,?,?,?,?,?)");
		ps.setString(1, cname);
		ps.setString(2, cnumber);
		ps.setString(3, cvvnumber);
		ps.setString(4, mobnumber);
		ps.setString(5, city);
		ps.setString(6, pincode);
		ps.setString(7, hno);
		ps.setString(8, email);
		return ps.executeUpdate();
	}
	public boolean checkPercentage(String email) throws ClassNotFoundException, SQLException {
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM MARKS_TABLE");
		while (rs.next()) {
			String emaildb = rs.getString(1);
			int percentagedb = rs.getInt(2);
			if (emaildb!=null  && emaildb.trim().equals(email.trim()) && percentagedb>=60) 
				{
					flag = true;
				}
			
		}
		con.close();
		return flag;
	}
	public boolean checkAppliedOrNot(String email) throws ClassNotFoundException, SQLException {
		boolean status=false;
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM CARD_DETAILS");
		while (rs.next()) {
			String emaildb = rs.getString(8);
			if (emaildb!=null  && emaildb.trim().equals(email.trim())) 
				{
					status = true;
				}
			
		}
		con.close();
		
		return status;
	}
	public boolean adminLogin(String username, String password) throws ClassNotFoundException, SQLException {
		Connection con = getDbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINVALUE_TABLE");
		while (rs.next()) {
			String usernamedb = rs.getString(1);
			String passworddb = rs.getString(2);
			if (usernamedb!=null && passworddb!=null &&usernamedb.trim().equals(username.trim()) && passworddb.trim().equals(password.trim())) 
				{
					flag = true;
				}
			
		}
		con.close();
		return flag;
		
	}
}

	

