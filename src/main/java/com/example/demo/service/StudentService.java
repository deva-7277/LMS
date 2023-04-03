package com.example.demo.service;

import com.example.demo.enums.cardStatus;
import com.example.demo.model.LibraryId;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public String add(Student student) {
        LibraryId libraryId = new LibraryId();
        libraryId.setStatus(cardStatus.ACTIVATED);
        libraryId.setValidTill("05/25");
        libraryId.setStudent(student);

        student.setLibraryId(libraryId);
        studentRepository.save(student);
        return "Student successfully added with student id: "+student.getId()+" with library id card no: "+libraryId.getCardNo();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
