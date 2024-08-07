package com.test.sku.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO 
{
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Connection getConn() 
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         return conn;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public boolean login(User user) {
      String sql = "SELECT * FROM users WHERE userid=? AND userpwd=?";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, user.getUid());
         pstmt.setString(2,  user.getPwd());
         rs = pstmt.executeQuery();
         if(rs.next()) {
            return true;
         }
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }finally {
         closeAll();
      }
      return false;
   }
   
   public List<User> getList()
   {
      String sql = "SELECT * FROM users";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         List<User> list = new ArrayList<>();
         while(rs.next()) {
            String userid = rs.getString("USERID");
            String userpwd = rs.getString("USERPWD");
            list.add(new User(userid, userpwd));
         }
         return list;
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }finally {
         closeAll();
      }
      return null;
   }
   

   public User getDetail(String uid) {
      String sql = "SELECT * FROM users WHERE userid=?";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, uid);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            String userid = rs.getString("USERID");
            String userpwd = rs.getString("USERPWD");
            return new User(userid, userpwd);
         }
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }finally {
         closeAll();
      }
      return null;
   }
   
   public boolean updatePwd(User user) {
      String sql = "UPDATE users SET userpwd=? WHERE userid=?";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, user.getPwd());
         pstmt.setString(2, user.getUid());
         int rows = pstmt.executeUpdate();
         
         return rows>0;
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }finally {
         closeAll();
      }
      return false;
   }
   
   private void closeAll() {
      try {
         if(rs!=null) rs.close();
         if(pstmt!=null) pstmt.close();
         if(conn!=null) conn.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
}