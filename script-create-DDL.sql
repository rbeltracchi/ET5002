CREATE DATABASE scat111mil;

CREATE TABLE Housing (
id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(255) NOT NULL,
hostServiceType VARCHAR(255) NOT NULL,
name VARCHAR(255) UNIQUE NOT NULL,
address VARCHAR(255) NOT NULL,
mail VARCHAR(255) NOT NULL,
phone VARCHAR(255) NOT NULL,
idUser_IdUser INT(11));



CREATE TABLE Room(
id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(255) NOT NULL,
hostServiceType VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL,
available Boolean NOT NULL,
capacity INT(11) NOT NULL,
housing_id int NOT NULL);

CREATE TABLE room_commodities(
Room_id INT(11) PRIMARY KEY NOT NULL,
commodities VARCHAR(255)
);

CREATE TABLE housing_commodities(
Housing_id INT(11) PRIMARY KEY NOT NULL,
commodities VARCHAR(255)
);

CREATE TABLE User(
userType VARCHAR(255) NOT NULL,
id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
idHousing int);


ALTER TABLE User 
    ADD CONSTRAINT UsersHousing
    FOREIGN KEY(idHousing)
    REFERENCES Housing(id);
    
ALTER TABLE Housing
    ADD CONSTRAINT HousingUser
    FOREIGN KEY(idUser_IdUser)
    REFERENCES User(id);    
    
    
ALTER TABLE Room
    ADD CONSTRAINT RoomHousing
    FOREIGN KEY(Housing_id)
    REFERENCES Housing(id);




