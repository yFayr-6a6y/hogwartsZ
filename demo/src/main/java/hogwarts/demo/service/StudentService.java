package hogwarts.demo.service;

import hogwarts.demo.model.Student;
import hogwarts.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Вызван метод createStudent с данными: {}", student);
        Student saved = studentRepository.save(student);
        logger.debug("Студент успешно создан, id = {}", saved.getId());
        return saved;
    }

    public Optional<Student> getStudentById(Long id) {
        logger.info("Вызван метод getStudentById с id = {}", id);
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresentOrElse(
                s -> logger.debug("Найден студент: {}", s),
                () -> logger.warn("Студент с id = {} не найден", id)
        );
        return student;
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Вызван метод updateStudent для id = {}, данные: {}", id, student);
        student.setId(id);
        Student updated = studentRepository.save(student);
        logger.debug("Студент с id = {} успешно обновлён", id);
        return updated;
    }

    public void deleteStudent(Long id) {
        logger.info("Вызван метод deleteStudent с id = {}", id);
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            logger.debug("Студент с id = {} успешно удалён", id);
        } else {
            logger.warn("Попытка удалить несуществующего студента с id = {}", id);
        }
    }

    public Collection<Student> getAllStudents() {
        logger.info("Вызван метод getAllStudents");
        Collection<Student> students = studentRepository.findAll();
        logger.debug("Возвращено {} студентов", students.size());
        return students;
    }

    public Collection<Student> findStudentsByAge(int age) {
        logger.info("Вызван метод findStudentsByAge с возрастом = {}", age);
        Collection<Student> students = studentRepository.findByAge(age);
        logger.debug("Найдено {} студентов возраста {}", students.size(), age);
        return students;
    }

    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        logger.info("Вызван метод findStudentsByAgeBetween: min = {}, max = {}", min, max);
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        logger.debug("Найдено {} студентов в диапазоне {}–{}", students.size(), min, max);
        return students;
    }

    public long getStudentCount() {
        logger.info("Вызван метод getStudentCount");
        long count = studentRepository.getStudentCount();
        logger.debug("Общее количество студентов: {}", count);
        return count;
    }

    public double getAverageAge() {
        logger.info("Вызван метод getAverageAge");
        double avg = studentRepository.getAverageAge();
        logger.debug("Средний возраст студентов: {}", avg);
        return avg;
    }

    public List<Student> findLastFiveStudents() {
        logger.info("Вызван метод findLastFiveStudents");
        List<Student> lastFive = studentRepository.findLastFiveStudents();
        logger.debug("Возвращено {} последних студентов", lastFive.size());
        return lastFive;
    }

    public List<String> getNamesStartingWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && name.toUpperCase().startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    public double getAverageAgeStream() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public long calculateHeavySum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()                     // ← ключевой момент для ускорения
                .reduce(0, Integer::sum);       // или (a, b) -> a + b
    }
}