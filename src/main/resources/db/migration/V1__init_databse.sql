drop table if exists todo cascade;
drop table if exists hibernate_sequence;

create table todo
(
    id						bigint not null auto_increment primary key,
    title					varchar(255) not null,
    description		text,
    owner_id			bigint not null,
    todo_status		int not null,
    created			timestamp default current_timestamp,
    modified			timestamp default current_timestamp,
    due_date			timestamp
) engine = InnoDB;

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );