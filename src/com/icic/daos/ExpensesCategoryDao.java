package com.icic.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icic.pojos.ExpensesCategory;
import com.icic.utilities.ConnectionPool;

public class ExpensesCategoryDao {
	public void create(ExpensesCategory ec) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql ="insert into expenses_category(exp_catname,exp_catdetails,userid) values(?,?,?)";
			 PreparedStatement pd = c.prepareStatement(sql);
			 pd.setString(1, ec.getExp_catname());
			 pd.setString(2, ec.getExp_catdetails());
			 pd.setInt(3, ec.getUserid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to insert data in expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void remove(ExpensesCategory ec) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql = "delete from Expenses_Category where exp_catid=?";
			 PreparedStatement pd= c.prepareStatement(sql);
			 pd.setInt(1, ec.getExp_catid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to remove  data from expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void edit(ExpensesCategory ec) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql= "update Expenses_Category set exp_catname=?,exp_catdetails=?,userid=? where exp_catid=?";
		 PreparedStatement pd= c.prepareStatement(sql);
		 pd.setString(1, ec.getExp_catname());
		 pd.setString(2, ec.getExp_catdetails());
		 pd.setInt(3, ec.getUserid());
		 pd.setInt(4, ec.getExp_catid());
		 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to edit data in expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 
	 public ExpensesCategory find(int exp_catid) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ExpensesCategory ec = new ExpensesCategory();
		 try {
			 String sql ="select * from expenses_category where exp_catid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setInt(1, exp_catid);
			 ResultSet rs =pd.executeQuery();
			 if(rs.next()) {
				 ec.setExp_catdetails(rs.getString("exp_catdetails"));
				 ec.setExp_catname(rs.getString("exp_catname"));
				 ec.setUserid(rs.getInt("userid"));
				 ec.setExp_catid(exp_catid);
			 }else {
				 return null;
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return ec;
	 }
	 
	 public ArrayList<ExpensesCategory> findAll(){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ArrayList<ExpensesCategory> array = new ArrayList<ExpensesCategory>();
		 try {
			 String sql ="select * from expenses_category";
			 PreparedStatement pd =c.prepareStatement(sql);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 ExpensesCategory ec = new ExpensesCategory();
				 ec.setExp_catdetails(rs.getString("exp_catdetails"));
				 ec.setExp_catname(rs.getString("exp_catname"));
				 ec.setUserid(rs.getInt("userid"));
				 ec.setExp_catid(rs.getInt("exp_catid"));
				 array.add(ec);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
	 }
	 public ArrayList<ExpensesCategory> findAll(int userid){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ArrayList<ExpensesCategory> array = new ArrayList<ExpensesCategory>();
		 try {
			 String sql ="select * from expenses_category where userid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setInt(1, userid);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 ExpensesCategory ec = new ExpensesCategory();
				 ec.setExp_catdetails(rs.getString("exp_catdetails"));
				 ec.setExp_catname(rs.getString("exp_catname"));
				 ec.setUserid(rs.getInt("userid"));
				 ec.setExp_catid(rs.getInt("exp_catid"));
				 array.add(ec);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in expenses_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
	 }

	 
	 
	 	public static  void main(String [] a) {
		ExpensesCategory ec = new ExpensesCategory("entertainment","Movie", 2);
		ExpensesCategoryDao ecd=new ExpensesCategoryDao();
		ecd.create(ec);
		ec =ecd.find(9);
	System.out.println(ec.toString());
	ArrayList<ExpensesCategory> array =ecd.findAll(2);
		if(array!=null) {
			for(ExpensesCategory ec2:array)
				System.out.println(ec2.toString());
		}
	}
}
