CREATE TABLE employee
(
  id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  surname VARCHAR(255),
  position VARCHAR(255),
  departmentId INTEGER,
  salary INTEGER
);