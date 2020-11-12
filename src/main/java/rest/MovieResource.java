package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AdresseDTO;
import dto.MovieDTO;
import dto.PostnordDTO;
import dto.ServicePointsDTO;
import dto.TempDTO;
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

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample facade =  FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String movieURL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    private static final String movieApiKey = "liNcU16apDyGHV0yurSqPUMuAzQnyj98";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static Helper helper = new Helper();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("review")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"user"})
    public String getMovieReview(String movieJSON) throws IOException {
        TempDTO temp = gson.fromJson(movieJSON, TempDTO.class);
        String movie = HttpUtils.fetchData(movieURL + "?query=" + temp.getQuery() + "&api-key=" + movieApiKey);
        MovieDTO movieDTO = gson.fromJson(movie, MovieDTO.class);
        System.out.println("TEST" + movieJSON);
        return gson.toJson(movieDTO);
    }
}
