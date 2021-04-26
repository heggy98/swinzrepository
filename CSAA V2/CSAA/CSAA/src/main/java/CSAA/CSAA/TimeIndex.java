package CSAA.CSAA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeIndex {

    public static Map<Integer, String> hours;

    static {
        hours = new HashMap<>();
        hours.put(0, "08:00");
        hours.put(1, "09:00");
        hours.put(2, "10:00");
        hours.put(3, "11:00");
        hours.put(4, "12:00");
        hours.put(5, "13:00");
        hours.put(6, "14:00");
        hours.put(7, "15:00");
        hours.put(8, "16:00");
        hours.put(9, "17:00");
    }

    public static int GetIndex(String value) {
        for (Map.Entry<Integer, String> entry : hours.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("This hour has no index!");
    }

    @Override
    public String toString() {
        return "tajm index is here ! ";
    }

    public static Object GetValues(){
        return hours.values().toArray();
    }
}
