import org.junit.Assert;
import org.junit.Test;
import sample.Reservation;
import sample.ReservationProvider;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static junit.framework.TestCase.fail;

public class ReservationTests {

    @Test
    public void GetReservation() throws SQLException {

        int input = 1; //reservation Id
        LocalDate ld = LocalDate.of(2021,03,01);
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault())); //
        Date date = Date.from(instant);


        Reservation expectedResult = new Reservation(1, "Dominik Heger", "735071251", "1H2 2456", date, 0);
        Reservation actualResult = ReservationProvider.GetReservation(input);
        Assert.assertEquals(expectedResult.toString(),actualResult.toString());
    }

    @Test
    public void GetReservation_SQLERROR(){

        int input = 1; //reservation Id

        try{
            Reservation actualResult = ReservationProvider.GetReservation(input);
            fail();
        }
        catch (Exception exception){}
    }
}
