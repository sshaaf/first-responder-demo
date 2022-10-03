package org.cajun.navy.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/incident-priority-service")
@RequestScoped
public class IncidentPriorityResource {

    @GET
    @Path("/priority-zones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shelters() {
        return Response.status(Response.Status.ACCEPTED).entity(new ArrayList<String>()).build();
    }
}
