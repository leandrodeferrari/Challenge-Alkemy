CREATE DATABASE IF NOT EXISTS disney;

USE disney;

CREATE TABLE IF NOT EXISTS personajes(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    edad TINYINT UNSIGNED NOT NULL,
    peso FLOAT UNSIGNED NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    imagen BLOB,
    historia VARCHAR(256),
	CONSTRAINT PK_personajes_id PRIMARY KEY(id)
);

ALTER TABLE personajes
ADD CONSTRAINT CK_personajes_edad CHECK (edad < 150);

CREATE TABLE IF NOT EXISTS generos(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    imagen BLOB,
    CONSTRAINT PK_generos_id PRIMARY KEY(id)
);

ALTER TABLE generos
ADD CONSTRAINT UQ_generos_nombre UNIQUE(nombre);

CREATE TABLE IF NOT EXISTS peliculas_series(
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    titulo VARCHAR(30) NOT NULL,
    fecha_de_creacion DATETIME NOT NULL,
    calificacion TINYINT UNSIGNED NOT NULL,
    imagen BLOB,
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
