package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DeveloperFacade;
import facades.ProjectHourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("developer")
public class DeveloperResource {

    @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DeveloperFacade facade =  DeveloperFacade.getDeveloperFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("getall")
    @GET
    @Produces("text/plain")
    public String getAllDevelopers() {
        return gson.toJson(facade.getAllDevelopers());
    }
}
