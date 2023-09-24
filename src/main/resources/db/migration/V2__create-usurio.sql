CREATE TABLE usuarios (
    id bigint not null auto_increment,
    nombre VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    pass VARCHAR(255),

    primary key (id)
    );
