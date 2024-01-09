package service;
import java.util.List;
import java.util.Set;

import com.example.demo.model.ResponseDTO;

public interface ScraperService {

    

    public void extractDataFromAnka(Set<ResponseDTO> responseDTOS, String url);
	public void saveData(Set<ResponseDTO> responseDTOS);
	public List<String> getUrls();
   

}
