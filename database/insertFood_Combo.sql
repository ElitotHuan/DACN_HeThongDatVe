USE food_combo;

INSERT INTO food (food_name, price)
VALUES
('Bắp rang caramel', 35000),
('Bắp rang phô mai', 35000),
('Bắp rang truyền thống', 32000),
('Bắp rang mix', 35000),
('Coca', 25000),
('Pepsi', 25000),
('7up', 25000),
('Redbull', 25000),
('Snack Poca',15000),
('Snack Oshi',15000),
('Snack Swing',15000);


INSERT INTO combo (combo_name, price)
VALUES
('combo 4 người mix',299000),
('combo caramel x phô mai + Coca x Pepsi',110000),
('combo mix x truyền thống + 7up x Redbull',110000),
('combo truyền thống x Pepsi',55000),
('combo Snack',40000),
('combo 4 loại nước',90000),
('combo best seller phô mai x Coca',55000000);

INSERT INTO combo_detail (combo_id, food_id)
VALUES
(1,1),(1,2),(1,3),(1,4),
(1,5),(1,6),(1,7),(1,8),

(2,1),(2,2),(2,5),(2,6),

(3,4),(3,3),(3,7),(3,8),

(4,3),(4,6),

(5,9),(5,10),(5,11),

(6,5),(6,6),(6,7),(6,8),

(7,2),(7,5);




