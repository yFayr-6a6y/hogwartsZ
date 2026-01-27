package hogwarts.demo.repository;

import hogwarts.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // студенты определённого возраста
    Collection<Student> findByAge(int age);

    // студенты, чей возраст находится в диапазоне [min, max]
    Collection<Student> findByAgeBetween(int min, int max);

    // студенты, чьё имя содержит подстроку (без учёта регистра)
    Collection<Student> findByNameIgnoreCaseContaining(String partOfName);

    // студенты, чьё имя начинается с указанной строки
    Collection<Student> findByNameStartingWith(String prefix);

    // студенты определённого факультета (если есть связь @ManyToOne)
    // Collection<Student> findByFaculty(Faculty faculty);

    // студенты определённого факультета по ID факультета
    // Collection<Student> findByFacultyId(Long facultyId);
}