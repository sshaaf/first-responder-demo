package org.cajun.navy.resource;

import org.cajun.navy.model.mission.Mission;
import org.cajun.navy.model.mission.MissionDao;
import org.cajun.navy.service.MissionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/missions")
@RequestScoped
public class MissionResource {

    @Inject
    MissionService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allMissions(String incident) {
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
    public Response missionByResponderId(@PathParam("id") String missionId) {
        Mission item = service.findByMissionId(missionId);
        if(item == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
            return Response.ok(item).build();
        }
    }


}


