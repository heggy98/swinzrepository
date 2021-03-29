import org.junit.Assert;
import org.junit.Ignore;
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
    public void GetTimeIndex_NotEquals(){
        String input = "09:00";
        int expectedResult = 2;
        int actualResult = TimeIndex.GetIndex(input);

        Assert.assertNotEquals(expectedResult, actualResult);
    }

    @Test
    public void GetTimeString(){
        int input = 1;
        String expectedResult = "09:00";
        String actualResult = TimeIndex.hours.get(input);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void GetTimeString_NotEquals(){
        int input = 1;
        String expectedResult = "10:00";
        String actualResult = TimeIndex.hours.get(input);

        Assert.assertNotEquals(expectedResult, actualResult);
    }

    @Ignore
    @Test
    public void GetTimeString_Ignored(){
        int input = 1;
        String expectedResult = "09:00";
        String actualResult = TimeIndex.hours.get(input);

        Assert.assertEquals(expectedResult, actualResult);
    }
}
