package CSAA.CSAA;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // <1>
public class HomeController {

    @RequestMapping(value = "/") // <2>
    public String index() {
        return "index"; // <3>
    }

}
