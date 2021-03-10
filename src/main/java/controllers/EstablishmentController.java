package controllers;

import entities.Establishment;
import entities.User;
import filters.customAnnotations.JWTTokenNeeded;
import services.interfaces.IEstablishmentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

@Path("establishments")
public class EstablishmentController {
        @Inject
        private IEstablishmentService establishmentService;

        @JWTTokenNeeded
        @GET
        public Response getAllEstablishments() {
            return Response.ok("Nothing").build();
        }


        @JWTTokenNeeded
        @GET
        @Path("/{id}")
        public Response getEstablishmentById(@PathParam("id") int id) {
            Establishment establishment;
            try {
                establishment = establishmentService.getEstablishmentById(id);
            } catch (ServerErrorException ex) {
                return Response
                        .status(500).entity(ex.getMessage()).build();
            }

            if (establishment == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Establishment does not exist!")
                        .build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(establishment)
                    .build();
        }
    }

