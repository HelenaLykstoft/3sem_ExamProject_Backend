package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.CRUDentityDTO;
import errorhandling.API_Exception;
import facades.CRUDentityFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("crud")
public class CRUDentityResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final CRUDentityFacade facade = CRUDentityFacade.getCRUDentityFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo(){
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    public Response getAllCRUDentities() {
        List<CRUDentityDTO> entities = facade.getAllCRUDentities();
        return Response.ok().entity(entities).build();
    }

    @GET
    @Path("{id}")
    public Response getCRUDentityById(@PathParam("id") int id) {
        CRUDentityDTO entity = facade.getCRUDentityById(id);
        if (entity != null) {
            return Response.ok().entity(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("update/{id}")
    public Response updateCRUDentity(@PathParam("id") int id, CRUDentityDTO dto) {
        CRUDentityDTO updatedEntity = facade.updateCRUDentity(id, dto.getName(), dto.getDescription());
        if (updatedEntity != null) {
            return Response.ok().entity(updatedEntity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteCRUDentity(@PathParam("id") int id) {
        boolean deleted = facade.deleteCRUDentity(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @POST
//    @Path("create")
//    public Response createCRUDentity(CRUDentityDTO dto) {
//        CRUDentityDTO createdEntity = facade.createCRUDentity(dto.getName(), dto.getDescription());
//        return Response.ok().entity(createdEntity).build();
//    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCRUDentity(String jsonString) throws AuthenticationException, API_Exception {
        String name;
        String description;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            name = json.get("name").getAsString();
            description = json.get("description").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        try {
            facade.createCRUDentity(name,description);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok(new Gson().toJson(name)).build();
    }

}

