INSERT INTO users (name, email, password, phone_number, role) VALUES
('Alice Smith', 'alice1@email.com', 'password1', '1111111111', 'ADMIN'),
('Bob Johnson', 'bob2@email.com', 'password2', '2222222222', 'MANAGER'),
('Carol White', 'carol3@email.com', 'password3', '3333333333', 'MANAGER_VIP'),
('David Brown', 'david4@email.com', 'password4', '4444444444', 'USER'),
('Eve Black', 'eve5@email.com', 'password5', '5555555555', 'ADMIN'),
('Frank Green', 'frank6@email.com', 'password6', '6666666666', 'MANAGER'),
('Grace Lee', 'grace7@email.com', 'password7', '7777777777', 'MANAGER_VIP'),
('Henry King', 'henry8@email.com', 'password8', '8888888888', 'USER'),
('Ivy Scott', 'ivy9@email.com', 'password9', '9999999999', 'ADMIN'),
('Jack Young', 'jack10@email.com', 'password10', '1010101010', 'MANAGER'),
('Karen Hall', 'karen11@email.com', 'password11', '1111111122', 'MANAGER_VIP'),
('Leo Adams', 'leo12@email.com', 'password12', '1212121212', 'USER'),
('Mona Clark', 'mona13@email.com', 'password13', '1313131313', 'ADMIN'),
('Nina Lewis', 'nina14@email.com', 'password14', '1414141414', 'MANAGER'),
('Oscar Walker', 'oscar15@email.com', 'password15', '1515151515', 'MANAGER_VIP'),
('Paul Allen', 'paul16@email.com', 'password16', '1616161616', 'USER'),
('Quinn Wright', 'quinn17@email.com', 'password17', '1717171717', 'ADMIN'),
('Rita Hill', 'rita18@email.com', 'password18', '1818181818', 'MANAGER'),
('Sam Baker', 'sam19@email.com', 'password19', '1919191919', 'MANAGER_VIP'),
('Tina Evans', 'tina20@email.com', 'password20', '2020202020', 'USER');

SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));