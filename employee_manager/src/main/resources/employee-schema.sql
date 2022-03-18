drop table if exists employee;
create table employee (
    id bigint not null auto_increment,
    age integer not null,
    email varchar(255) not null,
    first_name varchar(255) not null,
    gender varchar(255) not null,
    second_name varchar(255) not null
    );