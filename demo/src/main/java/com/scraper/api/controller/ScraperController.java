package com.scraper.api.controller;

import com.example.demo.model.ResponseDTO;
import service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/scrape")
public class ScraperController {

    private final ScraperService scraperService;

    @Autowired
    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }
   
    @GetMapping("/riyasewana")
    public Set<ResponseDTO> scrapeRiyasewana(@RequestParam String url) {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        scraperService.extractDataFromAnka(responseDTOS, url);
        return responseDTOS;
    }
    @GetMapping(path = "/")
    public Set<ResponseDTO> getVehicleByModel(@PathVariable String vehicleModel) {
        return  scraperService.getSellerName(vehicleModel);

    }
}