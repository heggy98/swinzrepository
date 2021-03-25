package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
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

        Reservation reservation = new Reservation(
                name,
                phone,
                spz,
                date,
                TimeIndex.GetIndex(time));


        System.out.println(reservation);

        Database_Access.AddNewReservation(reservation);
    }
}
