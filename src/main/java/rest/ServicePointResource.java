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
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

//Todo Remove or change relevant parts before ACTUAL use
@Path("servicepoints")
public class ServicePointResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample facade =  FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String postnordURL = "https://api2.postnord.com/rest/businesslocation/v1/servicepoint/";
    private static final String postnordApiKey = "5d4a85e4661bc2c34e380d9ba5500b0c";
    private static final String weatherURL = "https://api.weatherbit.io/v2.0/current";
    private static final String weatherApiKey = "cdf47dcc554d4589880067a2ea47c310";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("servicepoints")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTest(String address) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        AdresseDTO adresse = gson.fromJson(address, AdresseDTO.class);
        return responseFromExternalServersParallel(es, adresse);
    }
    
    public static String responseFromExternalServersParallel(ExecutorService threadPool, AdresseDTO adresse) throws InterruptedException, ExecutionException, TimeoutException {
        // String variable = adresse.getVariable();
        String city = "Herlev";
        String postalCode = "2730";
        String streetName = "Kamdalen";
        String streetNumber = "21";
        
        Callable<PostnordDTO> postnordTask = new Callable<PostnordDTO>() {
            @Override
            public PostnordDTO call() throws IOException {
                String postnord = HttpUtils.fetchData(postnordURL + "findNearestByAddress.json?apikey=" + postnordApiKey + "&countryCode=DK&agreementCountry=DK&city=" + city
                + "&postalCode=" + postalCode + "&streetName=" + streetName + "&streetNumber=" + streetNumber);
                PostnordDTO postnordDTO = gson.fromJson(postnord, PostnordDTO.class);
                return postnordDTO;
            }
        };
        Callable<WeatherDTO> weatherTask = new Callable<WeatherDTO>() {
            @Override
            public WeatherDTO call() throws IOException {
                String weather = HttpUtils.fetchData(weatherURL + "?key=" + weatherApiKey + "&lang=da&postal_code=" + postalCode + "&country=DK");
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
