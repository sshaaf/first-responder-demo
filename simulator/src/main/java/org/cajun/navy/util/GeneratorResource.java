package org.cajun.navy.util;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class GeneratorResource {

    @Inject
    Disaster disaster;
    @GET
    @Path("incidents/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incident> incidents(@PathParam("number") int numOfIncidents) {
       return disaster.generateIncidents(numOfIncidents, true);
    }

    @GET
    @Path("responders/{number}/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Responder> responders(@PathParam("number") int numOfResponders) {
        Disaster d = new Disaster();
        return d.generateResponders(numOfResponders);
    }

}