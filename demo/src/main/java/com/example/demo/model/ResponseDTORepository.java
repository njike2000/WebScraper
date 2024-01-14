package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResponseDTORepository extends JpaRepository<ResponseDTO, Long> {
    List<ResponseDTO> findByTitle(String title);
    List<ResponseDTO> findByPriceGreaterThan(String price);

    @Query("SELECT r FROM ResponseDTO r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%', :searchKey, '%'))")
    List<ResponseDTO> findByTitleIgnoreCase( String searchKey);


}
