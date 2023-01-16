package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("project")
public class ProjectResource {

    @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProjectFacade facade =  ProjectFacade.getProjectFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("getall")
    @GET
    @Produces("text/plain")
    public String getProjects() {
        return gson.toJson(facade.getAllProjects());
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createProject(String jsonInput){
        ProjectDTO projectDTO = gson.fromJson(jsonInput, ProjectDTO.class);
        ProjectDTO returned = facade.createProject(projectDTO);
        return Response.ok().entity(gson.toJson(returned)).build();
    }
}
