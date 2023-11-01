package com.example.coderlab.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private UserEntity student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<Assessment> assessments;
}
