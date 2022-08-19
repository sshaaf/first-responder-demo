package org.cajun.navy.service.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaTestContainer implements ConfigSource {
    public static final String KAFKA_SERVER_PROP = "mp.messaging.connector.smallrye-kafka.bootstrap.servers";
    private static KafkaTestContainer INSTANCE;
    private KafkaContainer kafka;
    private Map<String, String> propOverrides = new HashMap<>();

    private KafkaTestContainer() {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka").withTag("7.2.1"));
        kafka.start();
        propOverrides.put(KAFKA_SERVER_PROP, kafka.getBootstrapServers());

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        try (Admin admin = Admin.create(props)) {
            String topicName = "IncidentReportedEvent";
            NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
            CreateTopicsResult result = admin.createTopics(
                    Collections.singleton(newTopic)
            );

            KafkaFuture<Void> future = result.values().get(topicName);
            future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static KafkaTestContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KafkaTestContainer();
        }
        return INSTANCE;
    }

    public KafkaContainer getContainer() {
        return kafka;
    }

    @Override
    public Set<String> getPropertyNames() {
        return propOverrides.keySet();
    }

    @Override
    public String getValue(String s) {
        return propOverrides.get(s);
    }

    @Override
    public String getName() {
        return "KafkaTestContainer";
    }
}
