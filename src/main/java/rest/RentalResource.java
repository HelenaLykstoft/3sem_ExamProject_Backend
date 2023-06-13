package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.RentalDTO;
import entities.Rental;
import errorhandling.API_Exception;
import facades.RentalFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

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
public class RentalResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final RentalFacade facade = RentalFacade.getRentalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo(){
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    public String getAllRentals() {
        return GSON.toJson(facade.getAllRentals());
    }

    @GET
    @Path("{id}")
    public Response getRentalById(@PathParam("id") int id) {
        RentalDTO entity = facade.getRentalById(id);
        if (entity != null) {
            return Response.ok().entity(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("update/{id}")
    public Response updateRental(@PathParam("id") int id, RentalDTO dto) {
        RentalDTO updatedEntity = facade.updateRental(id, dto.getStartDate(), dto.getEndDate(), dto.getPriceAnnual(), dto.getDeposit(), dto.getContactPerson());
        if (updatedEntity != null) {
            return Response.ok().entity(updatedEntity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteRental(@PathParam("id") int id) {
        facade.deleteRental(id);
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
    public Response createRental(String jsonString) throws AuthenticationException, API_Exception {
        String startDate;
        String endDate;
        int priceAnnual;
        int deposit;
        String contactPerson;
        RentalDTO rentalDTO = null;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            startDate = json.get("startDate").getAsString();
            endDate = json.get("endDate").getAsString();
            priceAnnual = json.get("priceAnnual").getAsInt();
            deposit = json.get("deposit").getAsInt();
            contactPerson = json.get("contactPerson").getAsString();
            rentalDTO = new RentalDTO(startDate, endDate, priceAnnual, deposit, contactPerson);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        try {
            RentalDTO rentalDTO1 = facade.create(rentalDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.ok(new Gson().toJson(startDate)).build();
    }

}

