import javafx.scene.control.DatePicker;
import org.junit.Assert;
import org.junit.Test;
import sample.Reservation;
import sample.ReservationProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReservationTests {

    @Test
    public void GetReservation() throws ParseException {

        int input = 1; //reservation Id
        LocalDate ld = LocalDate.of(2021,03,01);
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault())); //
        Date date = Date.from(instant);


        Reservation expectedResult = new Reservation(1, "Dominik Heger", "735071251", "1H2 2456", date, 0);
        Reservation actualResult = ReservationProvider.GetReservation(input);
        Assert.assertEquals(expectedResult.toString(),actualResult.toString());
    }
}
