
CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    nombre_usuario VARCHAR(50) UNIQUE,
    contraseña_hash VARCHAR(128),
    rol VARCHAR(20),
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);