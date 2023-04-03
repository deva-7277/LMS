package com.example.demo.service;

import com.example.demo.commons.Logging;
import com.example.demo.converters.TransactionConverter;
import com.example.demo.dto.TransactionRequestDto;
import com.example.demo.enums.cardStatus;
import com.example.demo.commons.MailSending;
import com.example.demo.model.Book;
import com.example.demo.model.LibraryId;
import com.example.demo.model.Student;
import com.example.demo.model.Transaction;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    MailSending mailSending;

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    Logging logging;

    public String issueBook(TransactionRequestDto transactionRequestDto) {
        logging.bookIssueStart();

        Student student;
        try {
            student = studentRepository.findById(transactionRequestDto.getStudentId()).get();
        }
        catch (Exception e){
            logger.info("student id not valid :"+transactionRequestDto.getStudentId());
            return "Student id not valid";
        }
        Book book;
        try {
            book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        }
        catch (Exception e){
            logger.info("book id not valid :"+transactionRequestDto.getBookId());
            return "Book id not valid";
        }
        LibraryId libraryId = student.getLibraryId();
        if(libraryId.getStatus()== cardStatus.BLOCKED){
            logger.info(student.getName()+" your library card is blocked :"+student.getLibraryId().getCardNo());
            return student.getName()+" your library card is blocked :"+student.getLibraryId().getCardNo();
        }
        List<Book> bookList = student.getLibraryId().getBookList();
        if(bookList.contains(book)){
            logger.info(book.getBookName()+" is already issued to you, can't issue you again!");
            return book.getBookName()+" is already issued to you, can't issue you again!";
        }
        book.setLibraryId(libraryId);
        bookList.add(book);
        book.setQuantity(book.getQuantity()-1);

//        Setting details as per transaction model
        Transaction transaction = transactionConverter.getTransactionIssueModel(transactionRequestDto,book,libraryId);


        logger.info(student.getName()+" is having "+bookList.size() +" books from library");
        bookRepository.save(book);

        mailSending.sendIssueMail(student,book);

        return student.getName()+" has issued "+ book.getBookName() +" on "+transaction.getTransactionDate()
                +" and has "+bookList.size() +" books on his name";
    }

    public String returnBook(TransactionRequestDto transactionRequestDto) {
       logging.bookReturnStart();
//        Getting the student from student id provided in TransactionRequestDto
        Student student;
        try {
            student = studentRepository.findById(transactionRequestDto.getStudentId()).get();
        }
        catch (Exception e){
            logger.info("student id not valid :"+transactionRequestDto.getStudentId());
            return "Student id not valid";
        }

//        Getting the Book from book id provided in TransactionRequestDto
        Book book;
        try {
            book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        }
        catch (Exception e){
            logger.info("book id not valid :"+transactionRequestDto.getBookId());
            return "Book id not valid";
        }

        List<Book> bookList = student.getLibraryId().getBookList();

        logging.bookList(book,bookList);

        if(!bookList.contains(book)){
            logger.info(book.getBookName()+" was not issued to "+student.getName()+", please check book id and student id again :"+student.getId());
            return book.getBookName()+" was not issued to "+student.getName()+", please check book id and student id again :"+student.getId();
        }


//       removing book from bookList and setting library id for it as null means it is in library
        LibraryId libraryId = student.getLibraryId();
        bookList.remove(book);
        book.setLibraryId(null);
        book.setQuantity(book.getQuantity()+1);

//        Setting details as per transaction model using converter
        Transaction transaction = transactionConverter.getTransactionReturnModel(transactionRequestDto,book,libraryId);


        logger.info(student.getName()+" is having "+bookList.size() +" books from library");
        studentRepository.save(student);
        bookRepository.save(book);

//        For sending email
        mailSending.sendReturnMail(student,book);

        return student.getName()+" has returned "+ book.getBookName() +" on "+transaction.getTransactionDate()
                +" and has "+bookList.size() +" books on his name";
    }
}
