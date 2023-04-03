package com.example.demo.model;

import com.example.demo.enums.RetunredOrIssued;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private int studentId;

    @UpdateTimestamp
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    private RetunredOrIssued returnedOrIssued;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryId libraryId;

}
