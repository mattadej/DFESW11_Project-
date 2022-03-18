CREATE SCHEMA employees;
USE employees;
SHOW tables;
SHOW columns FROM employee;

INSERT INTO employee(first_name, second_name, age, email, gender) 	
	values	('Matthew', 'Adejumo', 26, 'madejumo@qa.com', 'Male');

SELECT * FROM employee;


