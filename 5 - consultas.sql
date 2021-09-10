SELECT 'Consulta 1';
SELECT mascotaNombre FROM mascota ORDER BY (mascotaNombre);

SELECT 'Consulta 2';
SELECT mascotaNombre, citaDescripcion, citaFecha FROM cita JOIN mascota ON (cita.mascotaId = mascota.mascotaId) ORDER BY citaFecha DESC;

SELECT 'Consulta 3';
SELECT mascotaNombre FROM mascota WHERE (propUsuario = "JReina");

SELECT 'Consulta 4';
SELECT mascotaNombre, citaDescripcion, citaFecha, propNombre, propApellido, propTelefono FROM cita JOIN mascota ON (cita.mascotaId = mascota.mascotaId)
JOIN propietario ON (propietario.propUsuario = mascota.propUsuario) ORDER BY citaFecha DESC;