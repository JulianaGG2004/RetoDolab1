 INSERT INTO categorias (id,nombre) VALUES (1, 'Terror');
INSERT INTO categorias (id,nombre) VALUES (2, 'Accion');
INSERT INTO categorias (id,nombre) VALUES (3, 'Romance');
INSERT INTO categorias (id,nombre) VALUES (4, 'Aventura');
INSERT INTO categorias (id,nombre) VALUES (5, 'Comedia');
INSERT INTO categorias (id,nombre) VALUES (6, 'Drama');
INSERT INTO categorias (id,nombre) VALUES (7, 'Suspenso');
INSERT INTO categorias (id,nombre) VALUES (8, 'Infantil');

INSERT INTO actores (id,nombre) VALUES (1, 'Johnny Depp');
INSERT INTO actores (id,nombre) VALUES (2, 'Tom Hanks');
INSERT INTO actores (id,nombre) VALUES (3, 'Patrick Wils');
INSERT INTO actores (id,nombre) VALUES (4, 'Will Smith');
INSERT INTO actores (id,nombre) VALUES (5, 'Scarlett Johanson');
INSERT INTO actores (id,nombre) VALUES (6, 'Tom Hiddleston');
INSERT INTO actores (id,nombre) VALUES (7, 'Evan Peters');
INSERT INTO actores (id,nombre) VALUES (8, 'Chris Evans');

INSERT INTO directores (id,nombre) VALUES (1, 'James wan');
INSERT INTO directores (id,nombre) VALUES (2, 'Guillermo del Toro');
INSERT INTO directores (id,nombre) VALUES (3, 'Wes Craven');
INSERT INTO directores (id,nombre) VALUES (4, 'Jonh Carpenter');
INSERT INTO directores (id,nombre) VALUES (5, 'Quentin Tarantino');
INSERT INTO directores (id,nombre) VALUES (6, 'Andrew Stanton');

INSERT INTO peliculas (categoria_id,actor_id,director_id,nombre,descripcion,fecha_publicacion) VALUES (1,2,3,'Conjuro','Pelicula de terror','2021-01-01');
INSERT INTO peliculas (categoria_id,actor_id,director_id,nombre,descripcion,fecha_publicacion) VALUES (2,3,6,'Anabelle','Pelicula de terror','2021-02-03');
INSERT INTO peliculas (categoria_id,actor_id,director_id,nombre,descripcion,fecha_publicacion) VALUES (4,2,2,'Rapidos y Furiosos','Pelicula de accion','2021-01-04');
INSERT INTO peliculas (categoria_id,actor_id,director_id,nombre,descripcion,fecha_publicacion) VALUES (4,5,4,'Intensamente','Pelicula de animacion','2021-01-07');
INSERT INTO peliculas (categoria_id,actor_id,director_id,nombre,descripcion,fecha_publicacion) VALUES (5,6,6,'Sing','Pelicula de animacion','2021-01-08');