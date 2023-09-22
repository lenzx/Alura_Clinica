ALTER TABLE medico ADD activo tinyint(1);
UPDATE medico set activo = 1;