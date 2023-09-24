CREATE TABLE respuestas (
    id bigint not null auto_increment,
    mensaje TEXT,
    fecha_creacion TIMESTAMP,
    solucion BOOLEAN,
    topico_id bigint,
    autor_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (topico_id) REFERENCES topicos (id),
    FOREIGN KEY (autor_id) REFERENCES usuarios (id)
    );
