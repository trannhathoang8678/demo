package plusplus.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/get-news-detail")
    public String getNewsDetail() {
        return "This is detail news";
    }
}
