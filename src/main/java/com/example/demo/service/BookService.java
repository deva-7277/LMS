package com.example.demo.service;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;


    public String addBook(BookRequestDto bookRequestDto) {
        Author author;
        try {
            author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        }
        catch (Exception e){
            return "Author for this id is not present in System";
        }
        Book book = new Book();
        book.setBookName(bookRequestDto.getBookName());
        book.setPrice(bookRequestDto.getPrice());
        book.setQuantity(bookRequestDto.getQuantity());
        book.setAuthor(author);
        bookRepository.save(book);
        return bookRequestDto.getBookName()+" added to library ";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
