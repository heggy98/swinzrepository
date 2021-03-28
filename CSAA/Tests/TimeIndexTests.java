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

    @Test
    public void GetTimeString(){
        int input = 1;
        String expectedResult = "09:00";
        String actualResult = TimeIndex.hours.get(input);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void GetTimeString_Wrong(){
        int input = 1;
        String expectedResult = "10:00";
        String actualResult = TimeIndex.hours.get(input);

        Assert.assertEquals(expectedResult, actualResult);
    }

}
