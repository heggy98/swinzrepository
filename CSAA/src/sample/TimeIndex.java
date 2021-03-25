package sample;

import java.util.HashMap;
import java.util.Map;

public class TimeIndex{

    public static Map<Integer, String> hours;

    static {
        hours = new HashMap<>();
        hours.put(0,"08:00");
        hours.put(1,"09:00");
        hours.put(2,"10:00");
        hours.put(3,"11:00");
        hours.put(4,"12:00");
        hours.put(5,"13:00");
        hours.put(6,"14:00");
        hours.put(7,"15:00");
        hours.put(8,"16:00");
        hours.put(9,"17:00");
    }

    public static int GetIndex(String value){
        for (Map.Entry<Integer, String> entry : hours.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("This hour has no index!");
    }

//    public static String GetHourByIndex(int index){
//        switch (index){
//            case 0:
//                return "08:00";
//            case 1:
//                return "09:00";
//            case 2:
//                return "10:00";
//            case 3:
//                return "11:00";
//            case 4:
//                return "12:00";
//            case 5:
//                return "13:00";
//            case 6:
//                return "14:00";
//            case 7:
//                return "15:00";
//            case 8:
//                return "16:00";
//            case 9:
//                return "17:00";
//        }
//        throw new RuntimeException(Constants.TimeIndexError);
//    }
}
