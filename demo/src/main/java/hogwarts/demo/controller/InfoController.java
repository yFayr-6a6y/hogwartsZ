package hogwarts.demo.controller;

import hogwarts.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final StudentService studentService;

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    public InfoController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/port")
    public int getPort() {
        return serverPort;
    }

    @GetMapping("/heavy-sum")
    public long getHeavySum() {
        return studentService.calculateHeavySum();
    }
}