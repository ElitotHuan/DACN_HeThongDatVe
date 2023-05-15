CREATE DATABASE food_combo;

USE food_combo;

CREATE TABLE food (
    food_id INT PRIMARY KEY AUTO_INCREMENT,
    food_name VARCHAR(255) ,
    price bigint
); 

CREATE TABLE combo (
    combo_id INT PRIMARY KEY AUTO_INCREMENT,
    combo_name VARCHAR(255), 
    price bigint
);

CREATE TABLE combo_detail (
    combo_id INT,
    food_id INT,
	FOREIGN KEY (combo_id) REFERENCES combo(combo_id) ON DELETE CASCADE,
    FOREIGN KEY (food_id) REFERENCES food(food_id) ON DELETE CASCADE
);

