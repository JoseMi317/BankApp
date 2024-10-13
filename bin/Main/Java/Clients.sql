INSERT INTO Clientes (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, sexo, correo1, correo2, correo3, departamento, municipio, direccion, codigo_postal, identificacion_tipo, identificacion_numero, lugar_trabajo, fecha_inicio_labores, sueldo)
VALUES 
('Juan', 'Pérez', 'López', '1990-01-01', 'M', 'juan.perez@example.com', NULL, NULL, 'Guatemala', 'Guatemala', 'Calle 1', '01001', 'DPI', '123456789', 'Empresa X', '2015-01-01', 5000.00),
('Ana', 'Martínez', 'Gómez', '1992-02-02', 'F', 'ana.martinez@example.com', NULL, NULL, 'Guatemala', 'Mixco', 'Avenida 2', '01057', 'DPI', '987654321', 'Empresa Y', '2016-02-01', 6000.00),
('Luis', 'Ramírez', 'Díaz', '1985-03-03', 'M', 'luis.ramirez@example.com', NULL, NULL, 'Guatemala', 'Villa Nueva', 'Calle 3', '01064', 'DPI', '1122334455', 'Empresa Z', '2014-03-01', 7000.00),
('María', 'Hernández', 'Méndez', '1988-04-04', 'F', 'maria.hernandez@example.com', NULL, NULL, 'Guatemala', 'Santa Catarina Pinula', 'Avenida 4', '01015', 'DPI', '6677889900', 'Empresa W', '2017-04-01', 8000.00),
('Carlos', 'Lopez', 'Martínez', '1995-05-05', 'M', 'carlos.lopez@example.com', NULL, NULL, 'Guatemala', 'Escuintla', 'Calle 5', '01001', 'DPI', '5566778899', 'Empresa Q', '2019-05-01', 5500.00);

INSERT INTO Cuentas (cliente_id, numero_cuenta, tipo_cuenta, pin_hash, alertas_sms, alertas_email, acceso_banca_online, chequera)
VALUES 
(1, '1234567890', 'Ahorros', '1234', TRUE, TRUE, TRUE, TRUE),
(2, '2345678901', 'Corriente', 'abcd', TRUE, FALSE, TRUE, FALSE),
(3, '3456789012', 'Ahorros', '5678', TRUE, TRUE, FALSE, TRUE),
(4, '4567890123', 'Corriente', 'efgh', FALSE, TRUE, TRUE, FALSE),
(5, '5678901234', 'Ahorros', 'ijkl', TRUE, TRUE, TRUE, TRUE);

ALTER TABLE Cuentas ADD COLUMN nombre_usuario VARCHAR(50);
ALTER TABLE Cuentas ADD COLUMN contraseña_hash VARCHAR(255);
INSERT INTO Clientes (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, sexo, correo1, departamento, municipio, direccion, codigo_postal, identificacion_tipo, identificacion_numero, lugar_trabajo, fecha_inicio_labores, sueldo, nombre_usuario, contraseña_hash)
VALUES 
('Juan', 'Pérez', 'López', '1990-01-01', 'M', 'juan.perez@example.com', 'Guatemala', 'Guatemala', 'Calle 1', '01001', '123456789', 'DPI', 'Empresa X', '2015-01-01', 5000.00, 'juan', '1234'),
('Ana', 'Martínez', 'Gómez', '1992-02-02', 'F', 'ana.martinez@example.com', 'Guatemala', 'Mixco', 'Avenida 2', '01057', '987654321', 'DPI', 'Empresa Y', '2016-02-01', 6000.00, 'ana', 'abcd'),
('Luis', 'Ramírez', 'Díaz', '1985-03-03', 'M', 'luis.ramirez@example.com', 'Guatemala', 'Villa Nueva', 'Calle 3', '01064', '1122334455', 'DPI', 'Empresa Z', '2014-03-01', 7000.00, 'luis', '5678'),
('María', 'Hernández', 'Méndez', '1988-04-04', 'F', 'maria.hernandez@example.com', 'Guatemala', 'Santa Catarina Pinula', 'Avenida 4', '01015', '6677889900', 'DPI', 'Empresa W', '2017-04-01', 8000.00, 'maria', 'efgh'),
('Carlos', 'Lopez', 'Martínez', '1995-05-05', 'M', 'carlos.lopez@example.com', 'Guatemala', 'Escuintla', 'Calle 5', '01001', '5566778899', 'DPI', 'Empresa Q', '2019-05-01', 5500.00, 'carlos', 'ijkl');INSERT INTO Clientes (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, sexo, correo1, departamento, municipio, direccion, codigo_postal, identificacion_tipo, identificacion_numero, lugar_trabajo, fecha_inicio_labores, sueldo, usuario, contraseña)
VALUES 
('Juan', 'Pérez', 'López', '1990-01-01', 'M', 'juan.perez@example.com', 'Guatemala', 'Guatemala', 'Calle 1', '01001', '123456789', 'DPI', 'Empresa X', '2015-01-01', 5000.00, 'juan', '1234'),
('Ana', 'Martínez', 'Gómez', '1992-02-02', 'F', 'ana.martinez@example.com', 'Guatemala', 'Mixco', 'Avenida 2', '01057', '987654321', 'DPI', 'Empresa Y', '2016-02-01', 6000.00, 'ana', 'abcd'),
('Luis', 'Ramírez', 'Díaz', '1985-03-03', 'M', 'luis.ramirez@example.com', 'Guatemala', 'Villa Nueva', 'Calle 3', '01064', '1122334455', 'DPI', 'Empresa Z', '2014-03-01', 7000.00, 'luis', '5678'),
('María', 'Hernández', 'Méndez', '1988-04-04', 'F', 'maria.hernandez@example.com', 'Guatemala', 'Santa Catarina Pinula', 'Avenida 4', '01015', '6677889900', 'DPI', 'Empresa W', '2017-04-01', 8000.00, 'maria', 'efgh'),
('Carlos', 'Lopez', 'Martínez', '1995-05-05', 'M', 'carlos.lopez@example.com', 'Guatemala', 'Escuintla', 'Calle 5', '01001', '5566778899', 'DPI', 'Empresa Q', '2019-05-01', 5500.00, 'carlos', 'ijkl');
UPDATE Cuentas SET nombre_usuario = 'juan', contraseña_hash = '1234' WHERE cliente_id = 1;
UPDATE Cuentas SET nombre_usuario = 'ana', contraseña_hash = 'abcd' WHERE cliente_id = 2;
UPDATE Cuentas SET nombre_usuario = 'luis', contraseña_hash = '5678' WHERE cliente_id = 3;
UPDATE Cuentas SET nombre_usuario = 'maria', contraseña_hash = 'efgh' WHERE cliente_id = 4;
UPDATE Cuentas SET nombre_usuario = 'carlos', contraseña_hash = 'ijkl' WHERE cliente_id = 5;
