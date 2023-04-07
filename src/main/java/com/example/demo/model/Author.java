package com.example.demo.model;

import com.example.demo.enums.Roles;
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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String mobNo;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @OneToMany(mappedBy = "author", cascade =  CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();
}
