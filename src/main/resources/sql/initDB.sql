--liquibase formatted sql
--changeset tigratius:1

create table passenger
(
    id_passenger int auto_increment,
    name varchar(45) not null,
    surname varchar(45) not null,
    birthdate date,
    constraint passenger_pk
        primary key (id_passenger)
);

--changeset TestUsers_sql:2

create table plane
  (
      id_plane int auto_increment,
      name_plane varchar(45) not null,
      constraint plane_pk
          primary key (id_plane)
  );

--changeset TestUsers_sql:3

create table routes
(
    id_routes int auto_increment,
    departure varchar(45) not null,
    arrival varchar(45) not null,
    constraint plane_pk
        primary key (id_routes)
);

--changeset TestUsers_sql:4

create table ticket
(
    id_ticket int auto_increment,
    date datetime not null,
    type_seat varchar(45) not null,
    price int not null,
    id_plane int null,
    id_passenger int not null,
    id_routes int not null,
    constraint ticket_pk
        primary key (id_ticket),
    constraint ticket_passenger_id_passanger_fk
        foreign key (id_passenger) references passenger (id_passenger)
            on update cascade on delete cascade,
    constraint ticket_plane_id_plane_fk
        foreign key (id_plane) references plane (id_plane)
            on update cascade on delete cascade,
    foreign key (id_routes) references routes (id_routes)
        on update cascade on delete cascade
);