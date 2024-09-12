package com.example;

import java.sql.*;

public class LoginDao {
    String sql = "Select * from login where uname=? and pass=?";
    String url= "jdbc:mysql://localhost:3306/testdb";
    String username = "root";
    String password = "root";
    public boolean check(String uname, String pass)  {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,username,password);
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,uname);
        st.setString(2,pass);
        ResultSet rs= st.executeQuery();
        if(rs.next()){
            return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
