#!/usr/bin/env bash

export PORT=4200
export INCIDENT="http://localhost:8080/first-responder-demo"
export ALERT="http://localhost:8080/first-responder-demo"
export RESPONDER="http://localhost:8080/first-responder-demo/responder-service"
export MISSION="http://localhost:8080/first-responder-demo"
export PRIORITY="http://localhost:8080/first-responder-demo/incident-priority-service"
export DISASTER="http://localhost:8080/first-responder-demo/disaster-service"
export DISASTER_SIMULATOR="http://localhost:8080/first-responder-demo"
export PROCESS_VIEWER="http://localhost:8080/first-responder-demo"
export AUTH_URL="https://sso-sso.apps.erdemo-df1a.open.redhat.com/auth"
export KEYCLOAK="false"
export POLLING="10000"
export KEYCLOAK="false"
export REALM="emergency-realm"
export CLIENTID="js"
export KAFKA_HOST="localhost:9092"

# https://account.mapbox.com/access-tokens/
export TOKEN=${MAPBOX_TOKEN}

npm run start
