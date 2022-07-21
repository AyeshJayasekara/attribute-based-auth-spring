INSERT INTO allion.app_user (id, username, password)
VALUES (1, 'doctor', '$2a$10$tGPnpb0G9K7JaVmUi2uIwukD.pRPVyhdZAKkV42cbJ26031G8.fV.');
INSERT INTO allion.app_user (id, username, password)
VALUES (2, 'patient', '$2a$10$tGPnpb0G9K7JaVmUi2uIwukD.pRPVyhdZAKkV42cbJ26031G8.fV.');

INSERT INTO allion.doctor (id, first_name, last_name, contact, user_id)
VALUES (1, 'Ayesh', 'Doctor', '123', 1);
INSERT INTO allion.doctor (id, first_name, last_name, contact, user_id)
VALUES (3, 'Ranil', 'W', '119', 2);

INSERT INTO allion.patient (id, first_name, last_name, gender, date_of_birth, contact)
VALUES (1, 'Ayesh', 'Patient', 'MALE', '2000', '123');

INSERT INTO allion.consultation (id, diagnosis, blood_pressure, doctor_id, patient_id)
VALUES (1, 'Pain In The A', '200', 1, 1);
INSERT INTO allion.consultation (id, diagnosis, blood_pressure, doctor_id, patient_id)
VALUES (4, 'Crazy', '69', 3, 1);

INSERT INTO allion.policy (id, name, description, target, `condition`)
VALUES (1, 'View Profile', 'View Profile', 'action == ''PROFILE_VIEW''', 'subject == resource.id');
INSERT INTO allion.policy (id, name, description, target, `condition`)
VALUES (2, 'View Consultation', 'View Consultation', 'action == ''CONSULTATION_VIEW''',
        'resource.size() == 0 or resource.get(0).doctorId == subject');
