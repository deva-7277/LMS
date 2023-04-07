package com.example.demo.converters;

import com.example.demo.dto.TransactionRequestDto;
import com.example.demo.enums.RetunredOrIssued;
import com.example.demo.model.Book;
import com.example.demo.model.Librarian;
import com.example.demo.model.LibraryId;
import com.example.demo.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionConverter {

    public Transaction getTransactionIssueModel(TransactionRequestDto requestDto, Book book, LibraryId libraryId, Librarian librarian){
        Transaction transaction = Transaction.builder()
                .studentId(requestDto.getStudentId())
                .returnedOrIssued(RetunredOrIssued.ISSUED)
                .build();

        transaction.setBook(book);
        transaction.setLibraryId(libraryId);
        transaction.setLibrarian(librarian);
        return transaction;
    }

    public Transaction getTransactionReturnModel(TransactionRequestDto requestDto , Book book, LibraryId libraryId){
        Transaction transaction = Transaction.builder()
                .studentId(requestDto.getStudentId())
                .returnedOrIssued(RetunredOrIssued.RETURNED)
                .build();

        transaction.setBook(book);
        transaction.setLibraryId(libraryId);

        return transaction;
    }

}
