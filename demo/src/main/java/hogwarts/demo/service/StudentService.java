package hogwarts.demo.service;          // ← было ru.hogwarts.school.service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hogwarts.demo.model.Student;               // ← было ru.hogwarts.school.model
import hogwarts.demo.repository.StudentRepository; // ← было ru.hogwarts.school.repository

import java.util.Collection;
import java.util.Optional;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ── CRUD через репозиторий ─────────────────────────────────────

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Примеры методов поиска по условию (по заданию должны использоваться)
    public Collection<Student> findStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    // и т.д. — остальные методы поиска, которые у вас уже были
}