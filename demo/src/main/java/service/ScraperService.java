package service;
import java.util.Set;

import com.example.demo.model.ResponseDTO;

public interface ScraperService {

    public Set<ResponseDTO> getSellerName(String vehicleModel);
    public void extractDataFromAnka(Set<ResponseDTO> responseDTOS, String url);
   

}
