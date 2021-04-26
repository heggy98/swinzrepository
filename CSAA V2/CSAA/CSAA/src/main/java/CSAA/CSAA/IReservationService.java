package CSAA.CSAA;

import java.util.Collection;

public interface IReservationService {
    Collection<Reservation> findAll();

    Reservation findById(Long id);

    Reservation save(ReservationDTO reservationDTO);

    Reservation update(Reservation reservation);

    Reservation deleteById(Long id);
}
