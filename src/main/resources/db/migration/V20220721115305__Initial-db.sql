create table app_user
(
    id       int auto_increment
        primary key,
    username varchar(45)  not null,
    password varchar(100) not null
);

create table doctor
(
    id         int auto_increment
        primary key,
    first_name varchar(45) not null,
    last_name  varchar(45) not null,
    contact    varchar(45) not null,
    user_id    int         null,
    constraint docker_user_fk
        foreign key (user_id) references app_user (id)
);

create table patient
(
    id            int auto_increment
        primary key,
    first_name    varchar(45) not null,
    last_name     varchar(45) not null,
    gender        varchar(45) not null,
    date_of_birth varchar(45) not null,
    contact       varchar(45) null
);

create table consultation
(
    id             int auto_increment
        primary key,
    diagnosis      varchar(45) not null,
    blood_pressure varchar(45) null,
    doctor_id      int         not null,
    patient_id     int         null,
    constraint consultation_doctor_id_fk
        foreign key (doctor_id) references doctor (id),
    constraint consultation_patient_id_fk
        foreign key (patient_id) references patient (id)
);

create table policy
(
    id          int auto_increment
        primary key,
    name        text null,
    description text null,
    target      text null,
    `condition` text null
);

