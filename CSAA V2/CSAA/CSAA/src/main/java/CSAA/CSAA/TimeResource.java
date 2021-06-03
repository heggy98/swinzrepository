package CSAA.CSAA;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/times")
@CrossOrigin(origins = "http://localhost:3000")
public class TimeResource {

    @GetMapping
    public Object findAll() {
        return TimeIndex.GetValues();
    }

    @ResponseBody
    @RequestMapping("/byIndex")
    @GetMapping
    public Object getByIndex(@RequestParam int index) {
        return TimeIndex.getByIndex(index);
    }

//    Function for future purposes!
//    @ResponseBody
//    @RequestMapping("/byDate")
//    @GetMapping
//    public Object getByDate(@RequestParam String date) {
//
//        Date sdf = null;
//
//        try {
//            sdf = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        var reservations = DataSourceConfig.GetByDate(sdf);
//        var mapTimeIndex = TimeIndex.hours;
//
//        Map<Integer, String> hours = new HashMap<>();
//
//        for (var timeIndex : mapTimeIndex.keySet()) {
//            if (reservations.stream().filter(x -> x.getTimeIndex() == timeIndex).count() < 2) {
//                hours.put(timeIndex, TimeIndex.hours.get(timeIndex));
//            }
//        }
//
//        return hours.values().toArray();
//    }
}
