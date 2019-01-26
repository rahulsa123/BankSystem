package com.icic.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.icic.pojos.Users;
import com.icic.utilities.ConnectionPool;
	public class UsersDao {
	
	public void create(Users u) {
		ConnectionPool cp= ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="insert into Users(username,password,name ,address,mobile,email) values(?,?,?,?,?,?);";
			PreparedStatement ps =c.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getName());
			ps.setString(4, u.getAddrees());
			ps.setString(5, u.getMobile());
			ps.setString(6, u.getEmail());
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to insert data in Users Table "+e);
		}
		finally {
			cp.putConnection(c);
		}
}
	public void edit(Users u) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="update users set username =?,password=?,name=? ,address=?,mobile=?,email=? where uid=?";
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getName());
			ps.setString(4, u.getAddrees());
			ps.setString(5, u.getMobile());
			ps.setString(6, u.getEmail());
			ps.setInt(7, u.getUid());
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to edit data in users Table "+e);
		}finally {
		cp.putConnection(c);	
		}
	}
	
	public void remove(Users u) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="delete from Users where  uid=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u.getUid());
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to remove data from User table");
		}finally {
			cp.putConnection(c);
		}
	}
	
	public Users find(int uid) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		Users u =new Users();
		u.setUid(-1);
		try {
			String sql ="select * from users where uid=?";
			PreparedStatement ps =c.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setName(rs.getString("name"));
				u.setAddrees(rs.getString("address"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setUid(uid);
			}else {
				System.out.println("Data in not in record");
				return null;
			}
		}catch (SQLException e) {
			System.out.println("Unable to find data in Users table "+e);
			u=new Users();;
		}finally {
			cp.putConnection(c);
		}
		return u;
	}
	
	public ArrayList<Users> findAll(){
		ConnectionPool cp =ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Users> array = new ArrayList<Users>();
		try {
			String sql ="select * from users";
			PreparedStatement ps =c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Users u = new Users(rs.getString("username"), rs.getString("password"),rs.getString("name"), rs.getString("address"), rs.getString("mobile"), rs.getString("email"));
				u.setUid(rs.getInt("uid"));
				array.add(u);
			}
		}catch (SQLException e) {
			System.out.println("Unable to find all data in Users table "+e);
			
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	
	public static int checkAvailablity(String uname ,String pwd) {
		int uid=-1;
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="select uid from users where username=? and password =?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, uname);
			pd.setString(2, pwd);
			ResultSet rs =pd.executeQuery();
			if(rs.next()) {
				uid= rs.getInt("uid");
			}
		}catch (SQLException e) {
			System.out.println("Unble to find users "+e);
		}
		return uid;
	}
	
	public static Users authenticate(String uname, String pwd) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		Users u =null;
		try {
			String sql ="select * from users where username=? and password =?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, uname);
			pd.setString(2, pwd);
			ResultSet rs =pd.executeQuery();
			if(rs.next()) {
				u=new Users();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setName(rs.getString("name"));
				u.setAddrees(rs.getString("address"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setUid(rs.getInt("uid"));
			}
		}catch (SQLException e) {
			System.out.println("Unble to find users "+e);
		}
		return u;
	}

	public boolean checkAvailablity(String uname) {
		boolean check=false;
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="select * from users where username=? ";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, uname);
			ResultSet rs =pd.executeQuery();
			if(rs.next()) {
				check=true;
			}
		}catch (SQLException e) {
			System.out.println("Unble to find users "+e);
		}
		return check;
	}
	public int findUserId(String uname) {
		int uid=-1;
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql ="select * from users where username=? ";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, uname);
			ResultSet rs =pd.executeQuery();
			if(rs.next()) {
				uid=rs.getInt("uid");
			}
		}catch (SQLException e) {
			System.out.println("Unble to find UsersId "+e);
		}
	return uid;
	}
	public static void main(String[] args) {
		Users u = new Users("rohan123","rohan321#" ,"pooja" , "17/4 ward no 14", "9981712176", "pooja.et2016@gmail.com");
		u.setUid(2);
		UsersDao ud =new UsersDao();
		//ud.create(u);
		ud.edit(u);
//		ArrayList<Users> array=ud.findAll();
//		u=ud.authenticate("rahul12", "rajen321#");
//		System.out.println(u.toString());
//		int uid=ud.checkAvailablity("rahul123", "rajen321#");
//		if(uid!=-1) {
//			System.out.println("Uid "+uid);
//		}else {
//			System.out.println("user not present");
//		}
//		if(array !=null) {
//			for(Users u1:array) {
//				System.out.println(u1);
//			}
//		}
	}
}
