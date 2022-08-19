package org.cajun.navy.service;

import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.model.responder.ResponderDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ResponderService {

    @Inject
    ResponderDao responderDao;

    @Transactional
    public Responder create(Responder responder){
        return responderDao.create(responder);
    }

    public Responder findById(long id){
        return responderDao.findById(id);
    }

    public Responder findByName(String name){
        return responderDao.findByName(name);
    }

    @Transactional
    public Responder update(Responder responder){
        return responderDao.update(responder);
    }

    public List<Responder> availableResponders(){
        return responderDao.availableResponders();
    }

    public List<Responder> availableResponders(int limit, int offset){
        return responderDao.availableResponders(limit, offset);
    }

    public Responder getFirstAvailableResponder(){
        return responderDao.availableResponders().get(0);
    }

    public List<Responder> allResponders(){
        return responderDao.allResponders();
    }

    public List<Responder> allResponders(int limit, int offset){
        return responderDao.allResponders(limit, offset);
    }

    public List<Responder> personResponders(){
        return responderDao.personResponders();
    }

    public List<Responder> personResponders(int limit, int offset){
        return responderDao.personResponders(limit, offset);
    }

    public List<Responder> nonPersonResponders(){
        return responderDao.nonPersonResponders();
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

}
