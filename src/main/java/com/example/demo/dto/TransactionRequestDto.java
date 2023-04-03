package com.example.demo.dto;

import com.example.demo.enums.RetunredOrIssued;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TransactionRequestDto {

    private int studentId;

    private int bookId;

    private RetunredOrIssued retunredOrIssued;
}
