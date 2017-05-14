package com.janew.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginValidater {
   public static boolean checkUser(String user, String pass) {
	   boolean st = false;
	   try {
		   //loading drivers for Postgres
		   Class.forName("org.postgresql.Driver");
	
		   //creating connection with the database 
	       Connection con = 
	    		   DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","abcdefg");
	       PreparedStatement ps = con.prepareStatement("select * from public.user where name=? and password=?");
	       ps.setString(1, user);
	       ps.setString(2, pass);
	       ResultSet rs = ps.executeQuery();
	       st = rs.next();
	   } catch (Exception e) {
         e.printStackTrace();
       }
       return st;              
    }   
}
