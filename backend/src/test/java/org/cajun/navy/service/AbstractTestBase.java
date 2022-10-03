/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cajun.navy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AbstractTestBase {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment
    public static Archive getDeployment() throws IOException {
        PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml")
                .importDependencies(ScopeType.RUNTIME);
        String config = Files.readString(Path.of("src/main/resources/META-INF/microprofile-config.properties"));
        config = config.replaceAll("localhost:9092", "" + System.getProperty("kafka.server"));

        Archive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/beans.xml"))
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsResource(new StringAsset(config), "META-INF/microprofile-config.properties")
                .addAsLibraries(pom.importRuntimeAndTestDependencies().resolve().withTransitivity().asList(JavaArchive.class))
                .addAsLibraries(pom.resolve("com.mapbox.mapboxsdk:mapbox-sdk-services").withTransitivity().asList(JavaArchive.class))
                .addAsLibraries(pom.resolve("io.cloudevents:cloudevents-kafka").withTransitivity().asList(JavaArchive.class))
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/beans.xml"))
                .addAsResource("import.sql", "META-INF/import.sql")
                .addAsResource("wilmington.json")
                .addPackages(true, "org.cajun.navy");


        return webArchive;
    }
}
