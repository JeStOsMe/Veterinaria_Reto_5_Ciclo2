CREATE TABLE propietario(
	propUsuario varchar(30) PRIMARY KEY,
	propApellido varchar(30) NOT NULL DEFAULT " ",
	propNombre varchar(30) NOT NULL,
	propTelefono char(25) NULL
);

INSERT INTO propietario(propUsuario, propNombre, propApellido, propTelefono) VALUES ("vincent", "Vicente", "Vanghogh", "3221234564");
INSERT INTO propietario(propUsuario, propNombre, propApellido, propTelefono) VALUES ("dantonito", "Diego", "Antony", "3133151232");
INSERT INTO propietario(propUsuario, propNombre, propApellido, propTelefono) VALUES ("haylee", "Hayo", "Lee", "3192212121");
INSERT INTO propietario(propUsuario, propNombre, propApellido, propTelefono) VALUES ("cristop", "Christopher", "Rojas", "2927272 ext 333");
INSERT INTO propietario(propUsuario, propNombre, propApellido, propTelefono) VALUES ("JReina", "Johan", "Reina", "+549 4984445413");

-- SELECT * FROM propietario;