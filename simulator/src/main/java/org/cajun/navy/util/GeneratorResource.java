package org.cajun.navy.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class GeneratorResource {

    /**
     *         router.get("/g/incidents").handler(this::generateIncidents);
     *         router.get("/g/responders").handler(this::generateResponders);
     *         router.get("/c/incidents").handler(this::clearIncidents);
     *         router.get("/c/responders").handler(this::clearResponders);
     *         router.get("/c/missions").handler(this::clearMissions);
     *
     *         router.get("/g/incidents/lastrun").handler(this::lastRunIncidents);
     *         router.get("/g/responders/lastrun").handler(this::lastRunResponders);
     *         router.get("/*").handler(StaticHandler.create());
     * */


    @GET
    @Path("incidents/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Incident> incidents(@PathParam("number") int numOfIncidents) {
        Disaster d = new Disaster();
        List<Incident> list = d.generateIncidents(numOfIncidents);
        return list;

    }

    @GET
    @Path("responders/{number}/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Responder> responders(@PathParam("number") int numOfResponders) {
        Disaster d = new Disaster();
        return d.generateResponders(numOfResponders);
    }

}