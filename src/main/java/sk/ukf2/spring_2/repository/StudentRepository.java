package sk.ukf2.spring_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf2.spring_2.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findAllByOrderByLastNameAsc();
}
