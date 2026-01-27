package hogwarts.demo.repository;

import hogwarts.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByNameIgnoreCaseContaining(String partOfName);

    Collection<Student> findByNameStartingWith(String prefix);

}