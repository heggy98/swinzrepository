package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;


public class Database_Access {

    Connection conn = null;

    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/csaa_database","root","");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<reservation> getReservations(){
        Connection conn = ConnectDb();
        ObservableList<reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement statement = conn.prepareStatement("select * from reserve_test");
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                list.add(new reservation(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("phone"), rs.getString("spz"), rs.getString("time"),rs.getDate("date")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
