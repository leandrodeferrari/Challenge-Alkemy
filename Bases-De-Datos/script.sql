CREATE DATABASE IF NOT EXISTS disney;

USE disney;

CREATE TABLE IF NOT EXISTS personajes(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    edad TINYINT UNSIGNED NOT NULL,
    peso FLOAT UNSIGNED NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    imagen VARCHAR(30),
    historia VARCHAR(255),
	CONSTRAINT PK_personajes_id PRIMARY KEY(id)
);

ALTER TABLE personajes
ADD CONSTRAINT CK_personajes_edad CHECK (edad <= 150);

ALTER TABLE personajes
ADD CONSTRAINT CK_personajes_peso CHECK (peso <= 600);

CREATE TABLE IF NOT EXISTS generos(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    imagen VARCHAR(30),
    CONSTRAINT PK_generos_id PRIMARY KEY(id)
);

ALTER TABLE generos
ADD CONSTRAINT UQ_generos_nombre UNIQUE(nombre);

CREATE TABLE IF NOT EXISTS peliculas_series(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    titulo VARCHAR(30) NOT NULL,
    fecha_de_creacion DATETIME NOT NULL,
    calificacion TINYINT UNSIGNED NOT NULL,
    imagen VARCHAR(30),
	id_genero INT UNSIGNED NOT NULL,
	CONSTRAINT PK_peliculas_series_id PRIMARY KEY(id),
    CONSTRAINT FK_peliculas_series_id_genero FOREIGN KEY(id_genero) REFERENCES generos(id)
);

ALTER TABLE peliculas_series
ADD CONSTRAINT CK_peliculas_series_calificacion CHECK (calificacion <= 5);

ALTER TABLE peliculas_series
ALTER fecha_de_creacion SET DEFAULT (CURRENT_TIMESTAMP());

ALTER TABLE peliculas_series
ALTER calificacion SET DEFAULT (0);

CREATE TABLE IF NOT EXISTS personajes_peliculas_series(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    id_personaje INT UNSIGNED NOT NULL,
    id_pelicula_serie INT UNSIGNED NOT NULL,
    CONSTRAINT PK_personajes_peliculas_series_id PRIMARY KEY(id),
    CONSTRAINT FK_personajes_peliculas_series_id_personaje FOREIGN KEY(id_personaje) REFERENCES personajes(id),
    CONSTRAINT FK_personajes_peliculas_series_id_pelicula_serie FOREIGN KEY(id_pelicula_serie) REFERENCES peliculas_series(id)
);

ALTER TABLE personajes_peliculas_series
ADD CONSTRAINT UQ_personajes_peliculas_series_id_personaje_id_pelicula_serie UNIQUE (id_personaje, id_pelicula_serie);

-- Tabla de usuarios, para Spring Security

CREATE TABLE IF NOT EXISTS usuarios(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    fecha_de_alta DATETIME NOT NULL,
    rol VARCHAR(20) NOT NULL,
    CONSTRAINT PK_usuarios_id PRIMARY KEY(id)
);

ALTER TABLE usuarios
ALTER fecha_de_alta SET DEFAULT (CURRENT_TIMESTAMP());

ALTER TABLE usuarios
ADD CONSTRAINT UQ_usuarios_email UNIQUE(email);

-- Insertar registros a nuestras tablas

-- Tabla generos

INSERT INTO disney.generos (nombre, imagen) VALUES ('Romance', 'romance.jpg');
INSERT INTO disney.generos (nombre, imagen) VALUES ('Terror', 'terror.jpg');
INSERT INTO disney.generos (nombre, imagen) VALUES ('Suspenso', 'suspenso.jpg');
INSERT INTO disney.generos (nombre, imagen) VALUES ('Comedia', 'comedia.jpg');
INSERT INTO disney.generos (nombre, imagen) VALUES ('Tragedia', 'tragedia.jpg');
INSERT INTO disney.generos (nombre, imagen) VALUES ('Drama', 'drama.jpg');

-- Tabla personajes

INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (25, 65, 'Leandro', 'leandro.jpg', 'Historia de Leandro');
INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (37, 85, 'Juan', 'juan.jpg', 'Historia de Juan');
INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (29, 55, 'Emma', 'emma.jpg', 'Historia de Emma');
INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (28, 60, 'Ariana', 'ariana.jpg', 'Historia de Ariana');
INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (45, 89, 'Pedro', 'pedro.jpg', 'Historia de Pedro');
INSERT INTO disney.personajes (edad, peso, nombre, imagen, historia) VALUES (30, 70, 'Marcela', 'marcela.jpg', 'Historia de Marcela');

-- Tabla peliculas_series

INSERT INTO disney.peliculas_series (titulo, calificacion, imagen, id_genero) VALUES ('Scream Queens', 5, 'screamqueens.jpg', 3);
INSERT INTO disney.peliculas_series (titulo, calificacion, imagen, id_genero) VALUES ('Glee', 4, 'glee.jpg', 5);
INSERT INTO disney.peliculas_series (titulo, calificacion, imagen, id_genero) VALUES ('El exorcismo de Emily', 4, 'elexorcismodeemily.jpg', 1);
INSERT INTO disney.peliculas_series (titulo, calificacion, imagen, id_genero) VALUES ('Next', 0, 'next.jpg', 5);
INSERT INTO disney.peliculas_series (titulo, calificacion, imagen, id_genero) VALUES ('El gran Gatsby', 5, 'elgrangatsby.jpg', 5);

-- Tabla intermedia personajes_peliculas_series

INSERT INTO disney.personajes_peliculas_series (id_personaje, id_pelicula_serie) VALUES (4, 1);
INSERT INTO disney.personajes_peliculas_series (id_personaje, id_pelicula_serie) VALUES (5, 3);
INSERT INTO disney.personajes_peliculas_series (id_personaje, id_pelicula_serie) VALUES (1, 4);
INSERT INTO disney.personajes_peliculas_series (id_personaje, id_pelicula_serie) VALUES (2, 4);
INSERT INTO disney.personajes_peliculas_series (id_personaje, id_pelicula_serie) VALUES (3, 5);
