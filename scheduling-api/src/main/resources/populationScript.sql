INSERT INTO users (name, email, password, phone_number, role) VALUES
('Alice Smith', 'alice1@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1111111111', 'USER'),
('Bob Johnson', 'bob2@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '2222222222', 'MANAGER'),
('Carol White', 'carol3@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '3333333333', 'MANAGER_VIP'),
('David Brown', 'david4@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '4444444444', 'USER'),
('Eve Black', 'eve5@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '5555555555', 'USER'),
('Frank Green', 'frank6@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '6666666666', 'MANAGER'),
('Grace Lee', 'grace7@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '7777777777', 'MANAGER_VIP'),
('Henry King', 'henry8@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '8888888888', 'USER'),
('Ivy Scott', 'ivy9@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '9999999999', 'USER'),
('Jack Young', 'jack10@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1010101010', 'MANAGER'),
('Karen Hall', 'karen11@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1111111122', 'MANAGER_VIP'),
('Leo Adams', 'leo12@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1212121212', 'USER'),
('Mona Clark', 'mona13@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1313131313', 'USER'),
('Nina Lewis', 'nina14@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1414141414', 'MANAGER'),
('Oscar Walker', 'oscar15@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1515151515', 'MANAGER_VIP'),
('Paul Allen', 'paul16@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1616161616', 'USER'),
('Quinn Wright', 'quinn17@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1717171717', 'USER'),
('Rita Hill', 'rita18@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1818181818', 'MANAGER'),
('Sam Baker', 'sam19@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '1919191919', 'MANAGER_VIP'),
('Tina Evans', 'tina20@email.com', '$2a$10$gIJILJx7DsURB047ADpSkeOVQsoQqqdLmzuERwnaCkl8q8IN71ADG', '2020202020', 'USER');

SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));