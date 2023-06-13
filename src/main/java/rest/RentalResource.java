package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.HouseDTO;
import dtos.RentalDTO;
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRentals() {
        List<RentalDTO> rentalDTOList = facade.getAllRentals();
        return Response.ok().entity(rentalDTOList).build();
    }

//    @GET
//    @Path("all/{id}")
//    public String getAllHouseRentals(@PathParam("id") int id) {
//        HouseFacade houseFacade = HouseFacade.getHouseFacade(EMF);
//        return GSON.toJson(houseFacade.getAllHouses(id));
//    }

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
        return ;
    }


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
        String address;
        String city;
        int numberOfRooms;
        HouseDTO houseDTO = null;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            startDate = json.get("startDate").getAsString();
            endDate = json.get("endDate").getAsString();
            priceAnnual = json.get("priceAnnual").getAsInt();
            deposit = json.get("deposit").getAsInt();
            contactPerson = json.get("contactPerson").getAsString();
            address = json.get("address").getAsString();
            city = json.get("city").getAsString();
            numberOfRooms = json.get("numberOfRooms").getAsInt();
            houseDTO = new HouseDTO(address, city, numberOfRooms);
            rentalDTO = new RentalDTO(startDate, endDate, priceAnnual, deposit, contactPerson, houseDTO);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }
        try {
            RentalDTO rentalDTO1 = facade.create(rentalDTO, houseDTO);
            return Response.ok(new Gson().toJson(rentalDTO1)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred during rental creation.")
                    .build();
        }
    }

    @PUT
    @Path("addTenantToRental/{rentalId}/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTenantToRental(@PathParam("rentalId") int rentalId, @PathParam("userName") String userName, String jsonString) throws AuthenticationException, API_Exception {
        RentalDTO rentalDTO = null;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            userName = json.get("userName").getAsString();
            rentalId = json.get("rentalId").getAsInt();
            rentalDTO = facade.addTenantToRental(rentalId, userName);
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }
        return Response.ok(new Gson().toJson(rentalDTO)).build();
    }

}

