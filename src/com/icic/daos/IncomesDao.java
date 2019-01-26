package com.icic.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.icic.pojos.BankBook;
import com.icic.pojos.CashBook;
import com.icic.pojos.Incomes;
import com.icic.utilities.ConnectionPool;

public class IncomesDao {
	public void create(Incomes i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		java.sql.Date date =null;
		try {
			String sql="insert into incomes(inc_ac,userid,inc_catid,amount,tran_date,receiveby,remark) values(?,?,?,?,?,?,?)";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, i.getInc_ac());
			pd.setInt(2, i.getUserid());
			pd.setInt(3, i.getInc_catid());
			pd.setDouble(4, i.getAmount());
			date = new java.sql.Date(i.getTran_date().getTime());
			pd.setDate(5, date);
			pd.setString(6, i.getReceiveby());
			pd.setString(7, i.getRemark());
			pd.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Unable to insert data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
		if(i.getReceiveby().equalsIgnoreCase("cash")) {
			CashBookDao cbd= new CashBookDao();
			CashBook cb = new CashBook(i.getInc_ac(), date,i.getAmount(), i.getUserid(), "Recevie");
			cbd.create(cb);
			}else {
				BankBookDao bbd = new BankBookDao();
				BankBook bb = new BankBook(i.getInc_ac(), date, i.getAmount(), i.getUserid(), "Recevie");
				bbd.create(bb);
			}
		
	}
	
	public void edit(Incomes i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql="update incomes set inc_ac=?,userid=?,inc_catid=?,amount=?,tran_date=?,receiveby=?,remark=? where inc_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, i.getInc_ac());
			pd.setInt(2, i.getUserid());
			pd.setInt(3, i.getInc_catid());
			pd.setDouble(4, i.getAmount());
			java.sql.Date date = new java.sql.Date(i.getTran_date().getTime());
			pd.setDate(5, date);
			pd.setString(6, i.getReceiveby());
			pd.setString(7, i.getRemark());
			pd.setInt(8, i.getInc_id());
			pd.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Unable to edit data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
	}
	
	public void remove(Incomes i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql="delete from incomes where inc_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1, i.getInc_id());
			pd.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Unable to remove data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
	}
	public Incomes find(int inc_id) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		Incomes i = new Incomes();
		try {
			String sql="select * from incomes where inc_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1, inc_id);
			ResultSet rs=pd.executeQuery();
			if(rs.next()) {
				i.setAmount(rs.getDouble("amount"));
				i.setInc_ac(rs.getString("inc_ac"));
				i.setInc_catid(rs.getInt("inc_catid"));
				i.setInc_id(rs.getInt("inc_id"));
				i.setReceiveby(rs.getString("receiveby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
			}else {
				System.out.println("Data not present in Incomes table");
				return null;
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
		return i;
	}
	public ArrayList<Incomes> findAll(){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Incomes> array = new ArrayList<Incomes>();
		try {
			String sql="select * from incomes";
			PreparedStatement pd = c.prepareStatement(sql);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Incomes i = new Incomes();
				i.setAmount(rs.getDouble("amount"));
				i.setInc_ac(rs.getString("inc_ac"));
				i.setInc_catid(rs.getInt("inc_catid"));
				i.setInc_id(rs.getInt("inc_id"));
				i.setReceiveby(rs.getString("receiveby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	public ArrayList<Incomes> findAll(int userid){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Incomes> array = new ArrayList<Incomes>();
		try {
			String sql="select * from incomes where userid=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1 ,userid);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Incomes i = new Incomes();
				i.setAmount(rs.getDouble("amount"));
				i.setInc_ac(rs.getString("inc_ac"));
				i.setInc_catid(rs.getInt("inc_catid"));
				i.setInc_id(rs.getInt("inc_id"));
				i.setReceiveby(rs.getString("receiveby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	
	public ArrayList<Incomes> findAllDateWise(String sdate, String edate, int userid){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Incomes> array = new ArrayList<Incomes>();
		try {
			java.util.Date startd= new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			java.util.Date endd= new SimpleDateFormat("yyyy-MM-dd").parse(edate);
			System.out.println(startd+"  "+endd);
			if(startd.after(endd)) {
				throw new  ParseException("Invalid range of date",1);
			}
			java.sql.Date sd=new java.sql.Date(startd.getTime());
			java.sql.Date ed =new java.sql.Date(endd.getTime());
			
			String sql="select * from incomes where tran_date>=? and tran_date<=? and userid =?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setDate(1, sd);
			pd.setDate(2, ed);
			pd.setInt(3, userid);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Incomes i = new Incomes();
				i.setAmount(rs.getDouble("amount"));
				i.setInc_ac(rs.getString("inc_ac"));
				i.setInc_catid(rs.getInt("inc_catid"));
				i.setInc_id(rs.getInt("inc_id"));
				i.setReceiveby(rs.getString("receiveby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in incomes table "+e);
		}catch (ParseException e) {
			System.out.println("Unable to convert date in incomes table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	public static void main(String[] args) {
		//Incomes i =new Incomes("fixed", 1,3, 10090.9, new java.util.Date(), "UPI", "FEE");
		IncomesDao id = new IncomesDao();
		//id.create(i);
//		i.setInc_id(3);
//		i=id.find(2);
//		System.out.println("sss"+i.toString());
//		
		ArrayList<Incomes> array =id.findAllDateWise("2018-10-11", "2018-12-05", 2);
		for (Incomes i1: array) {
			System.out.println(i1.toString());
		}

	}

}
