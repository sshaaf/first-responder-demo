package org.cajun.navy.service.responder;

import java.math.BigDecimal;
import javax.inject.Inject;

import org.cajun.navy.model.responder.ResponderEntity;
import org.cajun.navy.model.responder.ResponderDao;
import org.cajun.navy.service.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;

public class ResponderDaoIT extends AbstractTestBase {
    @Inject
    private ResponderDao dao;

    /**
     * Just a simple test to make sure the hard-codes named queries are syntactically correct
     */
    @Test
    public void testReadOnlyQueries() {
        dao.allResponders();
        dao.findById(1);
        dao.findByName("Test");
        dao.availableResponders();
        dao.availableResponders(1,1);
        dao.personResponders();
        dao.nonPersonResponders();
        dao.enrolledRespondersCount();
        dao.activeRespondersCount();
    }

    @Test
    public void createResponder() {
        ResponderEntity responder = new ResponderEntity();
        responder.setName("Test Responder");
        responder.setPhoneNumber("111-222-3333");
        responder.setAvailable(true);
        responder.setEnrolled(true);
        responder.setMedicalKit(true);
        responder.setBoatCapacity(3);
        responder.setLatitude(new BigDecimal(30.123435));
        responder.setLongitude(new BigDecimal(-70.98765));

        ResponderEntity created = dao.create(responder);
        Assert.assertNotNull(created.getId());
        Assert.assertEquals(responder.getName(), created.getName());
    }

    @Test
    public void updateResponder() {

    }
}
