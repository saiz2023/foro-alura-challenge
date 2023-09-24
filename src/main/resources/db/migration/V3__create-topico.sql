CREATE TABLE topicos (
    id bigint not null auto_increment,
    titulo VARCHAR(255)UNIQUE,
    mensaje TEXT ,
    fecha_creacion TIMESTAMP,
    status VARCHAR(255),
    autor_id bigint,
    curso_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_id) REFERENCES usuarios (id),
    FOREIGN KEY (curso_id) REFERENCES cursos (id)
    );
