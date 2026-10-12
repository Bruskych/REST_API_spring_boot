package sk.ukf2.spring_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.ukf2.spring_2.entity.Student;
import sk.ukf2.spring_2.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }
}
