package com.example.demo.commons;

import com.example.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class Logging {
    public void bookIssueStart(){
        log.info("--------------------------------------------------");
        log.info("Book issuing process started");
        log.info("--------------------------------------------------");
    }

    public void bookList(Book book, List<Book> bookList){
        //        logging part for books to the user
        log.info("you are doing this process for book "+book.getBookName());
        log.info("--------------------------------------------------");

        log.info("Books issued to you are ");
        log.info("--------------------------------------------------");
        for(Book b: bookList){
            log.info(b.getBookName());
        }
        log.info("--------------------------------------------------");
    }
    public void bookReturnStart() {
        log.info("--------------------------------------------------");
        log.info("Book returning process started");
        log.info("--------------------------------------------------");
    }
}
