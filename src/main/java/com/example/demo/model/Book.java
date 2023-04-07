package com.example.demo.model;

import com.example.demo.enums.RetunredOrIssued;
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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    private String bookName;

    private int price;

    private int quantity;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Author author;

    @ManyToOne
    @JsonIgnore
    LibraryId libraryId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Transaction> transactionList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    Librarian librarian;
}
