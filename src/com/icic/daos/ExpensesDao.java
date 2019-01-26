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
import com.icic.pojos.Expenses;
import com.icic.utilities.ConnectionPool;

public class ExpensesDao {

	public void create(Expenses i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		java.sql.Date date = null;
		try {
			String sql="insert into Expenses(Exp_ac,userid,Exp_catid,amount,tran_date,Payby,remark) values(?,?,?,?,?,?,?)";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, i.getExp_ac());
			pd.setInt(2, i.getUserid());
			pd.setInt(3, i.getExp_catid());
			pd.setDouble(4, i.getAmount());
			date = new java.sql.Date(i.getTran_date().getTime());
			pd.setDate(5, date);
			pd.setString(6, i.getPayby());
			pd.setString(7, i.getRemark());
			pd.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Unable to insert data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
		if(i.getPayby().equalsIgnoreCase("cash")) {
		CashBookDao cbd= new CashBookDao();
		CashBook cb = new CashBook(i.getExp_ac(), date,i.getAmount(), i.getUserid(), "Pay");
		cbd.create(cb);
		}else {
			BankBookDao bbd = new BankBookDao();
			BankBook bb = new BankBook(i.getExp_ac(), date, i.getAmount(), i.getUserid(), "Pay");
			bbd.create(bb);
		}
		
	}
	
	public void edit(Expenses i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql="update Expenses set Exp_ac=?,userid=?,Exp_catid=?,amount=?,tran_date=?,Payby=?,remark=? where Exp_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setString(1, i.getExp_ac());
			pd.setInt(2, i.getUserid());
			pd.setInt(3, i.getExp_catid());
			pd.setDouble(4, i.getAmount());
			java.sql.Date date = new java.sql.Date(i.getTran_date().getTime());
			pd.setDate(5, date);
			pd.setString(6, i.getPayby());
			pd.setString(7, i.getRemark());
			pd.setInt(8, i.getExp_id());
			pd.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Unable to edit data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
	}
	
	public void remove(Expenses i) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		try {
			String sql="delete from Expenses where Exp_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1, i.getExp_id());
			pd.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Unable to remove data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
	}
	public Expenses find(int Exp_id) {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		Expenses i = new Expenses();
		try {
			String sql="select * from Expenses where Exp_id=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1, Exp_id);
			ResultSet rs=pd.executeQuery();
			if(rs.next()) {
				i.setAmount(rs.getDouble("amount"));
				i.setExp_ac(rs.getString("Exp_ac"));
				i.setExp_catid(rs.getInt("Exp_catid"));
				i.setExp_id(rs.getInt("Exp_id"));
				i.setPayby(rs.getString("Payby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
			}else {
				System.out.println("Data not present in Expenses table");
				return null;
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
		return i;
	}
	public ArrayList<Expenses> findAll(){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Expenses> array = new ArrayList<Expenses>();
		try {
			String sql="select * from Expenses";
			PreparedStatement pd = c.prepareStatement(sql);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Expenses i = new Expenses();
				i.setAmount(rs.getDouble("amount"));
				i.setExp_ac(rs.getString("Exp_ac"));
				i.setExp_catid(rs.getInt("Exp_catid"));
				i.setExp_id(rs.getInt("Exp_id"));
				i.setPayby(rs.getString("Payby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	public ArrayList<Expenses> findAll(int userid){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Expenses> array = new ArrayList<Expenses>();
		try {
			String sql="select * from Expenses where userid=?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setInt(1 ,userid);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Expenses i = new Expenses();
				i.setAmount(rs.getDouble("amount"));
				i.setExp_ac(rs.getString("Exp_ac"));
				i.setExp_catid(rs.getInt("Exp_catid"));
				i.setExp_id(rs.getInt("Exp_id"));
				i.setPayby(rs.getString("Payby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	
	public ArrayList<Expenses> findAllDateWise(String sdate, String edate, int userid){
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.initialize();
		Connection c = cp.getConnection();
		ArrayList<Expenses> array = new ArrayList<Expenses>();
		try {
			java.util.Date startd= new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
			java.util.Date endd= new SimpleDateFormat("yyyy-MM-dd").parse(edate);
			System.out.println(startd+"  "+endd);
			if(startd.after(endd)) {
				throw new  ParseException("Invalid range of date",1);
			}
			java.sql.Date sd=new java.sql.Date(startd.getTime());
			java.sql.Date ed =new java.sql.Date(endd.getTime());
			
			String sql="select * from Expenses where tran_date>=? and tran_date<=? and userid =?";
			PreparedStatement pd = c.prepareStatement(sql);
			pd.setDate(1, sd);
			pd.setDate(2, ed);
			pd.setInt(3, userid);
			ResultSet rs=pd.executeQuery();
			while(rs.next()) {
				Expenses i = new Expenses();
				i.setAmount(rs.getDouble("amount"));
				i.setExp_ac(rs.getString("Exp_ac"));
				i.setExp_catid(rs.getInt("Exp_catid"));
				i.setExp_id(rs.getInt("Exp_id"));
				i.setPayby(rs.getString("Payby"));
				i.setRemark(rs.getString("remark"));
				i.setUserid(rs.getInt("userid"));
				java.util.Date date = new java.util.Date(rs.getDate("tran_date").getTime());
				i.setTran_date(date);
				array.add(i);
			}
		}catch(SQLException e) {
			System.out.println("Unable to find data in Expenses table "+e);
		}catch (ParseException e) {
			System.out.println("Unable to convert date in Expenses table "+e);
		}finally {
			cp.putConnection(c);
		}
		return array;
	}
	public static void main(String[] args) {
try {
	System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-30"));	
}catch (Exception e) {
	// TODO: handle exception
}
	//		Expenses i =new Expenses("fixed", 2,3, 10090.9, DateUtils.convertDate(""), "UPI", "FEE");
//		ExpensesDao id = new ExpensesDao();
//		id.create(i);
//		i.setExp_id(3);
//		i=id.find(2);
//		System.out.println("sss"+i.toString());
//		
//		ArrayList<Expenses> array =id.findAll();
//		for (Expenses i1: array) {
//			System.out.println(i1.toString());
//		}
	}
}
