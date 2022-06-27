drop SCHEMA IF EXISTS lab402;
CREATE SCHEMA lab402;
USE lab402;

CREATE TABLE employee (
	employee_id INT NOT NULL,
    department VARCHAR(255),
    name VARCHAR(255),
    status VARCHAR(255),
    PRIMARY KEY (employee_id)
);

CREATE TABLE patient (
	patient_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    date_of_birth DATE,
    admitted_by INT,
    PRIMARY KEY (patient_id),
    FOREIGN KEY (admitted_by) REFERENCES employee(employee_id)
);
