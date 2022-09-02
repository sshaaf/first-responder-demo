

#topic-incident-event

### IncidentReportedEvent
    HEADER: 91885f44-ee21-4663-8897-4138ebd8c16e, ce_source: emergency-response/incident-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:04.667994Z, ce_type: IncidentReportedEvent, content-type: application/json
    {
        "id": "a0137f42-b763-41b0-a96a-670149c42dda",
        "lat": 34.22796,
        "lon": -77.80576,
        "medicalNeeded": false,
        "numberOfPeople": 2,
        "victimName": "Nova Thomas",
        "victimPhoneNumber": "(651) 555-2777",
        "status": "REPORTED",
        "timestamp": 1661952424664
    }

### ASSIGNED
    HEADER: 81619974-9c61-4bf9-8b02-9e8af08a4132, ce_source: emergency-response/incident-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:29:46.873347Z, ce_type: IncidentUpdatedEvent, content-type: application/json
    {
        "id": "7ed05cd2-7130-4849-9542-adc30e58e8c3",
        "lat": 34.22081,
        "lon": -77.95107,
        "medicalNeeded": false,
        "numberOfPeople": 5,
        "victimName": "Nora Reed",
        "victimPhoneNumber": "(828) 555-5048",
        "status": "ASSIGNED",
        "timestamp": 1661952584759
    }


### RESCUED
    HEADER: 7f4d1884-e3eb-4be5-92ce-5015e07e228e, ce_source: emergency-response/incident-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:32:06.879809Z, ce_type: IncidentUpdatedEvent, content-type: application/json
    {
        "id": "50c8555d-7df0-46a4-b929-7424bc032812",
        "lat": 34.23063,
        "lon": -77.93465,
        "medicalNeeded": true,
        "numberOfPeople": 2,
        "victimName": "Luke Morgan",
        "victimPhoneNumber": "(252) 555-6815",
        "status": "RESCUED",
        "timestamp": 1661952664765
    }


### PICKEDUP
    HEADER: 932a17ee-17d5-4296-bce6-c5a8ae3f90c1, ce_source: emergency-response/incident-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:29:01.87793Z, ce_type: IncidentUpdatedEvent, content-type: application/json
    {
        "id": "099e1694-456c-4c3b-a1e9-c2e7589392f0",
        "lat": 34.24832,
        "lon": -77.88938,
        "medicalNeeded": true,
        "numberOfPeople": 4,
        "victimName": "Savannah Perez",
        "victimPhoneNumber": "(336) 555-7733",
        "status": "PICKEDUP",
        "timestamp": 1661952519752
    }


#topic-incident-command

### ASSIGNED 
    HEADER: be6c-446e-be9d-daddeb79ac59, ce_incidentid: , ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:31.227939Z, ce_type: UpdateIncidentCommand, content-type: application/json, id: b6b0b52d-be6c-446e-be9d-daddeb79ac59
    "{\"incident\":{\"id\":\"a6246742-6153-47cb-9fbf-d9fcc8bf5981\",\"status\":\"ASSIGNED\"}}"

### PICKEDUP
    HEADER: 85ab-4e06-ba6f-95f3cb414a76, ce_incidentid: , ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:51.239863Z, ce_type: UpdateIncidentCommand, content-type: application/json, id: afb6a0f4-85ab-4e06-ba6f-95f3cb414a76
    "{\"incident\":{\"id\":\"a6246742-6153-47cb-9fbf-d9fcc8bf5981\",\"status\":\"PICKEDUP\"}}"

### RESCUED
    HEADER: 1c8b-4a3a-b111-956dedcb0333, ce_incidentid: , ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:28:21.239565Z, ce_type: UpdateIncidentCommand, content-type: application/json, id: fb156ea6-1c8b-4a3a-b111-956dedcb0333
    "{\"incident\":{\"id\":\"a6246742-6153-47cb-9fbf-d9fcc8bf5981\",\"status\":\"RESCUED\"}}"


#topic-responder-command
### SetResponderUnavailableCommand
    HEADER: ce_incidentid: c3dd80ab-26df-4fdf-a75a-ea1eef8bab3d, ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:09.75696Z, ce_type: SetResponderUnavailableCommand, content-type: application/json, id: 5f6c1a37-29cb-4dd7-9760-2f402051fad9
    "{\"responder\":{\"id\":\"8\",\"name\":null,\"phoneNumber\":null,\"latitude\":null,\"longitude\":null,\"boatCapacity\":null,\"medicalKit\":null,\"available\":false}}"

