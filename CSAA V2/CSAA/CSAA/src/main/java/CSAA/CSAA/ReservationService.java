package CSAA.CSAA;


import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {

    @Override
    public Collection<Reservation> findAll() {
        var reservations = DataSourceConfig.getReservations();
        assert reservations != null;
        for (Reservation res :
                reservations) {
            if (res.time.equals("")) {
                res.time = TimeIndex.getByIndex(res.timeIndex);
            }
        }
        return reservations;
    }

    @Override
    public Reservation findById(Long id) {
        try {
            var reservation = DataSourceConfig.getReservation(id);
            return reservation;
        } catch (SQLException exception) {
            throw new RuntimeException("Reservation was not found!");
        }
    }

    @Override
    public Reservation save(ReservationDTO reservationDTO) {
        CheckTimeAvaiability(reservationDTO);
        Reservation reservation = ParseDtoToEntity(reservationDTO);
        return DataSourceConfig.addNewReservation(reservation);
    }

    private void CheckTimeAvaiability(ReservationDTO reservationDTO) {
        var allReservations = findAll();
        var reservationsWithSameDateTime = allReservations.stream().filter(x -> (x.date.toString().equals(reservationDTO.date)) && (x.timeIndex == reservationDTO.time)).collect(Collectors.toList());
        var moreThanOneReservations = reservationsWithSameDateTime.size() >= 2;
        boolean sameRes = false;
        for (var Res :
                reservationsWithSameDateTime) {
            if (Res.getId() == reservationDTO.getId()) {
                sameRes = true;
                break;
            }
        }

        if (moreThanOneReservations && !sameRes) {
            throw new RuntimeException("ReservationService/save - This datetime is full.");
        }
    }

    @Override
    public Reservation update(ReservationDTO reservationDTO) throws NotFoundException {
        Reservation reservationInDb = null;

        try {
            reservationInDb = DataSourceConfig.getReservation(reservationDTO.getId());

            LocalDateTime now = LocalDateTime.now();


            if(reservationInDb.getDate().before(new Date())){
                throw new SecurityException("Gone reservation cannot be edited.");
            }

            CheckTimeAvaiability(reservationDTO);
            reservationInDb = ParseDtoToEntity(reservationDTO);
            return DataSourceConfig.updateReservation(reservationInDb);
        } catch (Exception exception) {
            if (exception.getClass().equals(SQLException.class)){
                throw new NotFoundException("Reservation was not found!");
            }
            else{
                throw new RuntimeException("Reservation was not modified!");
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            var reservation = DataSourceConfig.getReservation(id);
            if (reservation != null) {
                DataSourceConfig.deleteReservation(reservation);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static Reservation ParseDtoToEntity(ReservationDTO reservationDTO) {

        Date parsedDate = CreateDateFromString(reservationDTO.date);

        int parsedTime = GetRightTimeIndex(reservationDTO);

        return new Reservation(
                reservationDTO.getId() == null ? 0 : reservationDTO.getId(),
                reservationDTO.name,
                reservationDTO.phone,
                reservationDTO.spz,
                parsedDate,
                parsedTime);
    }

    private static int GetRightTimeIndex(ReservationDTO reservationDTO) {
        if (reservationDTO.time == 0 && reservationDTO.timeIndex != 0) {
            return reservationDTO.timeIndex;
        } else {
            return reservationDTO.time;
        }
    }

    private static Date CreateDateFromString(String dateToParse) {
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


