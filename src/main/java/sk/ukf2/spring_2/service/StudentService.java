package sk.ukf2.spring_2.service;

import sk.ukf2.spring_2.entity.Student;
import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);

    void deleteById(Integer id);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> getSortedStudents();
}