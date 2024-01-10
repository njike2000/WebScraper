package com.example.demo.controller;

import com.example.demo.model.ResponseDTO;
import com.example.demo.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String startScraping() {
        Set<ResponseDTO> responseDTOS = new HashSet<>();

        String url = "https://www.afrikrea.com/fr/categories/clothing";

        //try {
        // Scraping data from the target URL
        service.extractDataFromAnka(responseDTOS, url);

        // Save the scraped data into the database
        service.saveData(responseDTOS);

        //return ResponseEntity.ok("Scraping process completed and data saved!");
        return "ok entered";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during scraping.");
//        }
    }
}
