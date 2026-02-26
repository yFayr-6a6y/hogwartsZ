package hogwarts.demo.controller;

import hogwarts.demo.model.Faculty;
import hogwarts.demo.model.Student;
import hogwarts.demo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")           // или "/student"
public class FacultyController {

    private final FacultyService service;
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService service, FacultyService facultyService) {
        this.service = service;
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return service.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getById(@PathVariable Long id) {
        return service.getFacultyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return service.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return service.getAllFaculties();
    }

    // + все методы поиска, которые есть в сервисе
    @GetMapping("/color/{color}")
    public Collection<Faculty> getByColor(@PathVariable String color) {
        return service.findFacultiesByColor(color);
    }

    @GetMapping("/name/{name}")
    public Collection<Faculty> getByName(@PathVariable String name) {
        return service.findFacultiesByName(name);
    }

    @GetMapping("/search")
    public Collection<Faculty> searchFaculties(@RequestParam String q) {
        return facultyService.findByNameOrColor(q);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getFacultyStudents(@PathVariable Long id) {
        return facultyService.getFacultyById(id)
                .map(f -> ResponseEntity.ok(f.getStudents()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/longest-name")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }
}
