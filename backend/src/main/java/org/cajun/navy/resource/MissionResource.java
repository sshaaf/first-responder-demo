package org.cajun.navy.resource;

import org.cajun.navy.model.mission.MissionEntity;
import org.cajun.navy.service.MissionService;
import org.cajun.navy.service.model.Mission;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/missions")
@RequestScoped
public class MissionResource {

    @Inject
    MissionService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allMissions() {
        return Response.status(Response.Status.ACCEPTED).entity(service.findAll()).build();
    }

    @POST
    @Path("clear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clearAll() {
        return Response.status(Response.Status.ACCEPTED).build();
    }


    @GET
    @Path("responders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response missionByResponderId(@PathParam("id") String responderId) {
        Mission item = service.findByMissionId(responderId);
        if(item == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
            return Response.ok(item).build();
        }
    }


}


