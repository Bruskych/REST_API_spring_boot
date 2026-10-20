package sk.ukf2.spring_2.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf2.spring_2.entity.Student;
import sk.ukf2.spring_2.exception.EmailAlreadyExistsException;
import sk.ukf2.spring_2.exception.ObjectNotFoundException;
import sk.ukf2.spring_2.repository.StudentRepository;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Študent", id));
    }

    @Transactional
    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new EmailAlreadyExistsException(student.getEmail());
            }
        } else {
            Student existingWithEmail = studentRepository.findByEmail(student.getEmail()).orElse(null);
            if (existingWithEmail != null && !Objects.equals(existingWithEmail.getId(), student.getId())) {
                throw new EmailAlreadyExistsException(student.getEmail());
            }
        }
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new ObjectNotFoundException("Študent", id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Student> getSortedStudents() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }
}