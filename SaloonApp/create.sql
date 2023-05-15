create table roles (id integer not null auto_increment, role_name varchar(255), primary key (id)) engine=InnoDB;
create table users (id integer not null auto_increment, email varchar(255), encrypted_password varchar(255), name varchar(255), id_role integer not null, primary key (id)) engine=InnoDB;
alter table users add constraint FKt92dgi4412ywy3u8tm9jwdya5 foreign key (id_role) references roles (id);
