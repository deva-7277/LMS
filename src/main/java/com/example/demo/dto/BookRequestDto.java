package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BookRequestDto {

    private String bookName;
    private int price;
    private int quantity;
    private int authorId;

}
