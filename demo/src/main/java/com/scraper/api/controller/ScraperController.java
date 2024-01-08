package com.scraper.api.controller;

import com.example.demo.model.ResponseDTO;
import service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping(path = "/")
public class ScraperController {
    
    @Autowired
    ScraperService scraperService;

    @GetMapping(path = "/")
    public Set<ResponseDTO> getVehicleByModel(@PathVariable String vehicleModel) {
        return  scraperService.getSellerName(vehicleModel);
    }
}