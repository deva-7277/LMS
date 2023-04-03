package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @GetMapping("/get-all")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/get-by-name/{name}")
    public Author getAuthorByName(@PathVariable String name){
        return authorService.getAuthorByName(name);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteAuthorById(@PathVariable Integer id){
      authorService.deleteById(id);
    }

}
