package hogwarts.demo;

import hogwarts.demo.controller.FacultyController;
import hogwarts.demo.model.Faculty;
import hogwarts.demo.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty("Slytherin", "green");

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "name": "Slytherin",
                              "color": "green"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Slytherin"))
                .andExpect(jsonPath("$.color").value("green"));
    }

    @Test
    void testGetAllFaculties() throws Exception {
        when(facultyService.getAllFaculties()).thenReturn(List.of(new Faculty()));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testSearchFaculties() throws Exception {
        when(facultyService.findByNameOrColor("gry")).thenReturn(List.of(new Faculty()));

        mockMvc.perform(get("/faculty/search")
                        .param("q", "gry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Добавьте тесты для:
    // - getById
    // - update
    // - delete
    // - getFacultyStudents (если эндпоинт есть)
}