package hogwarts.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}