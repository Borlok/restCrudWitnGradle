create table if not exists users
(
    id int auto_increment primary key
);

create table if not exists events
(
    id      int auto_increment primary key,
    user_id int,
    name    varchar(255),
    foreign key (user_id) references users (id)
);

create table if not exists accounts
(
    id             int auto_increment primary key,
    user_id        int UNIQUE,
    name           varchar(50) DEFAULT 'UNNAMED',
    account_status varchar(20) default 'ACTIVE',
    foreign key (user_id) references users (id)
);

create table if not exists files
(
    id          int auto_increment primary key,
    user_id     int,
    file_status varchar(20) default 'ACTIVE',
    foreign key (user_id) references users (id)
);

create table if not exists access
(
    id         int auto_increment primary key,
    email      varchar(50)  not null,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    password   varchar(255) not null,
    role       varchar(20)  not null default 'USER',
    status     varchar(20)  not null default 'ACTIVE'
)