package sk.ukf2.spring_2.service;

import sk.ukf2.spring_2.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}