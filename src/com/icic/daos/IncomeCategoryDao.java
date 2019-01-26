package com.icic.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icic.pojos.IncomeCategory;
import com.icic.utilities.ConnectionPool;

public class IncomeCategoryDao {
	public void create(IncomeCategory ic) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql ="insert into income_category(inc_catname,inc_catdetails,userid) values(?,?,?)";
			 PreparedStatement pd = c.prepareStatement(sql);
			 pd.setString(1, ic.getInc_catname());
			 pd.setString(2, ic.getInc_catdetails());
			 pd.setInt(3, ic.getUserid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to insert data in income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void remove(IncomeCategory ic) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql = "delete from income_category where inc_catid=?";
			 PreparedStatement pd= c.prepareStatement(sql);
			 pd.setInt(1, ic.getInc_catid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to remove  data from income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void edit(IncomeCategory ic) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql= "update income_category set inc_catname=?,inc_catdetails=?,userid=? where inc_catid=?";
		 PreparedStatement pd= c.prepareStatement(sql);
		 pd.setString(1, ic.getInc_catname());
		 pd.setString(2, ic.getInc_catdetails());
		 pd.setInt(3, ic.getUserid());
		 pd.setInt(4, ic.getInc_catid());
		 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to edit data in income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 
	 public IncomeCategory find(int inc_catid) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 IncomeCategory ic=new IncomeCategory();
		 try {
			 String sql ="select * from income_category where inc_catid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setInt(1, inc_catid);
			 ResultSet rs =pd.executeQuery();
			 if(rs.next()) {
				 ic.setInc_catdetails(rs.getString("inc_catdetails"));
				 ic.setInc_catname(rs.getString("inc_catname"));
				 ic.setUserid(rs.getInt("userid"));
				 ic.setInc_catid(inc_catid);
			 }
			 else {
				 System.out.println("data is not present");
				 return null;
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return ic;
	 }
	 
	 public ArrayList<IncomeCategory> findAll(){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ArrayList<IncomeCategory> array = new ArrayList<IncomeCategory>();
		 try {
			 String sql ="select * from income_category";
			 PreparedStatement pd =c.prepareStatement(sql);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 IncomeCategory ic = new IncomeCategory();
				 ic.setInc_catdetails(rs.getString("inc_catdetails"));
				 ic.setInc_catname(rs.getString("inc_catname"));
				 ic.setUserid(rs.getInt("userid"));
				 ic.setInc_catid(rs.getInt("inc_catid"));
				 array.add(ic);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
	 }
	 
	 public ArrayList<IncomeCategory> findAll(int userid){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ArrayList<IncomeCategory> array = new ArrayList<IncomeCategory>();
		 try {
			 String sql ="select * from income_category where userid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setInt(1, userid);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 IncomeCategory ic = new IncomeCategory();
				 ic.setInc_catdetails(rs.getString("inc_catdetails"));
				 ic.setInc_catname(rs.getString("inc_catname"));
				 ic.setUserid(rs.getInt("userid"));
				 ic.setInc_catid(rs.getInt("inc_catid"));
				 array.add(ic);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in income_category table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
		 
	 }
	 
	public static void main(String [] ags){
 	IncomeCategory ic=new IncomeCategory("direct","direct",2);
		IncomeCategoryDao icd = new IncomeCategoryDao();
		ic=icd.find(5);
		System.out.println(ic.toString());
//		icd.create(ic);
//		ArrayList<IncomeCategory> array = icd.findAll();
//		if(array!=null)
//			for(IncomeCategory in1:array)
//		System.out.println(in1.toString());
	}

}
