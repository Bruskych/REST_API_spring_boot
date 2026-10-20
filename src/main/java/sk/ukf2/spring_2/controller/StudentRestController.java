package sk.ukf2.spring_2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf2.spring_2.entity.Student;
import sk.ukf2.spring_2.response.ResponseFactory;
import sk.ukf2.spring_2.response.ResponseWrapper;
import sk.ukf2.spring_2.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final StudentService studentService;
    private final ResponseFactory response;

    @Autowired
    public StudentRestController(StudentService studentService, ResponseFactory response) {
        this.studentService = studentService;
        this.response = response;
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper<List<Student>>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return response.ok("Študenti úspešne načítaní", students);
    }

    @GetMapping("/students/search")
    public ResponseEntity<ResponseWrapper<List<Student>>> searchStudents(
            @RequestParam String firstName, @RequestParam String lastName) {
        List<Student> students = studentService.findByFirstNameAndLastName(firstName, lastName);
        return response.ok("Študenti úspešne načítaní", students);
    }

    @GetMapping("/students/sorted")
    public ResponseEntity<ResponseWrapper<List<Student>>> getSortedStudents() {
        List<Student> students = studentService.getSortedStudents();
        return response.ok("Študenti úspešne zoradení", students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> getStudentById(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        return response.ok("Študent úspešne načítaný", student);
    }

    @PostMapping("/students")
    public ResponseEntity<ResponseWrapper<Student>> createStudent(@Valid @RequestBody Student student) {
        student.setId(null);
        Student createdStudent = studentService.save(student);
        return response.created("Študent úspešne vytvorený", createdStudent);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> updateStudent(
            @PathVariable Integer id, @Valid @RequestBody Student student) {
        studentService.findById(id);
        student.setId(id);
        Student updatedStudent = studentService.save(student);
        return response.ok("Študent úspešne aktualizovaný", updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
        return response.ok("Študent úspešne zmazaný");
    }
}