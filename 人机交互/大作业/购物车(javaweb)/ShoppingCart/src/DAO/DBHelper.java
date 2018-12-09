package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	
	public String url= "jdbc:mysql://172.16.73.89:3306/test?useUnicode=true&characterEncoding=UTF-8";
	Connection connection;
	
	
	public DBHelper(String sqlName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败");
			e.printStackTrace();			
		}
		try {
			connection = (Connection) DriverManager.getConnection(url, sqlName, password);
			System.out.println("链接数据库成功");
		} catch (SQLException e) {
			System.out.println("链接数据库失败");
			e.printStackTrace();
		}
	}
	
	public void ExecuteInsert(String sql) {
		try {
			Statement stmt=connection.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("ExecuteInsert()Exception:SQLException");
			e.printStackTrace();
		}
	}
	
	public void ExecuteDelete(String sql){
		try{	   
			Statement stmt=connection.createStatement();
			stmt.execute(sql);
		}catch(SQLException e){
			System.out.println("ExecuteDelete()Exception:SQLException");
			e.printStackTrace();
		}
	}
	
	public void ExecuteUpdate(String sql) {
		
		try {
			Statement stmt=connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("ExecuteUpdate()Exception:SQLException");
			e.printStackTrace();
		}
	}
	
	public ResultSet ExecuteQuery(String sql) throws SQLException {		   
		  
		Statement stmt=connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	   }
//	public static void main(String[] args) {
//		DBHelper db= new DBHelper("root", "root");
//	}
	
}