### UpdateResponderCommand
    HEADER: ce_incidentid: , ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:36:11.11749Z, ce_type:UpdateResponderCommand, content-type: application/json, id: 49d5ee99-bcc5-4661-a159-55b9e1b3595f
    "{\"responder\":{\"id\":\"64\",\"name\":null,\"phoneNumber\":null,\"latitude\":34.1706,\"longitude\":-77.949,\"boatCapacity\":null,\"medicalKit\":null,\"available\":true}}"



#topic-responder-event
### ResponderSetUnavailableEvent
    HEADER: ce_incidentid: e1783433-a5be-4acf-a1a5-4070e74867bd, ce_source: emergency-response/responder-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:28:03.373012Z, ce_type: ResponderSetUnavailableEvent, content-type: application/json
    {
        "status": "success",
        "statusMessage": "Responder updated",
        "responder": {
        "id": "134",
        "name": "Aria Price",
        "phoneNumber": "(252) 555-7752",
        "latitude": 34.21392,
        "longitude": -77.80267,
        "boatCapacity": 4,
        "medicalKit": false,
        "available": false,
        "person": false,
        "enrolled": true
        }
    }

#ResponderUpdatedEvent
    HEADER: ce_source: emergency-response/responder-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:28:40.871191Z, ce_type: ResponderUpdatedEvent, content-type: application/json
    {
        "responder": {
        "id": "34",
        "name": "Ezekiel Rogers",
        "phoneNumber": "(828) 555-5052",
        "latitude": 34.1706,
        "longitude": -77.949,
        "boatCapacity": 10,
        "medicalKit": false,
        "available": true,
        "person": false,
        "enrolled": true
        }
    }


#topic-responder-location-update

### ResponderLocationUpdatedEvent
    HEADER: ce_source: emergency-response/responder-simulator, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:16.068652Z, ce_type: ResponderLocationUpdatedEvent, content-type: application/json
    {
        "responderId": "5",
        "missionId": "4ee9477c-b363-4b51-87ad-7c47aa1cca1a",
        "incidentId": "a0137f42-b763-41b0-a96a-670149c42dda",
        "status": "MOVING",
        "lat": 34.2317,
        "lon": -77.8381,
        "human": false,
        "continue": true
    }



#topic-mission-command

    HEADER: 752b-4e59-a230-f52da31129bd, ce_incidentid: , ce_source: emergency-response/process-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:35.386639Z, ce_type: CreateMissionCommand, content-type: application/json, id: c4d84ba1-752b-4e59-a230-f52da31129bd
    "{\"incidentId\":\"1e8a4df3-f0dc-49ca-9b7f-03c8390c7df5\",\"responderId\":\"23\",\"responderStartLat\":34.23880,\"responderStartLong\":-77.81675,\"incidentLat\":34.23842,\"incidentLong\":-77.77396,\"destinationLat\":34.2461,\"destinationLong\":-77.9519,\"processId\":\"36\"}"


#topic-mission-event
### MissionStartedEvent
    HEADER: ddf78ca3-ffab-4fb8-8fbb-52fa05695b65, ce_source: emergency-response/mission-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:02.641514Z, ce_type: MissionStartedEvent, content-type: application/json
    {
    "id": "7ea3c3e7-ff72-4822-8d2b-47403727c9d6",
    "incidentId": "89eb15b4-4375-4ad3-88ce-ff49d17c6445",
    "responderId": "3",
    "responderStartLat": 34.23968,
    "responderStartLong": -77.92077,
    "incidentLat": 34.12112,
    "incidentLong": -77.91197,
    "destinationLat": 34.1706,
    "destinationLong": -77.949,
    "responderLocationHistory": [],
    "status": "CREATED",
    "steps": [
    {
    "lat": 34.2398,
    "lon": -77.9208,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2401,
    "lon": -77.9204,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2404,
    "lon": -77.9189,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.238,
    "lon": -77.9183,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2403,
    "lon": -77.9045,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.176,
    "lon": -77.9248,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.176,
    "lon": -77.9254,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1413,
    "lon": -77.8949,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1127,
    "lon": -77.9006,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.114,
    "lon": -77.9052,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1166,
    "lon": -77.9051,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1184,
    "lon": -77.907,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.121,
    "lon": -77.9128,
    "wayPoint": true,
    "destination": false
    },
    {
    "lat": 34.121,
    "lon": -77.9128,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1242,
    "lon": -77.9124,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1238,
    "lon": -77.9151,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1399,
    "lon": -77.9216,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1701,
    "lon": -77.9482,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1698,
    "lon": -77.9488,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1705,
    "lon": -77.949,
    "wayPoint": false,
    "destination": true
    }
    ]
    }

