package org.cajun.navy.service;

import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.model.responder.ResponderDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/responders/")
public class ResponderResource {

        @Inject
        ResponderDao responderDao;

        @GET
        @Path("stats")
        @Produces(MediaType.APPLICATION_JSON)
        public Response stats() {
                return Response.ok(responderDao.activeRespondersCount()).build();
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response responder(@PathParam("id") long id) {
                Responder responder = responderDao.findById(id);
                if (responder == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        return Response.ok(responder).build();
                }
        }

        @GET
        @Path("byname/{name}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response responderByName(@PathParam("name") String name) {
                Responder responder = responderDao.findByName(name);
                if (responder == null) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                } else {
                        return Response.ok(responder).build();
                }
        }


        @GET
        @Path("available")
        @Produces(MediaType.APPLICATION_JSON)
        public Response availableResponders(@QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) {
                List<Responder> responders;
                if (limit.isPresent()) {
                        if (offset.isPresent()) {
                                responders = responderDao.availableResponders(limit.get(), offset.get());
                        } else {
                                responders = responderDao.availableResponders(limit.get(),0);
                        }
                } else {
                        responders = responderDao.availableResponders();
                }
                return Response.ok(responders).build();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response allResponders(@QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) {
                List<Responder> responders;
                if (limit.isPresent()) {
                        if (offset.isPresent()) {
                                responders = responderDao.allResponders(limit.get(), offset.get());
                        } else {
                                responders = responderDao.allResponders(limit.get(),0);
                        }
                } else {
                        responders = responderDao.allResponders();
                }
                return Response.ok(responders).build();
        }

        @GET
        @Path("person")
        @Produces(MediaType.APPLICATION_JSON)
        public Response personResponders(@QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) {
                List<Responder> responders;
                if (limit.isPresent()) {
                        if (offset.isPresent()) {
                                responders = responderDao.personResponders(limit.get(), offset.get());
                        } else {
                                responders = responderDao.personResponders(limit.get(),0);
                        }
                } else {
                        responders = responderDao.personResponders();
                }
                return Response.ok(responders).build();
        }

        @POST
        @Path("/responder")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createResponder(Responder responder) throws URISyntaxException {
                Responder created = responderDao.create(responder);
                return Response.created(new URI("/responder/" + created.getId())).build();
        }

        @PUT
        @Path("/responder")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateResponder(Responder responder) {
                responderDao.update(responder);
                return Response.status(Response.Status.NO_CONTENT).build();
        }

        @POST
        @Path("reset")
        public Response reset() {
                responderDao.reset();
                return Response.ok().build();
        }

        @POST
        @Path("clear")
        public Response clear(@QueryParam("delete") Optional<String> delete) {

                if (delete.orElse("").equals("all")) {
                        responderDao.deleteAll();
                } else responderDao.clear();
                return Response.ok().build();
        }
}
