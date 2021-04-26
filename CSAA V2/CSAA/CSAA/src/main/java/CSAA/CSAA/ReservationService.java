package CSAA.CSAA;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService implements IReservationService {

    private Long reservationId = 100L;
    private Map<Long, Reservation> reservationMap = new HashMap<Long, Reservation>();

    {
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        reservation.setName("Rezervace");
        reservation.setPhone("735145857");
        reservation.setSpz("4T4-9917");
        try {
            reservation.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("1928-09-04"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setTimeIndex(8);
        reservationMap.put(reservation.getId(),reservation);
    }

    @Override
    public Collection<Reservation> findAll() {
        return DataSourceConfig.getReservations();
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
    public Reservation deleteById(Long id) {
        if(reservationMap.get(id) != null) {
            return reservationMap.remove(id);
        }
        return null;
    }

    private static ArrayList<ReservationDTO> ConvertReservationsToDto(ArrayList<Reservation> reservations){
        ArrayList<ReservationDTO> reservationsDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsDTOs.add(ConvertReservationToDto(reservation));
        }

        return reservationsDTOs;
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

    private static Reservation ParseDtoToEntity(ReservationDTO reservationDTO){

        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(reservationDTO.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Reservation(
                reservationDTO.getId(),
                reservationDTO.name,
                reservationDTO.phone,
                reservationDTO.spz,
                parsedDate,
                0);
    }
}
