package sk.ukf2.spring_2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf2.spring_2.entity.Student;

import java.util.List;

@Repository
public class StudentDAOJpaImpl implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student WHERE firstName = :firstName AND lastName = :lastName",
                Student.class
        );
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public void deleteById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }
}