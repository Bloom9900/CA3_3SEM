package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PostnordDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

//Todo Remove or change relevant parts before ACTUAL use
@Path("postnord")
public class PostnordResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample facade =  FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("test")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTest() throws IOException {
        String response = HttpUtils.fetchData("https://api2.postnord.com/rest/businesslocation/v1/servicepoint/findNearestByAddress.json?apikey=5d4a85e4661bc2c34e380d9ba5500b0c&countryCode=DK&agreementCountry=DK&city=Herlev&postalCode=2730&streetName=Kamdalen&streetNumber=21");
        PostnordDTO postnordDTO = gson.fromJson(response, PostnordDTO.class);
        return gson.toJson(postnordDTO);
    }
}
