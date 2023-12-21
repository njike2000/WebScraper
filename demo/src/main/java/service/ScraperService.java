package service;
import java.util.Set;

import com.example.demo.model.ResponseDTO;

public interface ScraperService {

    public Set<ResponseDTO> getSellerName(String vehicleModel);
    public void extractDataFromRiyasewana(Set<ResponseDTO> responseDTOS, String url);
    public void extractDataFromIkman(Set<ResponseDTO> responseDTOS, String url);

}
