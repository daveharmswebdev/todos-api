drop table if exists todo cascade;

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

insert into todo (title, description, owner_id, todo_status, due_date)
values ("test title", "test description", 0, 0, '2022-01-01 08:00:00');