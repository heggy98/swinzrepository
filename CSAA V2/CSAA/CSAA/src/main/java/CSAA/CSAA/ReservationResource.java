package CSAA.CSAA;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Collection<Reservation>> findAll(){
        try{
            var res = reservationService.findAll();
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        try{
            var res = reservationService.findById(id);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody ReservationDTO reservationDTO){
        try{
            var res = reservationService.save(reservationDTO);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody ReservationDTO reservationDTO){
        try{
            var res = reservationService.update(reservationDTO);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }catch (NotFoundException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        try{
            reservationService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }
}
