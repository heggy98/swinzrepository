package CSAA.CSAA;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        return reservationMap.values();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationMap.get(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        Long newReservationId = ++reservationId;
        reservation.setId(newReservationId);
        reservationMap.put(newReservationId, reservation);
        return reservationMap.get(newReservationId);
    }

    @Override
    public Reservation update(Reservation reservation) {
        reservationId = reservation.getId();
        if(reservationMap.get(reservationId) != null){
            reservationMap.put(reservationId, reservation);
            return reservationMap.get(reservationId);
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
}
