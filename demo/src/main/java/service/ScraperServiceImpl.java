package service;

import com.example.demo.model.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public abstract class ScraperServiceImpl implements ScraperService {
    // Reading data from property file to a list
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;



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
    
    public Set<ResponseDTO> getModelName(String model) {
        // Using a set here to only store unique elements
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        // Traversing through the urls
        for (String url : urls) {

            if (url.contains("afrikrea")) {
                // method to extract data from Ikman.lk
                extractDataFromAnka(responseDTOS, url + model);
       
            }

        }
        return responseDTOS;
        
    }



}