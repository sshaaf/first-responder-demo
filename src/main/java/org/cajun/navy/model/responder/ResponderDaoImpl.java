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
    public Responder create(Responder responder) {
        entityManager.persist(responder);
        return responder;
    }

    @Override
    public Responder findById(long id) {
        return entityManager.find(Responder.class, id, LockModeType.OPTIMISTIC);
    }

    @Override
    public Responder findByName(String name) {
        List<Responder> results = entityManager.createNamedQuery("Responder.findByName", Responder.class)
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
    public Responder update(Responder responder) {

        Responder toUpdate = findById(responder.getId());
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
    public List<Responder> availableResponders() {
        return entityManager.createNamedQuery("Responder.availableResponders", Responder.class)
                .getResultList();
    }

    @Override
    public List<Responder> availableResponders(int limit, int offset) {
        TypedQuery<Responder> q = entityManager.createNamedQuery("Responder.availableRespondersOrderedByPerson", Responder.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<Responder> allResponders() {
        return entityManager.createNamedQuery("Responder.allResponders", Responder.class).getResultList();
    }

    @Override
    public List<Responder> allResponders(int limit, int offset) {
        TypedQuery<Responder> q = entityManager.createNamedQuery("Responder.allResponders", Responder.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<Responder> personResponders() {
        return entityManager.createNamedQuery("Responder.persons", Responder.class).getResultList();
    }

    @Override
    public List<Responder> personResponders(int limit, int offset) {
        TypedQuery<Responder> q = entityManager.createNamedQuery("Responder.persons", Responder.class);
        if (limit > 0 && offset >= 0) {
            q.setMaxResults(limit);
            q.setFirstResult(offset);
        }
        return q.getResultList();
    }

    @Override
    public List<Responder> nonPersonResponders() {
        return entityManager.createNamedQuery("Responder.nonPersons", Responder.class).getResultList();
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
