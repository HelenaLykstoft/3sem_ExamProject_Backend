package rest;

import dtos.CRUDentityDTO;
import facades.CRUDentityFacade;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CRUDentityResource {
    private final CRUDentityFacade facade;
    private static EntityManagerFactory emf;

    @Context
    SecurityContext securityContext;

    public CRUDentityResource() {
        facade = CRUDentityFacade.getCRUDentityFacade(emf);
    }


    @GET
    @Path("all")
    public Response getAllCRUDentities() {
        List<CRUDentityDTO> entities = facade.getAllCRUDentities();
        return Response.ok(entities).build();
    }

    @GET
    @Path("{id}")
    public Response getCRUDentityById(@PathParam("id") int id) {
        CRUDentityDTO entity = facade.getCRUDentityById(id);
        if (entity != null) {
            return Response.ok(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("update/{id}")
    @RolesAllowed("user")
    public Response updateCRUDentity(@PathParam("id") int id, CRUDentityDTO dto) {
        CRUDentityDTO updatedEntity = facade.updateCRUDentity(id, dto.getName(), dto.getDescription());
        if (updatedEntity != null) {
            return Response.ok(updatedEntity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed("user")
    public Response deleteCRUDentity(@PathParam("id") int id) {
        boolean deleted = facade.deleteCRUDentity(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
