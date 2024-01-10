package com.example.demo.controller;

import com.example.demo.model.ResponseDTO;
import com.example.demo.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/scrape")
public class ScraperController {

    @Autowired
    ScraperService service;

    @GetMapping("/get")
    public ResponseEntity<String> startScraping() {
        Set<ResponseDTO> responseDTOS = new HashSet<>();

        String url = "https://www.afrikrea.com/fr/categories/clothing";

        try {
         //Scraping data from the target URL
        service.extractDataFromAnka(responseDTOS, url);

         //Save the scraped data into the database
        service.saveData(responseDTOS);


            // Log the scraped data to the console
            printScrapedData(responseDTOS);

        return ResponseEntity.ok("Scraping process completed and data saved!");
        //return "ok entered";
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during scraping.");
        }
    }

    private void printScrapedData(Set<ResponseDTO> responseDTOS) {
        System.out.println("=== Scraped Data ===");
        for (ResponseDTO responseDTO : responseDTOS) {
            System.out.println("Title: " + responseDTO.getTitle());
            System.out.println("URL: " + responseDTO.getUrl());
            System.out.println("Price: " + responseDTO.getPrice());
            System.out.println("Name: " + responseDTO.getName());
            System.out.println("====================");
        }
    }
}
