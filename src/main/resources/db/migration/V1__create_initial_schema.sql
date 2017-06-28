create sequence hibernate_sequence start 1 increment 1;
create table health_check (id int8 not null, ok int4 not null, primary key (id));
