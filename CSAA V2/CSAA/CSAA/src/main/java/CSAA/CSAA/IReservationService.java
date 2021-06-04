package CSAA.CSAA;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface IReservationService {
    Collection<Reservation> findAll();

    Reservation findById(Long id);

    Reservation save(ReservationDTO reservationDTO);

    Reservation update(ReservationDTO reservationDTO);

    void deleteById(Long id);
}
