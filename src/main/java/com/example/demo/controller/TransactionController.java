package com.example.demo.controller;

import com.example.demo.dto.TransactionRequestDto;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/issue-book")
    public String issueBook(@RequestBody TransactionRequestDto transactionRequestDto){
        return transactionService.issueBook(transactionRequestDto);
    }

    @PostMapping("/return-book")
    public String returnBook(@RequestBody TransactionRequestDto transactionRequestDto){
        return transactionService.returnBook(transactionRequestDto);
    }
}
