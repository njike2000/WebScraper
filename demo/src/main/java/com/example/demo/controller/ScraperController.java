package com.example.demo.controller;

import com.example.demo.model.ResponseDTO;
import com.example.demo.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class ScraperController {

    @Autowired
    ScraperService scraperService;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("")
    public ResponseEntity<Object> startScraping(Model model, Principal principal) {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

        String url = "https://marketplace.anka.africa/en/categories/men-clothing";
        model.addAttribute("user", userDetails);

        try {
            // Scraping data from the target URL
            scraperService.extractDataFromAnka(responseDTOS, url);

            // Save the scraped data into the database
            scraperService.saveData(responseDTOS);

            // Log the scraped data to the console
            printScrapedData(responseDTOS);

            return new ResponseEntity<>(Map.of("message", "Scraping completed successfully", "status", true),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("message", "Error occurred during scraping", "status", false),
                    HttpStatus.OK);

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
