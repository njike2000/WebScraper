package com.example.demo.service;

import com.example.demo.model.ResponseDTO;

import java.util.List;
import java.util.Set;

public interface ScraperService {

    public void extractDataFromAnka(Set<ResponseDTO> responseDTOS, String url);
    public void saveData(Set<ResponseDTO> responseDTOS);
    public List<ResponseDTO> getProducts(String searchKey);

}
