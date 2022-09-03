package org.cajun.navy.model.responder;

import java.util.List;

public interface ResponderDao {
    ResponderEntity create(ResponderEntity responder);

    ResponderEntity findById(long id);

    ResponderEntity findByName(String name);

    ResponderEntity update(ResponderEntity responder);

    List<ResponderEntity> availableResponders();

    List<ResponderEntity> availableResponders(int limit, int offset);

    List<ResponderEntity> allResponders();

    List<ResponderEntity> allResponders(int limit, int offset);

    List<ResponderEntity> personResponders();

    List<ResponderEntity> personResponders(int limit, int offset);

    List<ResponderEntity> nonPersonResponders();

    void reset();

    void clear();

    void resetPersonsDeleteBots();

    void deleteAll();

    Long enrolledRespondersCount();

    Long activeRespondersCount();
}
