package org.cajun.navy.resource;

import org.cajun.navy.exception.NoResponderAvailableException;
import org.cajun.navy.service.IncidentService;
import org.cajun.navy.service.MissionService;
import org.cajun.navy.service.model.Incident;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/incidents")
@RequestScoped
public class IncidentResource {

    private static final Logger logger = Logger.getLogger(IncidentResource.class.getName());

    @Inject
    IncidentService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidents() {
        return Response.status(Response.Status.ACCEPTED).entity(service.findAll()).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createIncident(@Valid Incident incident) {
        try {
            return Response.status(Response.Status.CREATED).entity(service.createIncident(incident)).build();
        } catch (NoResponderAvailableException ae){
            logger.info("No responders were found");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidentsByStatus(@PathParam("status") String status) {
        return Response.ok(service.findByStatus(status)).build();
    }

    @GET
    @Path("/incident/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidentById(@PathParam("id") String incidentId) {
        Incident item = service.findByIncidentId(incidentId);
        if(item == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
            return Response.ok(item).build();
        }
    }

    @GET
    @Path("/byname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidentsByName(@PathParam("name") String name) {
        return Response.ok(service.findByName(name)).build();
    }

    @POST
    @Path("/reset")
    public Response reset() {
        return Response.ok(Response.Status.ACCEPTED).build();
    }


}
