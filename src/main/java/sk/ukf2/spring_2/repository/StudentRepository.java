package sk.ukf2.spring_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf2.spring_2.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findAllByOrderByLastNameAsc();
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}