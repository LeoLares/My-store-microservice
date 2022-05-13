DROP TABLE IF EXISTS tbl_invoices;

CREATE TABLE tbl_invoices (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  number_invoice BIGINT,
  description VARCHAR(250) NOT NULL,
  customer_id BIGINT,
  create_at DATE,
  invoice_id BIGINT,
  state VARCHAR(250) NOT NULL 
);


DROP TABLE IF EXISTS tbl_invoice_items;

CREATE TABLE tbl_invoice_items (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  invoice_id BIGINT,
  quantity DOUBLE,
  price DOUBLE,
  product_id BIGINT,
  subTotal DOUBLE
);