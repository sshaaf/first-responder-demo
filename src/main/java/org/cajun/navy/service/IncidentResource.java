package org.cajun.navy.service;

import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.incident.IncidentDao;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/incidents")
public class IncidentResource {

    @Inject
    IncidentDao incidentDao;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidents() {
        return Response.status(Response.Status.ACCEPTED).entity(incidentDao.findAll()).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createIncident(@Valid Incident incident) {
        Incident item = incidentDao.create(incident);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @GET
    @Path("/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidentsByStatus(@PathParam("status") String status) {
        return Response.ok(incidentDao.findByStatus(status)).build();
    }

    @GET
    @Path("/incident/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incidentById(@PathParam("id") String incidentId) {
        Incident item = incidentDao.findByIncidentId(incidentId);
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
        return Response.ok(incidentDao.findByName(name)).build();
    }

    @POST
    @Path("/reset")
    public Response reset() {
        return Response.ok(Response.Status.ACCEPTED).build();
    }

}
