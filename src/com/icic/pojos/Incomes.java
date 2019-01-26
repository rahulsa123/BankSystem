package com.icic.pojos;

import java.util.Date;

public class Incomes {
	private int inc_id;
	private String inc_ac;
	private int userid;
	private int inc_catid;
	private double amount;
	private java.util.Date tran_date;
	private String receiveby;
	private String remark;
	public int getInc_id() {
		return inc_id;
	}
	public void setInc_id(int inc_id) {
		this.inc_id = inc_id;
	}
	public String getInc_ac() {
		return inc_ac;
	}
	public void setInc_ac(String inc_ac) {
		this.inc_ac = inc_ac;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getInc_catid() {
		return inc_catid;
	}
	public void setInc_catid(int inc_catid) {
		this.inc_catid = inc_catid;
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
	public String getReceiveby() {
		return receiveby;
	}
	public void setReceiveby(String receiveby) {
		this.receiveby = receiveby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Incomes [inc_id=" + inc_id + ", inc_ac=" + inc_ac + ", userid=" + userid + ", inc_catid=" + inc_catid
				+ ", amount=" + amount + ", tran_date=" + tran_date + ", receiveby=" + receiveby + ", remark=" + remark
				+ "]";
	}
	public Incomes( String inc_ac, int userid, int inc_catid, double amount, Date tran_date, String receiveby,
			String remark) {
		super();
		this.inc_ac = inc_ac;
		this.userid = userid;
		this.inc_catid = inc_catid;
		this.amount = amount;
		this.tran_date = tran_date;
		this.receiveby = receiveby;
		this.remark = remark;
	}
	public Incomes() {
		super();
	}
	
	
}
