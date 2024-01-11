package com.example.demo.service;

import com.example.demo.model.ResponseDTO;
import com.example.demo.model.ResponseDTORepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ScraperServiceImp implements ScraperService{

    @Value("#{'${website.urls}'.split(',')}")
    private List<String> urls;

    @Autowired
   private ResponseDTORepository responseDTORepository;

    @Override
    public List<String> getUrls()
    {
        System.out.println("URLs: " + urls);
        return urls;
    }



    public void extractDataFromAnka(Set<ResponseDTO> responseDTOS, String url) {

        try {
            // loading the HTML to a Document Object
            Document document = Jsoup.connect(url).get();
            System.out.println("HTML Content: " + document);
            // Selecting the element which contains the ad list
            Elements contentelement = document.getElementsByClass("content");
            System.out.println("Content Element Size: " + contentelement.size());
            // getting all the <a> tag elements inside the content div tag

            for(Element contentElement : contentelement) {

                    ResponseDTO responseDTO = new ResponseDTO();
                Element nameElement = contentElement.selectFirst(".author .name");
                if (nameElement != null) {
                    responseDTO.setName(nameElement.text().trim());
                }

                // Extracting title from the nested h3 element
                Element titleElement = contentElement.selectFirst(".title h3 a");
                if (titleElement != null) {
                    responseDTO.setTitle(titleElement.text().trim());
                    responseDTO.setUrl(titleElement.attr("href"));
                }

                // Extracting price from the nested span with class "price-tag"
                Element priceElement = contentElement.selectFirst(".description .price-tag");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text().trim());
                }

                    if (responseDTO.getUrl() != null)
                        responseDTOS.add(responseDTO);




            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Transactional
    @Override
    public void saveData(Set<ResponseDTO> responseDTOS) {
        try {
            responseDTORepository.saveAll(responseDTOS);
            System.out.println("==============Save==");
            System.out.println("Size of responseDTOS: " + responseDTOS.size());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed, possibly rollback the transaction.
        }

    }
}


