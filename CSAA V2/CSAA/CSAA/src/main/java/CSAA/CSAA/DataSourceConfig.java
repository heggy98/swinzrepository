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
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class DataSourceConfig {


    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/csaa_database", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/csaa_database");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    public static Collection<Reservation> getReservations() {
        Connection conn = ConnectDb();
        Collection<Reservation> reservationMap = new ArrayList<>();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("select * from reserve_test");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("spz"),
                        rs.getDate("date"),
                        Integer.parseInt(rs.getString("timeIndex")));
                reservationMap.add(reservation);
            }

            return reservationMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Reservation getReservation(Long id) throws SQLException {
        Reservation reservation;

        try {
            Connection conn = ConnectDb();
            String sql = String.format("select * from reserve_test where id = %s", id);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                reservation = new Reservation(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("spz"),
                        rs.getDate("date"),
                        Integer.parseInt(rs.getString("timeIndex")));
            } else {
                throw new SQLException("No reservation was found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return reservation;
    }

    public static void deleteReservation(Reservation reservation){
        Connection conn = ConnectDb();


        String sql = String.format("DELETE from reserve_test " +
                        "WHERE id = %s",
                reservation.getId());

        System.out.println(sql);

        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Reservation updateReservation(Reservation reservation) {
        Connection conn = ConnectDb();

        var formattedDate = giveMeDateToSaveInDb(reservation);

        String sql = String.format("UPDATE reserve_test " +
                        "SET name = '%s', phone = '%s', spz = '%s', timeIndex = '%s', date = '%s'" +
                        "WHERE id = %s",
                reservation.getName(),
                reservation.getPhone(),
                reservation.getSpz(),
                reservation.getTimeIndex(),
                formattedDate,
                reservation.getId());

        System.out.println(sql);

        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reservation;
    }

    private static String giveMeDateToSaveInDb(Reservation reservation){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(reservation.getDate());
    }

    public static Reservation addNewReservation(Reservation reservation) {
        Connection conn = ConnectDb();
        var formattedDate = giveMeDateToSaveInDb(reservation);

        String sql = String.format("INSERT INTO reserve_test (name, phone, spz, timeIndex, date) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')",
                reservation.getName(),
                reservation.getPhone(),
                reservation.getSpz(),
                reservation.getTimeIndex(),
                formattedDate);

        System.out.println(sql);

        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reservation;
    }

    public static Collection<Reservation> GetByDate(java.util.Date date) {

        Collection<Reservation> reservationMap = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        var formattedDate = sdf.format(date);

        try {
            Connection conn = ConnectDb();
            String sql = String.format("select * from reserve_test where date = '%s'", formattedDate);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                Reservation reservation = new Reservation(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("spz"),
                        rs.getDate("date"),
                        Integer.parseInt(rs.getString("timeIndex")));
                reservationMap.add(reservation);
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }

        return reservationMap;

    }
}
