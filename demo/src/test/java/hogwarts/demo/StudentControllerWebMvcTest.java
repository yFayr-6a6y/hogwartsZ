package hogwarts.demo;

import hogwarts.demo.controller.StudentController;
import hogwarts.demo.model.Student;
import hogwarts.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testCreateStudent() throws Exception {
        Student createdStudent = new Student();
        createdStudent.setId(1L);
        createdStudent.setName("Ron Weasley");
        createdStudent.setAge(11);

        when(studentService.createStudent(any(Student.class))).thenReturn(createdStudent);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "name": "Ron Weasley",
                              "age": 11
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ron Weasley"))
                .andExpect(jsonPath("$.age").value(11));
    }

    @Test
    void testGetStudentById_found() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Hermione Granger");
        student.setAge(12);

        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hermione Granger"))
                .andExpect(jsonPath("$.age").value(12));
    }

    @Test
    void testGetStudentById_notFound() throws Exception {
        when(studentService.getStudentById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/student/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(new Student(), new Student()));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testGetStudentsByAgeBetween() throws Exception {
        when(studentService.findStudentsByAgeBetween(10, 20))
                .thenReturn(List.of(new Student(), new Student()));

        mockMvc.perform(get("/student/age")
                        .param("min", "10")
                        .param("max", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Пример теста на обновление (если эндпоинт PUT /student/{id} существует)
    @Test
    void testUpdateStudent() throws Exception {
        Student updated = new Student();
        updated.setId(1L);
        updated.setName("Updated Name");
        updated.setAge(15);

        when(studentService.updateStudent(any(Long.class), any(Student.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "name": "Updated Name",
                              "age": 15
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }
}