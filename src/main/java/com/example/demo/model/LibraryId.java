package com.example.demo.model;

import com.example.demo.enums.cardStatus;
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
public class LibraryId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @Enumerated(EnumType.STRING)
    private cardStatus status;

    private String validTill;

    @JoinColumn
    @OneToOne
    @JsonIgnore
    private Student student;

    @OneToMany(mappedBy = "libraryId", cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();

    @OneToMany(mappedBy = "libraryId", cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();
}
