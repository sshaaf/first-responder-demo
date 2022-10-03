package org.cajun.navy.service;

import org.cajun.navy.model.responder.ResponderEntity;
import org.cajun.navy.model.responder.ResponderDao;
import org.cajun.navy.model.responder.ResponderStats;
import org.cajun.navy.service.model.Responder;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ResponderService {

    @Inject
    ResponderDao responderDao;

    @Inject
    @Channel("responder-command")
    Emitter<Responder> responderCommandEmitter;

    @Inject
    @Channel("responder-event")
    Emitter<Responder> responderEventEmitter;


    @Transactional
    public Responder create(Responder responder){

        ResponderEntity entity = new ResponderEntity.Builder(responder.getId())
                .name(responder.getName())
                .phoneNumber(responder.getPhoneNumber())
                .latitude(responder.getLatitude())
                .longitude(responder.getLongitude())
                .boatCapacity(responder.getBoatCapacity())
                .medicalKit(responder.isMedicalKit())
                .available(responder.isAvailable())
                .enrolled(responder.isEnrolled())
                .person(responder.isPerson())
                .build();

        responderDao.create(entity);
        return fromEntity(entity);
    }


    @Transactional
    public void createResponders(List<Responder> responders) {
        List<Responder> createdResponders = responders.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public Responder findById(long id){
        return fromEntity(responderDao.findById(id));
    }

    public Responder findByName(String name){
        return fromEntity(responderDao.findByName(name));
    }

    @Transactional
    public ResponderStats getResponderStats() {
        return new ResponderStats(responderDao.activeRespondersCount(), responderDao.enrolledRespondersCount());
    }

    @Transactional
    public Responder update(Responder responder){
        ResponderEntity item = responderDao.findById(responder.getId());
        item.setAvailable(responder.isAvailable());
        item.setEnrolled(responder.isEnrolled());
        item.setLatitude(responder.getLatitude());
        item.setLongitude(responder.getLongitude());
        item.setMedicalKit(responder.isMedicalKit());
        item.setBoatCapacity(responder.getBoatCapacity());
        item.setName(responder.getName());
        item.setPhoneNumber(responder.getPhoneNumber());
        item.setPerson(responder.isPerson());
        responderDao.update(item);

        Responder updatedResponder = fromEntity(item);
        fireEvent(updatedResponder);
        return updatedResponder;
    }

    public List<Responder> availableResponders(){
        return responderDao.availableResponders().stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Responder> availableResponders(int limit, int offset){
        return responderDao.availableResponders(limit, offset).stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public Responder getFirstAvailableResponder(){
        return fromEntity(responderDao.availableResponders().get(0));
    }

    public List<Responder> allResponders(){
        return responderDao.allResponders().stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Responder> allResponders(int limit, int offset){
        return responderDao.allResponders(limit, offset).stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Responder> personResponders(){
        return responderDao.personResponders().stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Responder> personResponders(int limit, int offset){
        return responderDao.personResponders(limit, offset).stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Responder> nonPersonResponders(){
        return responderDao.nonPersonResponders()
                .stream().map(this::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public void reset(){
        responderDao.reset();
    }

    @Transactional
    public void clear(){
        responderDao.clear();
    }

    @Transactional
    public void resetPersonsDeleteBots(){
        responderDao.resetPersonsDeleteBots();
    }

    @Transactional
    public void deleteAll(){
        responderDao.deleteAll();
    }

    public Long enrolledRespondersCount(){
        return responderDao.enrolledRespondersCount();
    }

    public Long activeRespondersCount(){
        return responderDao.activeRespondersCount();
    }

    public void fireEvent(Responder responder){
        responderCommandEmitter.send(responder);
        responderEventEmitter.send(responder);
    }

    private Responder fromEntity(ResponderEntity entity){
        return new Responder.Builder(entity.getId())
                .available(entity.isAvailable())
                .enrolled((entity.isEnrolled()))
                .boatCapacity(entity.getBoatCapacity())
                .medicalKit(entity.getMedicalKit())
                .person(entity.isPerson())
                .phoneNumber(entity.getPhoneNumber())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .name(entity.getName())
                .build();
    }
}
