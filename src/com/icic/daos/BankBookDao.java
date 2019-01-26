package com.icic.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.icic.pojos.BankBook;
import com.icic.utilities.ConnectionPool;


public class BankBookDao {
	 public void create(BankBook bb) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql ="insert into bank_book(account,tran_date,amount,userid,operation) values(?,?,?,?,?)";
			 PreparedStatement pd = c.prepareStatement(sql);
			 pd.setString(1, bb.getAccount());
			 java.sql.Date date = new java.sql.Date(bb.getTran_date().getTime());
			 pd.setDate(2, date);
			 pd.setDouble(3, bb.getAmount());
			 pd.setInt(4, bb.getUserid());
			 pd.setString(5, bb.getOperation());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to insert data in bank_book table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void remove(BankBook bb) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql ="delete from bank_book where acid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setInt(1, bb.getAcid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to remove  data from bank_book table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 public void edit(BankBook bb) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 try {
			 String sql ="update bank_book set account=? ,tran_date=?,amount=?,userid=?,operation=? where acid=?";
			 PreparedStatement pd = c.prepareStatement(sql);
			 pd.setString(1, bb.getAccount());
			 java.sql.Date date = new java.sql.Date(bb.getTran_date().getTime());
			 pd.setDate(2, date);
			 pd.setDouble(3, bb.getAmount());
			 pd.setInt(4, bb.getUserid());
			 pd.setString(5, bb.getOperation());
			 pd.setInt(6, bb.getAcid());
			 pd.executeUpdate();
		 }catch(SQLException e) {
			 System.out.println("Unable to edit data in bank_book table " +e);
		 }finally {
			cp.putConnection(c);
		}
	 }
	 
	 public BankBook find(int acid) {
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 BankBook bb = new BankBook();
		 try {
			 String sql ="select * from bank_book where acid=?";
			 PreparedStatement pd = c.prepareStatement(sql);
			 pd.setInt(1, acid);
			 ResultSet rs = pd.executeQuery();
			 if(rs.next()) {
				 bb.setAccount(rs.getString("account"));
				 bb.setAmount(rs.getDouble("amount"));
				 bb.setOperation(rs.getString("operation"));
				 java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				 bb.setTran_date(date);
				 bb.setUserid(rs.getInt("userid"));
				 bb.setAcid(acid);
			 }else {
				 return null;
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data in bank_book table " +e);
			 bb=null;
		 }finally {
			cp.putConnection(c);
		}
		 return bb;
	 }
	 
	 public ArrayList<BankBook> findAll(){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 ArrayList<BankBook> array =new ArrayList<BankBook>();
		 try {
			 String sql="select * from bank_book";
			 PreparedStatement pd =c.prepareStatement(sql);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 BankBook bb = new BankBook();
				 bb.setAccount(rs.getString("account"));
				 bb.setAmount(rs.getDouble("amount"));
				 bb.setOperation(rs.getString("operation"));
				 java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				 bb.setTran_date(date);
				 bb.setUserid(rs.getInt("userid"));
				 bb.setAcid(rs.getInt("acid"));
				 array.add(bb);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to insert data in bank_book table " +e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
	 }
	 public ArrayList<BankBook> findAllDateWise(String sdate, String edate, int userid){
		 ConnectionPool cp = ConnectionPool.getInstance();
		 cp.initialize();
		 Connection c = cp.getConnection();
		 java.util.Date startingDate=null,endingDate=null;
		 try {
		 startingDate = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		 endingDate = new SimpleDateFormat("yyyy-MM-dd").parse(edate);
		 if(startingDate.after(endingDate)) {
			 System.out.println("Invalid Range");
		 }
		 }catch(ParseException e) {
			 System.out.println("Invalid data Formate");
		 }
		// System.out.println(startingDate+" "+endingDate);
		 ArrayList<BankBook> array =new ArrayList<BankBook>();
		 try {
			 String sql="select * from bank_book where tran_date>=?  and tran_date<=? and userid=?";
			 PreparedStatement pd =c.prepareStatement(sql);
			 pd.setDate(1, new java.sql.Date(startingDate.getTime()));
			 pd.setDate(2, new java.sql.Date(endingDate.getTime()));
			 pd.setInt(3, userid);
			 ResultSet rs =pd.executeQuery();
			 while(rs.next()) {
				 BankBook bb = new BankBook();
				 bb.setAccount(rs.getString("account"));
				 bb.setAmount(rs.getDouble("amount"));
				 bb.setOperation(rs.getString("operation"));
				 java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				 bb.setTran_date(date);
				 bb.setUserid(rs.getInt("userid"));
				 bb.setAcid(rs.getInt("acid"));
				 array.add(bb);
			 }
		 }catch(SQLException e) {
			 System.out.println("Unable to find data "+e);
		 }finally {
			cp.putConnection(c);
		}
		 return array;
	 }
	 
	 
	 
	 public static void main(String[] args) {
		//BankBook bb = new BankBook("fixed",new java.util.Date(118,9,1),6000,2,"deposit");
		BankBookDao bbd = new BankBookDao();
		//bbd.create(bb);
//		BankBook bb = bbd.find(6);
//		if(bb!=null)
//		System.out.println("hhdh"+bb.toString());
		ArrayList<BankBook> array=bbd.findAllDateWise("2018-11-01","2018-11-30", 8);
		//System.out.println(array.size());
		if(array!=null) {
			for(BankBook bb1:array)
			System.out.println(bb1.toString());	
			}

	}

}
