package CSAA.CSAA;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/times")
@CrossOrigin(origins = "http://localhost:3000")
public class TimeResource {

    @GetMapping
    public Object findAll(){
        return TimeIndex.GetValues();
    }
}
