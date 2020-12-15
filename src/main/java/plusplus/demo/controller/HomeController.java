package plusplus.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import plusplus.demo.entity.Student;
import plusplus.demo.entity.User;

import java.util.List;

@RestController
public class HomeController {
    @GetMapping(value = "/get-news-detail")
    public String getNewsDetail() {
        return "This is detail news";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return null;
    }

    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        return null;
    }
}
