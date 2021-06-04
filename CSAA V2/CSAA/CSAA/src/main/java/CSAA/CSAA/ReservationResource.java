package CSAA.CSAA;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping()
    public Reservation save(@RequestBody ReservationDTO reservationDTO){
        return reservationService.save(reservationDTO);
    }

    @PutMapping()
    public Reservation update(@RequestBody ReservationDTO reservationDTO){
        return reservationService.update(reservationDTO);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        reservationService.deleteById(id);
    }
}
