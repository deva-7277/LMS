package com.example.demo.model;

import com.example.demo.enums.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private int age;

    @Enumerated(EnumType.STRING)
    private Department dept;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private LibraryId libraryId;

}
