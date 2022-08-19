package org.cajun.navy.service.responder;

import java.math.BigDecimal;
import javax.inject.Inject;

import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.model.responder.ResponderDao;
import org.cajun.navy.service.AbstractTestBase;
import org.junit.Assert;
import org.junit.Test;

public class ResponderDaoIT extends AbstractTestBase {
    @Inject
    private ResponderDao dao;

    @Test
    public void createResponder() {
        Responder responder = new Responder();
        responder.setName("Test Responder");
        responder.setPhoneNumber("111-222-3333");
        responder.setAvailable(true);
        responder.setEnrolled(true);
        responder.setMedicalKit(true);
        responder.setBoatCapacity(3);
        responder.setLatitude(new BigDecimal(30.123435));
        responder.setLongitude(new BigDecimal(-70.98765));

        Responder created = dao.create(responder);
        Assert.assertNotNull(created.getId());
        Assert.assertEquals(responder.getName(), created.getName());
    }


}
