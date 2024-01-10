package com.example.demo.service;

import com.example.demo.model.ResponseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ScraperServiceImp implements ScraperService{

    @Value("#{'${website.urls}'.split(',')}")
    private List<String> urls;

    //@Autowired
   // private ResponseDTORepository responseDTORepository;

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
        for (ResponseDTO responseDTO : responseDTOS) {
             //Assuming ResponseDTORepository extends JpaRepository<ResponseDTO, Long>
           // responseDTORepository.save(responseDTO);
        }
        System.out.println("==============Save==");
    }

}
