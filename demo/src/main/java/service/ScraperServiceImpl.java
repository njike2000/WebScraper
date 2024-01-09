package service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.ResponseDTO;

@Service
public abstract class ScraperServiceImpl implements ScraperService {
    // Reading data from property file to a list
    @Value("#{'${website.urls}'.split(',')}")
    private List<String> urls;

    @Override
    public List<String> getUrls() {
        return urls;
    }



    public void extractDataFromAnka(Set<ResponseDTO> responseDTOS, String url) {

        try {
            // loading the HTML to a Document Object
            Document document = Jsoup.connect(url).get();
            // Selecting the element which contains the ad list
            Elements contentelement = document.getElementsByClass("content");
            // getting all the <a> tag elements inside the content div tag
            
         for(Element content : contentelement) {   
            Elements spanelement = content.getElementsByTag("span");
            // traversing through the elements
            for (Element ads : spanelement) {
                ResponseDTO responseDTO = new ResponseDTO();

                if (!StringUtils.isEmpty(ads.attr("title"))) {
                    // mapping data to the model class
                    responseDTO.setTitle(ads.attr("title"));
                    responseDTO.setUrl(ads.attr("href"));
                }
                
                
                Element priceElement = ads.selectFirst(".price-tag");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text().trim());
                }

                Element nameElement = ads.selectFirst(".name");
                if (nameElement != null) {
                    responseDTO.setName(nameElement.text().trim());
                }
                
                if (responseDTO.getUrl() != null)
                    responseDTOS.add(responseDTO);
                
                }
                
               
            }
         
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void saveData(Set<ResponseDTO> responseDTOS) {
        // Logic to save data to a database
        // Implement this method based on your database configuration
    }




}