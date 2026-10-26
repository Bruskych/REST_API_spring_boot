package sk.ukf2.spring_2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf2.spring_2.entity.Employee;
import sk.ukf2.spring_2.response.ResponseFactory;
import sk.ukf2.spring_2.response.ResponseWrapper;
import sk.ukf2.spring_2.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final ResponseFactory response;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ResponseFactory response) {
        this.employeeService = employeeService;
        this.response = response;
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseWrapper<List<Employee>>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return response.ok("Zamestnanci úspešne načítaní", employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        return response.ok("Zamestnanec úspešne načítaný", employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseWrapper<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        Employee createdEmployee = employeeService.save(employee);
        return response.created("Zamestnanec úspešne vytvorený", createdEmployee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> updateEmployee(
            @PathVariable Integer id, @Valid @RequestBody Employee employee) {
        employeeService.findById(id); // Проверка существования
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return response.ok("Zamestnanec úspešne aktualizovaný", updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return response.ok("Zamestnanec úspešne zmazaný");
    }
}