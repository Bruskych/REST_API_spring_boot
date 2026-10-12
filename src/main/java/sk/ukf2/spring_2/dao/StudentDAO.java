package sk.ukf2.spring_2.dao;

import sk.ukf2.spring_2.entity.Student;
import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

//    Student findById(Integer id);
//    Student save(Student student);
//    void deleteById(Integer id);
}
