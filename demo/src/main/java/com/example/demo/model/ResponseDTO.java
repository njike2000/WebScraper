package com.example.demo.model;

import lombok.Data;

@Data
public class ResponseDTO {

    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    String title;
    String url;
    String price;
    String name;



}
