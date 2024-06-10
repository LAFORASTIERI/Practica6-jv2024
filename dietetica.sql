create database dietetica;
use dietetica;

CREATE TABLE Producto (
    idProducto INT PRIMARY KEY,
    nombreProducto VARCHAR(100),
    stock INT,
    precio DOUBLE
);

CREATE TABLE Cliente (
    idCliente INT PRIMARY KEY,
    dni INT,
    nombreCliente VARCHAR(100),
    email VARCHAR(100),
    telefono INT
);

CREATE TABLE Factura (
    numeroFactura INT PRIMARY KEY,
    idCliente INT,
    idProducto INT,
    fecha DATE,
    cantidad INT,
    total DOUBLE,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);
