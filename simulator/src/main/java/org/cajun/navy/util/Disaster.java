package org.cajun.navy.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class Disaster {

    private static GenerateFullNames fullNames = null;
    private static GenerateRandomPoints points = null;

    @ConfigProperty(name = "sendRestToBackend")
    boolean send;

    @RestClient
    BackendService backendService;
    private String fNameFile= "/FNames.txt";
    private String lNameFile= "/LNames.txt";


    public Disaster(){
        fullNames = new GenerateFullNames(fNameFile,lNameFile);
        points = new GenerateRandomPoints();
    }


    public Incident generateSingleIncident(){
        Point2D.Double point = points.getInternalPoint();
        Incident incident = new Incident.Builder().
        victimName(fullNames.getNextFullName())
                .lat(point.getY())
                .lon(point.getX())
                .victimPhoneNumber(GeneratePhoneNumbers.getNextPhoneNumber())
                .numberOfPeople(biasedRandom(1, 10, 1.3))
                .medicalNeeded(new Random().nextBoolean())
                .build();

        backendService.createIncident(incident);

        return incident;

    }

    public List<Responder> generateResponders(int number) {
        List<Responder> responders = new ArrayList<>(number);
        for(int i=0; i<number; i++)
            responders.add(generateResponder());
        return responders;
    }

    public Responder generateResponder() {
        Point2D.Double point = points.getInternalPoint();
        return new Responder.Builder()
                .name(fullNames.getNextFullName())
                .phoneNumber(GeneratePhoneNumbers.getNextPhoneNumber())
                .boatCapacity(biasedRandom(1, 12, 0.5))
                .medicalKit(new Random().nextBoolean())
                .latitude(point.getY())
                .longitude(point.getX())
                .enrolled(true)
                .person(false)
                .available(true)
                .build();
    }


    public List<Incident> generateIncidents(int number, boolean send){
        List<Incident> incidents = new ArrayList<Incident>(number);
        for(int i=0; i<number; i++)
            incidents.add(generateSingleIncident());
        return incidents;
    }

    protected int biasedRandom(int min, int max, double bias) {
        double d = ThreadLocalRandom.current().nextDouble();
        double biased = Math.pow(d, bias);
        return (int) Math.round(min + (max-min)*biased);
    }
 }
