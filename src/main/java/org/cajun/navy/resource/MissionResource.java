package org.cajun.navy.resource;

import org.cajun.navy.model.mission.Mission;
import org.cajun.navy.model.mission.MissionDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/missions")
public class MissionResource {

    @Inject
    MissionDao missionDao;

    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public Response allMissions(String incident) {
        return Response.status(Response.Status.ACCEPTED).entity(missionDao.findAll()).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("clear")
    public Response clearAll() {
        return Response.status(Response.Status.ACCEPTED).build();
    }


    @GET
    @Path("responders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response missionByResponderId(@PathParam("id") String missionId) {
        Mission item = missionDao.findByMissionId(missionId);
        if(item == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else{
            return Response.ok(item).build();
        }
    }


}


