package org.cajun.navy.model.responder;

import java.util.List;

public interface ResponderDao {
    Responder create(Responder responder);

    Responder findById(long id);

    Responder findByName(String name);

    Responder update(Responder responder);

    List<Responder> availableResponders();

    List<Responder> availableResponders(int limit, int offset);

    List<Responder> allResponders();

    List<Responder> allResponders(int limit, int offset);

    List<Responder> personResponders();

    List<Responder> personResponders(int limit, int offset);

    List<Responder> nonPersonResponders();

    void reset();

    void clear();

    void resetPersonsDeleteBots();

    void deleteAll();

    Long enrolledRespondersCount();

    Long activeRespondersCount();
}
