package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AdresseDTO;
import dto.PostnordDTO;
import dto.ServicePointsDTO;
import dto.WeatherDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.Helper;
import utils.HttpUtils;
import utils.Keys;

//Todo Remove or change relevant parts before ACTUAL use
@Path("servicepoints")
public class ServicePointResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample facade =  FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String postnordURL = "https://api2.postnord.com/rest/businesslocation/v1/servicepoint/";
    private static final String weatherURL = "https://api.weatherbit.io/v2.0/current";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static Helper helper = new Helper();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("servicepoints")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String getTest(String address) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        AdresseDTO adresse = gson.fromJson(address, AdresseDTO.class);
        return responseFromExternalServersParallel(es, adresse);
    }
    
    public static String responseFromExternalServersParallel(ExecutorService threadPool, AdresseDTO adresse) throws InterruptedException, ExecutionException, TimeoutException {
        // String variable = adresse.getVariable();
        String city = adresse.getCity();
        String postalCode = adresse.getPostalCode();
        String streetName = adresse.getStreetName();
        String streetNumber = adresse.getStreetNumber();
        
        Callable<PostnordDTO> postnordTask = new Callable<PostnordDTO>() {
            @Override
            public PostnordDTO call() throws IOException {
                String fullURL = (postnordURL + "findNearestByAddress.json?apikey=" + Keys.postNordKey + "&countryCode=DK&agreementCountry=DK&city=" + helper.removeSpaces(city)
                + "&postalCode=" + helper.removeSpaces(postalCode) + "&streetName=" + helper.removeSpaces(streetName) + "&streetNumber=" + helper.removeSpaces(streetNumber));
                String postnord = HttpUtils.fetchData(fullURL);
                PostnordDTO postnordDTO = gson.fromJson(postnord, PostnordDTO.class);
                return postnordDTO;
            }
        };
        Callable<WeatherDTO> weatherTask = new Callable<WeatherDTO>() {
            @Override
            public WeatherDTO call() throws IOException {
                String weather = HttpUtils.fetchData(weatherURL + "?key=" + Keys.weatherKey + "&lang=da&postal_code=" + postalCode + "&country=DK");
                WeatherDTO weatherDTO = gson.fromJson(weather, WeatherDTO.class);
                return weatherDTO;
            }
        };
        
        Future<PostnordDTO> futurePostnord = threadPool.submit(postnordTask);
        Future<WeatherDTO> futureWeather = threadPool.submit(weatherTask);
        
        PostnordDTO postnord = futurePostnord.get(3, TimeUnit.SECONDS);
        WeatherDTO weather = futureWeather.get(3, TimeUnit.SECONDS);
        
        ServicePointsDTO combinedDTO = new ServicePointsDTO(postnord, weather);
        String combinedJSON = gson.toJson(combinedDTO);
        
        return combinedJSON;
    }
  
    
}
