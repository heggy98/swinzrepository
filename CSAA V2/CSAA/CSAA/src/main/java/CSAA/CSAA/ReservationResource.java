package CSAA.CSAA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationResource {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public Collection<Reservation> findAll(){
        return reservationService.findAll();
    }

    @GetMapping("{id}")
    public Reservation findById(@PathVariable Long id){
        return reservationService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation save(@RequestBody ReservationDTO reservationDTO){
        return reservationService.save(reservationDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }

    @DeleteMapping("{id}")
    public Reservation deleteById(@PathVariable Long id){
        return reservationService.deleteById(id);
    }
}
