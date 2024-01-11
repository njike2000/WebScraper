package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseDTORepository extends JpaRepository<ResponseDTO, Long> {
    List<ResponseDTO> findByTitle(String title);
    List<ResponseDTO> findByPriceGreaterThan(String price);

}
