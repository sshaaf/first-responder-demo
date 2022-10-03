package org.cajun.navy.resource;

import org.cajun.navy.map.DisasterInfo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/disaster-service")
@RequestScoped
public class DisasterResource {

    @Inject
    DisasterInfo disasterInfo;

    @GET
    @Path("/shelters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shelters() {
        new DisasterInfo().loadModel();
        return Response.status(Response.Status.ACCEPTED).entity(disasterInfo.getShelters()).build();
    }

    @GET
    @Path("/inclusion-zones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inclusionZones() {
        return Response.status(Response.Status.ACCEPTED).entity(disasterInfo.getInclusionZones()).build();
    }

    @GET
    @Path("/center")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCenter() {
        return Response.status(Response.Status.ACCEPTED).entity(disasterInfo.getCenter()).build();
    }


}
