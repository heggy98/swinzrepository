package CSAA.CSAA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // <1>
public class DatabaseLoader implements CommandLineRunner { // <2>

    private final ReservationRepository repository;

    @Autowired // <3>
    public DatabaseLoader(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception { // <4>
        this.repository.save(new Reservation("NAME", "735145789", "4T4-9917", new Date(), 2));
    }
}
