package CSAA.CSAA;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface IReservationService {
    Collection<Reservation> findAll();

    Reservation findById(Long id);

    Reservation save(ReservationDTO reservationDTO);

    Reservation update(ReservationDTO reservationDTO) throws NotFoundException;

    void deleteById(Long id);
}
