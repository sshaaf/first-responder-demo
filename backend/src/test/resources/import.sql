insert into responder_table (responder_id, responder_name, responder_phone_number, responder_current_gps_lat, responder_current_gps_long, boat_capacity, has_medical_kit, available, person, enrolled, version) VALUES (-1, 'John Doe', '111-222-333', 30.12345, -70.98765, 3, true, true, true, true, 1);
insert into reported_incident ( id, incident_id, latitude, longitude, number_of_people, medical_needed, victim_name, victim_phone, version) VALUES (-1, 'incidentId', 30.12345, -70.98765, 3, true, 'John Doe', '(123) 456-7890)',0);

commit;