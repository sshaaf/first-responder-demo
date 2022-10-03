package org.cajun.navy.model.responder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class ResponderDaoImpl implements ResponderDao {

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public ResponderEntity create(ResponderEntity responder) {
        entityManager.persist(responder);
        return responder;
    }

    @Override
    @Transactional
    public ResponderEntity findById(long id) {
        return entityManager.find(ResponderEntity.class, id, LockModeType.OPTIMISTIC);
    }

    @Override
    public ResponderEntity findByName(String name) {
        List<ResponderEntity> results = entityManager.createNamedQuery("Responder.findByName", ResponderEntity.class)
                .setParameter("name", name).getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            throw new NonUniqueResultException("Found several Responders with name '" + name + "'");
        }
    }

    @Override
    @Transactional
    public ResponderEntity update(ResponderEntity responder) {

        ResponderEntity toUpdate = findById(responder.getId());
        if (toUpdate == null) {
            return null;
        }
        else{
            entityManager.persist(responder);
            entityManager.flush();
            return toUpdate;
        }
    }

    @Override
    public List<ResponderEntity> availableResponders() {
        return entityManager.createNamedQuery("Responder.availableResponders", ResponderEntity.class)
                .getResultList();
    }

    @Override
    public List<ResponderEntity> availableResponders(int limit, int offset) {
        TypedQuery<ResponderEntity> q = entityManager.createNamedQuery("Responder.availableRespondersOrderedByPerson", ResponderEntity.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<ResponderEntity> allResponders() {
        return entityManager.createNamedQuery("Responder.allResponders", ResponderEntity.class).getResultList();
    }

    @Override
    public List<ResponderEntity> allResponders(int limit, int offset) {
        TypedQuery<ResponderEntity> q = entityManager.createNamedQuery("Responder.allResponders", ResponderEntity.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<ResponderEntity> personResponders() {
        return entityManager.createNamedQuery("Responder.persons", ResponderEntity.class).getResultList();
    }

    @Override
    public List<ResponderEntity> personResponders(int limit, int offset) {
        TypedQuery<ResponderEntity> q = entityManager.createNamedQuery("Responder.persons", ResponderEntity.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<ResponderEntity> nonPersonResponders() {
        return entityManager.createNamedQuery("Responder.nonPersons", ResponderEntity.class).getResultList();
    }

    @Override
    @Transactional
    public void reset() {
        entityManager.createNamedQuery("Responder.reset").executeUpdate();
        entityManager.createNamedQuery("Responder.resetPerson").executeUpdate();
        entityManager.flush();
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createNamedQuery("Responder.clearNonPersons").executeUpdate();
        entityManager.createNamedQuery("Responder.resetPerson").executeUpdate();
        entityManager.flush();
    }

    @Override
    @Transactional
    public void resetPersonsDeleteBots() {
        entityManager.createNamedQuery("Responder.deleteNonPersons").executeUpdate();
        entityManager.createNamedQuery("Responder.resetPerson").executeUpdate();
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createNamedQuery("Responder.deleteAll").executeUpdate();
        entityManager.flush();
    }

    @Override
    public Long enrolledRespondersCount() {
        return (Long) entityManager.createNamedQuery("Responder.countEnrolled").getSingleResult();
    }

    @Override
    public Long activeRespondersCount() {
        return (Long) entityManager.createNamedQuery("Responder.countActive").getSingleResult();
    }

}
