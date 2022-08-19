package org.cajun.navy.service.incident;

import java.util.List;
import javax.inject.Inject;

import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.service.AbstractTestBase;
import org.cajun.navy.service.IncidentService;
import org.junit.Assert;
import org.junit.Test;

public class IncidentServiceIT extends AbstractTestBase {
    @Inject
    private IncidentService service;

    @Test
    public void foo() {
        List<Incident> incidents = service.findAll();
        Assert.assertNotNull(incidents);
    }
}
