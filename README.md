# First Responder Demo

The First Responder Demo application is meant to act as a test application for gauging the impact of OpenTelemetry tracing on a 
typical WildFly/EAP workload. The application is still in early stages, so more details on the performance tests -- the results, how
to run them locally, etc -- will be added soon.



## Emergency Response - Web Console

The web interface for the emergency response demo, built using Angular 7 and node v12.22.12

### Overview

The web console has three main tabs.

### Dashboard

The dashboard tab shows an overview of the status of all incidents and
responders along with a map view of all ongoing missions. These values update
in real-time based on events in Kafka.

![dashboard](console/docs/screenshots/dashboard.png)

### Incidents

The incidents tab is a table of all incidents that have been created and their
information. It has a search bar that will perform searches based on all
information about the incident.

![incidents](console/docs/screenshots/incident.png)


### Mission

The mission tab allows the user to make themselves an available responder in
the emergency response system. To do this, select a location on the provided
map to set your current location and select `Available`. The user will then be
assigned a mission. Once the user reaches the waypoint of the victim, select
the `Picked up` button to drop off the victim. Once a mission has been
completed the user will be set to an unavailable state again.
[NOTE: Backend for this tab is not yet implemented]

![mission](console/docs/screenshots/mission.png)



## Running the demo with docker-compose

``` 
# your token from mapbox.com 
export MAPBOX_TOKEN="...."

# Start the setup with docker compose
docker-compose -f docker-compose.yml up
``` 

If all is in order you should be able to see the dashboard on the following url: `https://localhost:4200/home/dashboard`


## Manual Setup on a host machine
With the manual setup there is quiet alot of things that need to be considered in the configuration. The recommended way is to use docker-compose. However we have documented all that is required to run this locally as well. 
### Setting up WildFly
To download and install WildFly, copy and paste the following into your shell:
``` 
wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar
wget https://github.com/wildfly/wildfly/releases/download/26.1.1.Final/wildfly-26.1.1.Final.zip
unzip -q wildfly-26.1.1.Final.zip
wildfly-26.1.1.Final/bin/standalone.sh
wildfly-26.1.1.Final/bin/jboss-cli.sh -c << EOF
batch
/extension=org.wildfly.extension.microprofile.reactive-messaging-smallrye:add
/extension=org.wildfly.extension.microprofile.reactive-streams-operators-smallrye:add
/subsystem=microprofile-reactive-streams-operators-smallrye:add
/subsystem=microprofile-reactive-messaging-smallrye:add
module add --name=org.postgres --resources=postgresql-42.2.5.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/FRDemoDS --name=FRDemoDS --connection-url=jdbc:postgresql://localhost/frdemo --driver-name=postgres --user-name=frdemo --password=frdemo
run-batch
reload
EOF
```

### Starting Kafka
To download and install Kafka, perform the steps below. For more information on Kafka, see the
[Apache Kafka Quickstart](https://kafka.apache.org/quickstart).
```
$ wget https://dlcdn.apache.org/kafka/3.2.1/kafka_2.13-3.2.1.tgz
$ tar xf kafka_2.13-3.2.1.tgz
$ cd kafka_2.13-3.2.1.tgz
(In separate terminal windows/tabs)
$ bin/zookeeper-server-start.sh config/zookeeper.properties
$ bin/kafka-server-start.sh config/server.properties
(This next is optional)
$ bin/kafka-console-consumer.sh --topic IncidentReportedEvent --from-beginning --bootstrap-server localhost:9092
```

### Deploying the application

```
$ mvn clean install wildfly:deploy
```

### Creating Responders

To create a responder, POST some JSON like this:

{
"available":true,
"boatCapacity":3,
"enrolled":false,
"latitude":34.12345,
"longitude":-77.98765,
"medicalKit":true,
"name":"John Doe",
"person":true,
"phoneNumber":"111-222-333"
}

to the endpoint https://localhost:4200/responder-service/responder

E.g.
 
curl -i -H 'Content-Type: application/json' \ 
        -H 'Accept: application/json' \ 
        -k -d @responder.json https://localhost:4200/responder-service/responder

if the content is placed in responder.json

This curl command can also be used as a smoke test to check that the system is starting correctly before running the Hyperfoil tests.

### Running the simulator

The simulator is a batch update tool that randomly generates data into the system if its REST endpoint is hit. 

$ export SIM_SEND=true
$ export BACKEND_URL="http://localhost:8080"
$ cd simulator/
$ mvn quarkus:dev

#### Creating Incidents

To generate an incident:
curl http://localhost:8888/incidents/1

Note that nothing will show on the front end until there's an incident


### Outgoing Kafka Messages
All the Outgoing message formats are documented here backend/etc/OutgoingMessages.md


## Running Hyperfoil tests

/bin/cli.sh

[] start-local
[] upload <PATH>
[] run <SCRIPT>

The scripts are templated - see https://hyperfoil.io/userguide/templates.html for details.
The key variable is SERVER, which default to localhost - and can be changed to allow for test scenarios
where load generation is separate from the SUT.