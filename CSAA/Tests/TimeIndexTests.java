import org.junit.Assert;
import org.junit.Test;
import sample.TimeIndex;

public class TimeIndexTests {

    @Test
    public void GetTimeIndex(){
        String input = "09:00";
        int expectedResult = 1;
        int actualResult = TimeIndex.GetIndex(input);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void GetTimeIndex_Wrong(){
        String input = "09:00";
        int expectedResult = 2;
        int actualResult = TimeIndex.GetIndex(input);

        Assert.assertEquals(expectedResult, actualResult);
    }



}
