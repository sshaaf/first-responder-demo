package org.cajun.navy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.inject.Inject;

import org.cajun.navy.model.incident.Incident;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class IncidentServiceIT extends AbstractTestBase {

    @Inject
    private IncidentService service;

    @Test
    public void foo() {
        List<Incident> incidents = service.findAll();
        assertThat(incidents).isNotNull();
    }
}
