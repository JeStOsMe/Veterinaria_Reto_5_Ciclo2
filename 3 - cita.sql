CREATE TABLE cita(
	citaId INTEGER PRIMARY KEY,
    citaFecha DATETIME,
    citaDescripcion varchar(180) NOT NULL,
    mascotaId integer NOT NULL,
    FOREIGN KEY (mascotaId) REFERENCES mascota(mascotaId) ON UPDATE CASCADE
);

INSERT INTO cita(citaId, citaFecha, citaDescripcion, mascotaId) VALUES (1, '2021-08-01 15:30:00', "El gato se encuentra con un peso 
normal y en su cita de control de ojos al parecer la catarata se detuvo", 1);
INSERT INTO cita(citaId, citaFecha, citaDescripcion, mascotaId) VALUES (2, '2021-08-02 18:30:00', "Merlín presenta halitosis reflejo de 
un problema localizado en la boca o incluso de algún problema del aparato digestivo.", 2);
INSERT INTO cita(citaId, citaFecha, citaDescripcion, mascotaId) VALUES (3, '2021-06-30 04:30:20', "El pronóstico es reservado. Presenta 
molestias bucales (gingivitis), vómitos, fiebre. Se deja en hospitalización.", 4);
INSERT INTO cita(citaId, citaFecha, citaDescripcion, mascotaId) VALUES (4, '2021-08-13 16:30:20', "Viene para el programa de perritos de 
la tercera edad.", 5);