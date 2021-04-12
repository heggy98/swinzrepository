package CSAA.CSAA;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

@Configuration
public class DataSourceConfig {


    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/csaa_database","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/csaa_database");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

//    public static ObservableList<Reservation> getReservations(){
//        Connection conn = ConnectDb();
//        ObservableList<Reservation> list = FXCollections.observableArrayList();
//        try {
//            assert conn != null;
//            PreparedStatement statement = conn.prepareStatement("select * from reserve_test");
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()){
//                Reservation reservation = new Reservation(
//                        Integer.parseInt(rs.getString("id")),
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getString("spz"),
//                        rs.getDate("date"),
//                        Integer.parseInt(rs.getString("timeIndex")));
//                list.add(reservation);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public static Reservation getReservation(int id) throws SQLException {
//        Reservation reservation = null;
//
//        try {
//            Connection conn = ConnectDb();
//            String sql = String.format("select * from reserve_test where id = %s", id);
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//
//            if(rs.next()){
//                reservation = new Reservation(
//                        Integer.parseInt(rs.getString("id")),
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getString("spz"),
//                        rs.getDate("date"),
//                        Integer.parseInt(rs.getString("timeIndex")));
//            }
//            else{
//                throw new SQLException("No reservation was found!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        return reservation;
//    }
//
//    public static void updateReservation(Reservation reservation){
//        Connection conn = ConnectDb();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        String sql = String.format("UPDATE reserve_test " +
//                                    "SET name = '%s', phone = '%s', spz = '%s', timeIndex = '%s', date = '%s'" +
//                                    "WHERE id = %s",
//                                        reservation.getName(),
//                                        reservation.getPhone(),
//                                        reservation.getSpz(),
//                                        reservation.getTimeIndex(),
//                                        sdf.format(reservation.getDate()),
//                                        reservation.getId());
//
//        System.out.println(sql);
//
//        try{
//            assert conn != null;
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(sql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public static void AddNewReservation(Reservation reservation){
//        Connection conn = ConnectDb();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        String sql = String.format("INSERT INTO reserve_test (name, phone, spz, timeIndex, date) " +
//                                    "VALUES ('%s', '%s', '%s', '%s', '%s')",
//                                        reservation.getName(),
//                                        reservation.getPhone(),
//                                        reservation.getSpz(),
//                                        reservation.getTimeIndex(),
//                                        sdf.format(reservation.getDate()));
//
//        System.out.println(sql);
//
//        try{
//            assert conn != null;
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(sql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
}
