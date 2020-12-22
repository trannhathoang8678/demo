package plusplus.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import plusplus.demo.entity.Student;
import plusplus.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @GetMapping(value = "/get-news-detail")
    public String getNewsDetail() {
        return "This is detail news";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user = new User("Hoang","trannhathoang8678@gmail.com");
        users.add(user);
        return users;
    }

    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        Student student = new Student("Hoang","trannhathoang8678@gmail.com","SE16");
        students.add(student);
        return students;
    }
}
