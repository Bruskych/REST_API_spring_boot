package sk.ukf2.spring_2.repository;

import sk.ukf2.spring_2.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}