CREATE TABLE mascota(
	mascotaId INTEGER PRIMARY KEY,
    mascotaNombre char(30),
    propUsuario varchar(30) NOT NULL,
    FOREIGN KEY (propUsuario) REFERENCES propietario(propUsuario) ON UPDATE CASCADE
);

INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (1, "Sam", "JReina");
INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (2, "Merlín", "cristop");
INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (3, "Toby", "JReina");
INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (4, "Kira", "dantonito");
INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (5, "Sasha", "vincent");
INSERT INTO mascota(mascotaId, mascotaNombre, propUsuario) VALUES (6, "Tribilín", "dantonito");

-- SELECT * FROM mascota;
