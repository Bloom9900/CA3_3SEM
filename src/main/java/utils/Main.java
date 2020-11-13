package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.WeatherResponseDTO;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void main(String[] args) throws IOException {
//        String URL = "https://api2.postnord.com/rest/businesslocation/v1/servicepoint/";
//        String apiKey = "5d4a85e4661bc2c34e380d9ba5500b0c";
//        String city = "Herlev";
//        String postalCode = "2730";
//        String streetName = "Kamdalen";
//        String streetNumber = "21";
//        String response = HttpUtils.fetchData(URL + "findNearestByAddress.json?apikey=" + apiKey + "&countryCode=DK&agreementCountry=DK&city=" + city
//                + "&postalCode=" + postalCode + "&streetName=" + streetName + "&streetNumber=" + streetNumber);
//        System.out.println(response);
//        System.out.println(URL + "findNearestByAddress.json?apikey=" + apiKey + "&countryCode=DK&agreementCountry=DK&city=" + city
//                + "&postalCode=" + postalCode + "&streetName=" + streetName + "&streetNumber=" + streetNumber);   
        
//        String URL = "https://api.weatherbit.io/v2.0/current?key=cdf47dcc554d4589880067a2ea47c310&lang=da&postal_code=2730&country=DK";
//        String response = HttpUtils.fetchData(URL);
        
//        WeatherResponseDTO weather = gson.fromJson(response, WeatherResponseDTO.class);
//        System.out.println(weather.getData());
    }
}
