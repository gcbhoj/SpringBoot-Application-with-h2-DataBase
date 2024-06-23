CREATE TABLE ticket(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(25),
email VARCHAR(50),
event_name VARCHAR(50),
artist_name VARCHAR(25),
location VARCHAR(25),
event_date VARCHAR(10),
event_time VARCHAR(10),
price DOUBLE,
section VARCHAR(10),
row_number INT,
seat INT,
parking BOOLEAN
);
