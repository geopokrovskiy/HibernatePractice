CREATE TABLE specs
(
    id     bigint primary key auto_increment,
    name   varchar(50) not null,
    status varchar(10)   not null
);

CREATE TABLE skills
(
    id     bigint primary key auto_increment,
    name   varchar(50) not null,
    status varchar(10)   not null
);

CREATE TABLE developers
(
    id        bigint primary key auto_increment,
    spec_id   bigint      not null,
    firstName varchar(50) not null,
    lastName  varchar(50) not null,
    status varchar(10)   not null,
    foreign key (spec_id) references specs (id)
        on delete cascade on update cascade
);

CREATE TABLE devs_skills
(
    dev_id   bigint not null,
    skill_id bigint not null,
    foreign key (dev_id) references developers (id)
        on delete cascade on update cascade,
    foreign key (skill_id) references skills (id)
        on delete cascade on update cascade
);

