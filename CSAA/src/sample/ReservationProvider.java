package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ReservationProvider {
    public static ObservableList<ReservationDTO> GetAllReservations(){
        ObservableList<Reservation> reservations = Database_Access.getReservations();
        return ConvertReservationsToDto(reservations);
    }

    public static Reservation GetReservation(int id) throws SQLException {

        Reservation res = null;

        try{
            res = Database_Access.getReservation(id);
        }catch (SQLException e){
            throw e;
        }
        return res;
    }

    private static ObservableList<ReservationDTO> ConvertReservationsToDto(ObservableList<Reservation> reservations){
        ArrayList<ReservationDTO> reservationsDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDTOs.add(ConvertReservationToDto(reservation));
        }

        return FXCollections.observableArrayList( reservationsDTOs);
    }

    private static ReservationDTO ConvertReservationToDto(Reservation reservation){

        return new ReservationDTO(
                reservation.getId(),
                reservation.getName(),
                reservation.getPhone(),
                reservation.getSpz(),
                String.valueOf(reservation.getDate()),
                TimeIndex.hours.get(reservation.getTimeIndex()));
    }

    public static void CreateReservation(String name, String phone, String spz, DatePicker datePicker, String time){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())); //
        Date date = Date.from(instant);

        var allReservations =  GetAllReservations();

        boolean cantEdit = allReservations.stream().filter(x -> x.getDate().equals(localDate.toString()) && x.getTime().equals(time)).count() > 1;

        if(cantEdit){
            throw new RuntimeException("There is too many reservations.");
        }

        Reservation reservation = new Reservation(
                name,
                phone,
                spz,
                date,
                TimeIndex.GetIndex(time));


        System.out.println(reservation);

        Database_Access.AddNewReservation(reservation);
    }

    public static void EditReservation (int id, String name, String phone, String spz, DatePicker datePicker, String time){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())); //
        Date date = Date.from(instant);

        var allReservations =  GetAllReservations();
        boolean cantEdit = allReservations.stream().filter(x -> x.getDate().equals(localDate.toString()) && x.getTime().equals(time)).count() > 1;

        if(cantEdit){
            throw new RuntimeException();
        }

        Reservation reservation = new Reservation(
                id,
                name,
                phone,
                spz,
                date,
                TimeIndex.GetIndex(time));

        Database_Access.updateReservation(reservation);
    }
}
