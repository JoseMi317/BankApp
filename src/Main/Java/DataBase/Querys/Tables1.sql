CREATE TABLE Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido_paterno VARCHAR(50),
    apellido_materno VARCHAR(50),
    fecha_nacimiento DATE,
    sexo CHAR(1),
    correo1 VARCHAR(100),
    correo2 VARCHAR(100),
    correo3 VARCHAR(100),
    departamento VARCHAR(50),
    municipio VARCHAR(50),
    direccion VARCHAR(150),
    codigo_postal VARCHAR(10),
    identificacion_tipo VARCHAR(50),
    identificacion_numero VARCHAR(50),
    lugar_trabajo VARCHAR(100),
    fecha_inicio_labores DATE,
    sueldo DECIMAL(10, 2)
);

CREATE TABLE Cuentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    numero_cuenta VARCHAR(20),
    tipo_cuenta VARCHAR(20),
    pin_hash VARCHAR(128),
    alertas_sms BOOLEAN,
    alertas_email BOOLEAN,
    acceso_banca_online BOOLEAN,
    chequera BOOLEAN,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

CREATE TABLE Transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cuenta_id INT,
    tipo_transaccion VARCHAR(50),
    monto DECIMAL(10, 2),
    fecha_transaccion DATETIME,
    descripcion VARCHAR(255),
    FOREIGN KEY (cuenta_id) REFERENCES Cuentas(id)
);

CREATE TABLE Prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    monto DECIMAL(10, 2),
    descripcion VARCHAR(255),
    fecha_solicitud DATETIME,
    estado VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

