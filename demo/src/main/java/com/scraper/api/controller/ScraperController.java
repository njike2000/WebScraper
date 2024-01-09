package com.scraper.api.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseDTO;

import service.ScraperService;


@RestController
@RequestMapping("/scrape")
public class ScraperController {

    private final ScraperService scraperService;

    @Autowired
    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }
   
    @GetMapping("/start")
    public ResponseEntity<String> startScraping() {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        
       String url = "https://www.afrikrea.com/fr/categories/clothing";

        try {
        	  // Scraping data from the target URL
            scraperService.extractDataFromAnka(responseDTOS, url);

            // Save the scraped data into the database
            scraperService.saveData(responseDTOS);

            return ResponseEntity.ok("Scraping process completed and data saved!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during scraping.");
        }
    }
}