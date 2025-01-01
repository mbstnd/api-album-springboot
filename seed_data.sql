DROP DATABASE IF EXISTS coleccion;

CREATE DATABASE coleccion;

USE coleccion;

DROP DATABASE IF EXISTS usuarios;
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL UNIQUE,
    last_name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT uk_usuario_nombres UNIQUE (first_name, last_name)
);

DROP DATABASE IF EXISTS albums;
CREATE TABLE albums (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    imagen VARCHAR(255),
    fecha_lanzamiento DATE,
    tipo_laminas VARCHAR(50),
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_album_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT uk_album_nombre UNIQUE (nombre)
);

DROP DATABASE IF EXISTS laminas;
CREATE TABLE laminas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    imagen VARCHAR(255),
    cantidad_repetidas INT DEFAULT 0,
    faltante BOOLEAN DEFAULT true,
    album_id BIGINT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_lamina_album FOREIGN KEY (album_id) REFERENCES albums(id) ON DELETE CASCADE
);

CREATE INDEX idx_album_usuario ON albums(usuario_id);
CREATE INDEX idx_lamina_album ON laminas(album_id);
CREATE INDEX idx_lamina_faltante ON laminas(faltante);
CREATE INDEX idx_lamina_repetidas ON laminas(cantidad_repetidas);


INSERT INTO usuarios (first_name, last_name, email) VALUES
('Mario', 'Quevedo', 'mario.dev@gmail.com'),
('Patricio', 'Ibargaray', 'patricio.dev@gmail.com'),
('Franco', 'Vasquez', 'franco.dev@gmail.com');

INSERT INTO albums (nombre, imagen, fecha_lanzamiento, tipo_laminas, usuario_id) VALUES
('Mundial Qatar 2022', 'qatar2022.jpg', '2022-11-20', 'PAPEL', 1),
('Dragon Ball Super', 'dbs.jpg', '2024-01-15', 'METALICA', 1),
('Liga Chilena 2024', 'liga-chilena.jpg', '2024-03-01', 'PAPEL', 2);

INSERT INTO laminas (nombre, imagen, cantidad_repetidas, faltante, album_id) VALUES
('Lionel Messi', 'messi.jpg', 2, false, 1),
('Cristiano Ronaldo', 'cr7.jpg', 0, true, 1),
('Goku Ultra Instinto', 'goku-ui.jpg', 1, false, 2),
('Vegeta Blue', 'vegeta-blue.jpg', 0, true, 2),
('Arturo Vidal', 'king-arturo.jpg', 3, false, 3);
