package com.example.demo.model;

import com.example.demo.enums.Department;
import com.example.demo.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String phoneNo;

    private int age;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @JsonIgnore
    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();

}
