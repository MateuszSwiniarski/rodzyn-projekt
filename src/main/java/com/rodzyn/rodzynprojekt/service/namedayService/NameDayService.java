package com.rodzyn.rodzynprojekt.service.namedayService;

import com.rodzyn.rodzynprojekt.model.nameday.Country;
import com.rodzyn.rodzynprojekt.model.nameday.Nameday;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class NameDayService {

    public NameDayService() {
        nameDayList().keySet().forEach(System.out::println);

        System.out.println(getNames("cz"));
        System.out.println(getNames("at"));

        System.out.println(getCountry().getCountry());
        System.out.println(getNameDays());
    }

    public String getNameDays(){
        System.out.println("country z servisu: " + getCountry());
        if(getCountry().getCountry()==null){
            return "Today name day is: " + getNames("pl");
        }else{
            System.out.println(getCountry().getCountry());
            return "Today name day is: " + getNames(getCountry().getCountry());
        }
    }

    public String getNames(String country){
        return nameDayList().get(country).toString();
    }

    public Map<String, Object> nameDayList(){
        Map<String, Object> nameDayList = getNameDayDetails().getData().getNamedays().getAdditionalProperties();
        nameDayList.put("pl", getNameDayDetails().getData().getNamedays().getPl());
        return nameDayList;
    }

    public String getData(){
        Integer day = getNameDayDetails().getData().getDates().getDay();
        Integer month = getNameDayDetails().getData().getDates().getMonth();
        return "Today is: " + day + "-" + month;
    }

    public Nameday getNameDayDetails(){
        RestTemplate restTemplate = new RestTemplate();
        Nameday nameday = restTemplate.getForObject("https://api.abalin.net/today", Nameday.class);
        return nameday;
    }

    private Country country = new Country();

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
