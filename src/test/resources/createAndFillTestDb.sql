CREATE TABLE employee
(
  id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  surname VARCHAR(255),
  position VARCHAR(255),
  departmentId INTEGER,
  salary INTEGER
);

INSERT INTO employee (id, name, surname, position, departmentId, salary) VALUES (1, 'name1', 'surname1', 'boss',     1, 100);
INSERT INTO employee (id, name, surname, position, departmentId, salary) VALUES (2, 'name2', 'surname2', 'big boss', 1, 200);
INSERT INTO employee (id, name, surname, position, departmentId, salary) VALUES (3, 'name3', 'surname2', 'big boss', 1, 300);
INSERT INTO employee (id, name, surname, position, departmentId, salary) VALUES (4, 'name4', 'surname3', 'boss',     2, 100);
INSERT INTO employee (id, name, surname, position, departmentId, salary) VALUES (5, 'name5', 'surname4', 'boss',     2, 100);
