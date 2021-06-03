package CSAA.CSAA;


import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService implements IReservationService {

    @Override
    public Collection<Reservation> findAll() {
        var reservations = DataSourceConfig.getReservations();
        for (Reservation res :
                reservations) {
            if(res.time == "" || res.time == null){
                res.time = TimeIndex.getByIndex(res.timeIndex);
            }
        }
        return reservations;
    }

    @Override
    public Reservation findById(Long id) {
        try {
            return DataSourceConfig.getReservation(id);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation save(ReservationDTO reservationDTO) {

        Reservation reservation = ParseDtoToEntity(reservationDTO);

        var allReservations = findAll();

        for (Reservation res :
                allReservations) {
            System.out.println(res.date);
        }

        var cantBook = allReservations.stream().filter(x -> (x.date.equals(reservation.getDate())) && (x.timeIndex == reservation.timeIndex)).count() > 1;

        if(cantBook){
            throw new RuntimeException("FULL");
        }

        return DataSourceConfig.AddNewReservation(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        try {
            if(DataSourceConfig.getReservation(reservation.getId()) != null){
                return DataSourceConfig.updateReservation(reservation);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try {
            var reservation = DataSourceConfig.getReservation(id);
            if(reservation != null){
                DataSourceConfig.deleteReservation(reservation);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

//    private static ArrayList<ReservationDTO> ConvertReservationsToDto(ArrayList<Reservation> reservations){
//        ArrayList<ReservationDTO> reservationsDTOs = new ArrayList<>();
//
//        for (Reservation reservation : reservations) {
//            reservationsDTOs.add(ConvertReservationToDto(reservation));
//        }
//
//        return reservationsDTOs;
//    }
//
//    private static ReservationDTO ConvertReservationToDto(Reservation reservation){
//
//        return new ReservationDTO(
//                reservation.getId(),
//                reservation.getName(),
//                reservation.getPhone(),
//                reservation.getSpz(),
//                String.valueOf(reservation.getDate()),
//                TimeIndex.getByIndex(reservation.timeIndex));
//    }

    private static Reservation ParseDtoToEntity(ReservationDTO reservationDTO){

        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(reservationDTO.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(reservationDTO.timeIndex != 0){
            reservationDTO.time = reservationDTO.timeIndex;
        }

        return new Reservation(
                0,
                reservationDTO.name,
                reservationDTO.phone,
                reservationDTO.spz,
                parsedDate,
                reservationDTO.time);
    }

//    private ArrayList<Integer> getFreeTimes(Date date){
//
//        ArrayList<Integer> allTimes = new ArrayList<>();
//
//        var reservations = DataSourceConfig.GetByDate(date);
//        if(reservations.size() == 2){
//            return null;
//        }
//        if(reservations.size() == 0){
//
//        }
//    }
}
