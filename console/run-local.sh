#!/usr/bin/env bash

export PORT=4200
export INCIDENT="http://localhost:8080/frdemo-backend"
export ALERT="http://localhost:8080/frdemo-backend"
export RESPONDER="http://localhost:8080/frdemo-backend/responder-service"
export MISSION="http://localhost:8080/frdemo-backend"
export PRIORITY="http://localhost:8080/frdemo-backend/incident-priority-service"
export DISASTER="http://localhost:8080/frdemo-backend/disaster-service"
export DISASTER_SIMULATOR="http://localhost:8080/frdemo-backend"
export PROCESS_VIEWER="http://localhost:8080/frdemo-backend"
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
