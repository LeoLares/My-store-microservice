DROP TABLE IF EXISTS categorias;

CREATE TABLE categorias (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS Productos;

CREATE TABLE Productos (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  stock DOUBLE,
  price DOUBLE,
  status VARCHAR(250) NOT NULL,
  created_at TIMESTAMP,
  categoria_id BIGINT
);