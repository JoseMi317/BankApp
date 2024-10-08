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


