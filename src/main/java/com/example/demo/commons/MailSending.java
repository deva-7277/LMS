package com.example.demo.commons;

import com.example.demo.model.Book;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MailSending {

    @Autowired
    JavaMailSender emailSender;


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    public void sendReturnMail(Student student, Book book){

        String text = "Greetings from the \u001BDevanand's Libraray\n\n "+ "Hi,\u001B"+student.getName() +
                " \nThanks for returning the book \u001B"+book.getBookName()+" to the library on " + dtf.format(now)
                +" \nVisit again! "+ "\n\n Regards,\n Library Management, \n Devanand's Library";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("devadkale@gmail.com");
        message.setTo(student.getEmail());
        message.setSubject("Return of Book "+book.getBookName() +" to the Deanand's library");
        message.setText(text);
        emailSender.send(message);
    }

    public void sendIssueMail(Student student, Book book){

        String text = "Greetings from the \u001BDevanand's Libraray \n\n"+ "Hi,\u001B"+student.getName() +
                "\n You have issued the book \u001B'"+book.getBookName()+ "' from the library on " + dtf.format(now)
                +". Please return it before time and handle it carefully.\n visit again! "
                + "\n\n Regards,\n Library Management, \n Devanand's Library";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("devadkale@gmail.com");
        message.setTo(student.getEmail());
        message.setSubject("Issuance of Book "+book.getBookName() +" from the Deanand's library");
        message.setText(text);
        emailSender.send(message);
    }
}
