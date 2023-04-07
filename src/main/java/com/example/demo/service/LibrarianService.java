package com.example.demo.service;

import com.example.demo.enums.Roles;
import com.example.demo.model.Librarian;
import com.example.demo.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {
    @Autowired
    LibrarianRepository librarianRepository;

    public String addLibrarian(Librarian librarian) {
         librarian.setRoles(Roles.LIBRARIAN);

         librarianRepository.save(librarian);
         return "Congratulations! "+librarian.getName() +". /n You are successfully enrolled as Librarian";
    }

    public Librarian getLibrarianById(Integer id) {
        return librarianRepository.findById(id).get();
    }
}
