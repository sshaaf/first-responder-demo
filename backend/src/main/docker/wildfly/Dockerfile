FROM quay.io/wildfly/wildfly:26.1.1.Final-2
USER root
RUN mkdir /app

COPY target/first-responder-demo.war /app
COPY target/dependency/postgresql-42.5.0.jar /app
COPY deploy/configure-wildfly.cli /app

RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/app/configure-wildfly.cli && \
    rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/ /opt/jboss/wildfly/standalone/log/* && \
    rm /app/postgresql-*.jar

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
