package sk.ukf2.spring_2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf2.spring_2.entity.Employee;
import sk.ukf2.spring_2.exception.EmailAlreadyExistsException;
import sk.ukf2.spring_2.exception.ObjectNotFoundException;
import sk.ukf2.spring_2.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Zamestnanec", id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else {
            Employee existingWithEmail = employeeRepository.findByEmail(employee.getEmail()).orElse(null);
            if (existingWithEmail != null && !Objects.equals(existingWithEmail.getId(), employee.getId())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Zamestnanec", id);
        }
        employeeRepository.deleteById(id);
    }
}