package com.tuanhc;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            new Main();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Main() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?user=root&password=123456789");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from khach_hang");
        while(rs.next()) {
            System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", "
                + rs.getString(3) + ", " + rs.getString(4));
        }
        connection.close();
    }
}
