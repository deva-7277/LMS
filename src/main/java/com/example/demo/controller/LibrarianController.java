package com.example.demo.controller;

import com.example.demo.model.Librarian;
import com.example.demo.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    LibrarianService librarianService;

    @GetMapping("/get-librarian")
    public @ResponseBody Librarian getLibrarian(@RequestParam Integer id){
         return librarianService.getLibrarianById(id);
    }

    @PostMapping("/add-librarian")
    public ResponseEntity<Librarian> addLibrarian(@RequestBody Librarian librarian){
        librarianService.addLibrarian(librarian);
        return new ResponseEntity<>(librarian,HttpStatus.CREATED);
    }

}
