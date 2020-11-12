/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DigitalOceanDTO;
import dto.MovieDTO;
import dto.MoviesDTO;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.Helper;
import utils.HttpUtils;

@Path("digitalocean")
public class DigitalOceanResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeExample facade = FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String digitalOceanURL = "https://api.digitalocean.com/v2/";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static Helper helper = new Helper();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("admin")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public String getDigitalOceanInfo(String digitalOceanJSON) throws IOException {
        String URL = digitalOceanURL + "droplets";
        String digitalOcean = HttpUtils.fetchData(URL);
        DigitalOceanDTO digitalOceanDTO = gson.fromJson(digitalOcean, DigitalOceanDTO.class);
        return gson.toJson(digitalOceanDTO);
    }
}