### MissionPickedUpEvent
    HEADER: 82144a1b-6ef4-4864-b061-7a97d7168b38, ce_source: emergency-response/mission-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:27:29.6238Z, ce_type: MissionPickedUpEvent, content-type: application/json
    {
    "id": "35a1e2c1-56e5-4a48-880c-3383ed0ba8be",
    "incidentId": "5436b2e1-94fc-4a8a-9ad6-f10fd5d62a0f",
    "responderId": "17",
    "responderStartLat": 34.23236,
    "responderStartLong": -77.87441,
    "incidentLat": 34.22905,
    "incidentLong": -77.86559,
    "destinationLat": 34.2461,
    "destinationLong": -77.9519,
    "responderLocationHistory": [
    {
    "lat": 34.23,
    "lon": -77.8724,
    "timestamp": 1661952439649
    },
    {
    "lat": 34.229,
    "lon": -77.8663,
    "timestamp": 1661952449623
    }
    ],
    "status": "UPDATED",
    "steps": [
    {
    "lat": 34.2322,
    "lon": -77.8745,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2328,
    "lon": -77.8747,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.233,
    "lon": -77.8752,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2346,
    "lon": -77.874,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2307,
    "lon": -77.877,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.229,
    "lon": -77.8663,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.229,
    "lon": -77.8663,
    "wayPoint": true,
    "destination": false
    },
    {
    "lat": 34.229,
    "lon": -77.8663,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2267,
    "lon": -77.8666,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2264,
    "lon": -77.8667,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.229,
    "lon": -77.8663,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2288,
    "lon": -77.8705,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.23,
    "lon": -77.8711,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2316,
    "lon": -77.8762,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2551,
    "lon": -77.8709,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2484,
    "lon": -77.9477,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2467,
    "lon": -77.9488,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2464,
    "lon": -77.9513,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2464,
    "lon": -77.9516,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.2462,
    "lon": -77.9521,
    "wayPoint": false,
    "destination": true
    }
    ]
    }

### MissionCompletedEvent
    HEADER: ce020662-6a96-4d7e-8a29-a53244413d94, ce_source: emergency-response/mission-service, ce_specversion: 1.0, ce_time: 2022-08-31T13:28:52.114695Z, ce_type: MissionCompletedEvent, content-type: application/json
    {
    "id": "03d489ed-3333-474c-87cc-7e3e6daaf7e7",
    "incidentId": "9833f8d6-2eb6-4836-a626-f535de57145f",
    "responderId": "91",
    "responderStartLat": 34.16008,
    "responderStartLong": -77.9107,
    "incidentLat": 34.14061,
    "incidentLong": -77.90123,
    "destinationLat": 34.1706,
    "destinationLong": -77.949,
    "responderLocationHistory": [
    {
    "lat": 34.1526,
    "lon": -77.9059,
    "timestamp": 1661952452112
    },
    {
    "lat": 34.1428,
    "lon": -77.8963,
    "timestamp": 1661952462113
    },
    {
    "lat": 34.1408,
    "lon": -77.9018,
    "timestamp": 1661952472113
    },
    {
    "lat": 34.1396,
    "lon": -77.9097,
    "timestamp": 1661952482113
    },
    {
    "lat": 34.1401,
    "lon": -77.9216,
    "timestamp": 1661952492113
    },
    {
    "lat": 34.1495,
    "lon": -77.9299,
    "timestamp": 1661952502154
    },
    {
    "lat": 34.1588,
    "lon": -77.9382,
    "timestamp": 1661952512113
    },
    {
    "lat": 34.1701,
    "lon": -77.9482,
    "timestamp": 1661952522114
    },
    {
    "lat": 34.1705,
    "lon": -77.949,
    "timestamp": 1661952532114
    }
    ],
    "status": "COMPLETED",
    "steps": [
    {
    "lat": 34.16,
    "lon": -77.9101,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1599,
    "lon": -77.9101,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1591,
    "lon": -77.9076,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1584,
    "lon": -77.9069,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1564,
    "lon": -77.9096,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1428,
    "lon": -77.8963,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1393,
    "lon": -77.9033,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1408,
    "lon": -77.9018,
    "wayPoint": true,
    "destination": false
    },
    {
    "lat": 34.1408,
    "lon": -77.9018,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1414,
    "lon": -77.9015,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1408,
    "lon": -77.9044,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1397,
    "lon": -77.9041,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1393,
    "lon": -77.9033,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1396,
    "lon": -77.9097,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1407,
    "lon": -77.9126,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1435,
    "lon": -77.9127,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1406,
    "lon": -77.9138,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1401,
    "lon": -77.9216,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1701,
    "lon": -77.9482,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1698,
    "lon": -77.9488,
    "wayPoint": false,
    "destination": false
    },
    {
    "lat": 34.1705,
    "lon": -77.949,
    "wayPoint": false,
    "destination": true
    }
    ]
    }