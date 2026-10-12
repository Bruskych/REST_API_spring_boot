package sk.ukf2.spring_2.dao;

import sk.ukf2.spring_2.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}