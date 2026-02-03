package hogwarts.demo.controller;


import hogwarts.demo.model.Faculty;
import hogwarts.demo.model.Student;
import hogwarts.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ──────────────── CRUD-операции ───────────────────────────────────────

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/age/{age}")
    public Collection<Student> getStudentsByAge(@PathVariable int age) {
        return studentService.findStudentsByAge(age);
    }

    /**
     * Получение студентов, чей возраст находится в указанном диапазоне
     */
    @GetMapping("/age")
    public Collection<Student> getStudentsByAgeBetween(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return studentService.findStudentsByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(s -> ResponseEntity.ok(s.getFaculty()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public long getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("/avg-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.findLastFiveStudents();
    }

}