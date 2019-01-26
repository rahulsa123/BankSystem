package com.icic.utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class ConnectionPool {
static ArrayList<Connection> connections =null;
static ConnectionPool instance =null;

public static synchronized ConnectionPool getInstance() {
	if(instance == null) {
		instance = new ConnectionPool();
	}
	return instance;
}
 public synchronized void initialize() {
	 if(connections == null) {
		 try {
			 Properties p = new Properties();
			 InputStream ins = getClass().getResourceAsStream("Connection.properties");
			 p.load(ins);
			 String driver = p.getProperty("driver");
			 String url = p.getProperty("url");
			 String username = p.getProperty("username");
			 String password = p.getProperty("password");
			 int maxconnections = Integer.parseInt(p.getProperty("maxconnections"));
			 Class.forName(driver);
			 connections = new ArrayList<Connection>();
			 while(maxconnections>0) {
				 maxconnections--;
				 Connection c = DriverManager.getConnection(url, username, password);
				 connections.add(c);
			 }
		 }catch(Exception e) {
			 System.out.println("Unable to connect with database");
		 }
	 }
 }
public synchronized void removeAllConnection() {
	if(connections==null) {
			return ;
	}
	try {
		for (int i=0;i<connections.size();i++) {
			Connection c=connections.get(i);
			c.close();
		}
		connections.clear();
		connections=null;
	}catch(SQLException e){
		System.out.println("Unable to remove connection from connectionpool "+e);
	}
}
 public synchronized Connection getConnection() {
	 Connection c =null;
	 if(connections==null) {
		 return null;
		}
		 else{
			 while(true) {
					 if(connections.size()>0) {
							 c =(Connection) connections.get(0);
							 connections.remove(0);
							 break;
					}
						 else {
							 try {
								 wait();
							 }catch (Exception e) {
								System.out.println(e);
							}
						 }
					 }
			 }
	 return c;
	 }
 public synchronized void putConnection(Connection c) {
	 connections.add(c);
	 notifyAll();
 }

}
 










