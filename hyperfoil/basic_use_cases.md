## Basic User Cases

### Overall Objective

Test overhead of OTel by comparing relative performance of system with and without OTel enabled

### Key tasks

1. Create Responders
2. Create Incidents
3. Get Missions
4. Get Responder Locations

### Scenario 1 - Smoke Test

Static Responders & Static Incidents - created 1-off at start
Fixed number of regular users doing:

1. Querying missions (every 2 secs)

### Scenario 2 - Handling Responder IDs

Static Responders & Static Incidents - created 1-off at start
Fixed number of regular users doing:

1. Querying missions (every 2 secs)
2. Querying responder locations (need responder IDs)

http://localhost:8080/frdemo-backend/responders/{id}

