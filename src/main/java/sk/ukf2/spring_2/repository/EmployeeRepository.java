package sk.ukf2.spring_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf2.spring_2.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
}