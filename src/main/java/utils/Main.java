package utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String URL = "https://api2.postnord.com/rest/businesslocation/v1/servicepoint/";
        String apiKey = "5d4a85e4661bc2c34e380d9ba5500b0c";
        String city = "Herlev";
        String postalCode = "2730";
        String streetName = "Kamdalen";
        String streetNumber = "21";
        String response = HttpUtils.fetchData(URL + "findNearestByAddress.json?apikey=" + apiKey + "&countryCode=DK&agreementCountry=DK&city=" + city
                + "&postalCode=" + postalCode + "&streetName=" + streetName + "&streetNumber=" + streetNumber);
        System.out.println(response);
        System.out.println(URL + "findNearestByAddress.json?apikey=" + apiKey + "&countryCode=DK&agreementCountry=DK&city=" + city
                + "&postalCode=" + postalCode + "&streetName=" + streetName + "&streetNumber=" + streetNumber);
    }
}
