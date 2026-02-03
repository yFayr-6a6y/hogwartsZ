package hogwarts.demo.repository;

import hogwarts.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {



    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    // 1. Количество всех студентов
    @Query("SELECT COUNT(*) FROM Student")
    long getStudentCount();

    // 2. Средний возраст студентов
    // Возвращаем double, чтобы получить 0.0 при отсутствии студентов
    @Query("SELECT COALESCE(AVG(age), 0.0) FROM Student")
    double getAverageAge();

    // 3. Пять последних студентов (по убыванию id)
    @Query(value = "SELECT * FROM students ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudents();
}