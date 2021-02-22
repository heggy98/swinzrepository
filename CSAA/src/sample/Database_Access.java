package sample;

import java.sql.*;

public class Database_Access {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/csaa_database";
    private static String user = "root", pass = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection(url,user,pass);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from reserve_test");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(id+" "+name);
            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
