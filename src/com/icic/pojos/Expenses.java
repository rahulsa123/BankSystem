package com.icic.pojos;

import java.util.Date;

public class Expenses {
	private int exp_id;
	private String exp_ac;
	private int userid;
	private int exp_catid;
	private double amount;
	private java.util.Date tran_date;
	private String payby;
	private String remark;
	public int getExp_id() {
		return exp_id;           
	}
	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}
	public String getExp_ac() {
		return exp_ac;
	}
	public void setExp_ac(String exp_ac) {
		this.exp_ac = exp_ac;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getExp_catid() {
		return exp_catid;
	}
	public void setExp_catid(int exp_catid) {
		this.exp_catid = exp_catid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public java.util.Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(java.util.Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Expenses [exp_id=" + exp_id + ", exp_ac=" + exp_ac + ", userid=" + userid + ", exp_catid=" + exp_catid
				+ ", amount=" + amount + ", tran_date=" + tran_date + ", payby=" + payby + ", remark=" + remark + "]";
	}
	public Expenses( String exp_ac, int userid, int exp_catid, double amount, Date tran_date, String payby,
			String remark) {
		super();
		this.exp_ac = exp_ac;
		this.userid = userid;
		this.exp_catid = exp_catid;
		this.amount = amount;
		this.tran_date = tran_date;
		this.payby = payby;
		this.remark = remark;
	}
	public Expenses() {
		super();
	}
	
}
