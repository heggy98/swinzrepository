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
        assert reservations != null;
        for (Reservation res :
                reservations) {
            if(res.time.equals("")){
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
        CheckTimeAvaiability(reservationDTO);
        Reservation reservation = ParseDtoToEntity(reservationDTO);
        return DataSourceConfig.addNewReservation(reservation);
    }

    private void CheckTimeAvaiability(ReservationDTO reservationDTO) {
        var allReservations = findAll();
        var cantBook = allReservations.stream().filter(x -> (x.date.equals(reservationDTO.date)) && (x.timeIndex == reservationDTO.timeIndex)).count() > 1;

        if(cantBook){
            throw new RuntimeException("ReservationService/save - This datetime is full.");
        }
    }

    @Override
    public Reservation update(ReservationDTO reservationDTO) {
        Reservation reservationInDb = null;

        try {
            CheckTimeAvaiability(reservationDTO);
            reservationInDb = DataSourceConfig.getReservation(reservationDTO.getId());
            if(reservationInDb != null){

                reservationInDb = ParseDtoToEntity(reservationDTO);
                return DataSourceConfig.updateReservation(reservationInDb);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return reservationInDb;
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

    private static Reservation ParseDtoToEntity(ReservationDTO reservationDTO){

        Date parsedDate = craeteDateFromString(reservationDTO.date);

        return new Reservation(
                reservationDTO.getId(),
                reservationDTO.name,
                reservationDTO.phone,
                reservationDTO.spz,
                parsedDate,
                reservationDTO.time);
    }

    private static Date craeteDateFromString(String dateToParse) {
        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateToParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
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

}


