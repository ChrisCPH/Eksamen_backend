package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectHourDTO;
import entities.ProjectHour;
import facades.ProjectHourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("project_hour")
public class ProjectHourResource {

    @Context
    private UriInfo context;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProjectHourFacade facade =  ProjectHourFacade.getProjectHourFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Path("getdev/{name}")
    @GET
    @Produces("text/plain")
    public String getAllDevProjects(@PathParam("name") String developerName) {
        return gson.toJson(facade.getAllDevProjects(developerName));
    }

    @Path("getdev/{name}/{project}")
    @GET
    @Produces("text/plain")
    public String getAllDevProjects(@PathParam("name") String developerName, @PathParam("project") String projectName) {
        return gson.toJson(facade.getSpecificDevProject(developerName, projectName));
    }

    @Path("get/{name}")
    @GET
    @Produces("text/plain")
    public String getProjectPrice(@PathParam("name") String projectName) {
        return gson.toJson(facade.getProjectPrice(projectName));
    }

    @POST
    @Path("assign/{developer}/{project}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response assignDeveloper(@PathParam("developer") Integer developerId, @PathParam("project") Integer projectId, String jsonInput){
        ProjectHourDTO projectHourDTO = gson.fromJson(jsonInput, ProjectHourDTO.class);
        ProjectHourDTO returned = facade.assignDeveloper(projectHourDTO, developerId, projectId);
        return Response.ok().entity(gson.toJson(returned)).build();
    }

    @PUT
    @Path("update/{id}/{developer}/{project}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateHours(@PathParam("id") Integer id, @PathParam("developer") Integer developerId, @PathParam("project") Integer projectId, String jsonInput){
        ProjectHourDTO projectHourDTO = gson.fromJson(jsonInput, ProjectHourDTO.class);
        projectHourDTO.setId(id);
        ProjectHourDTO returned = facade.updateHours(projectHourDTO, developerId, projectId);
        return Response.ok().entity(gson.toJson(returned)).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id) {
        ProjectHourDTO projectHourDTO = facade.deleteProjectHour(id);
        return Response.ok().entity(gson.toJson(projectHourDTO)).build();
    }
}
