-- Table app_user
INSERT INTO `app_user` (`user_id`,`name`, `username`, `password`, `role`) VALUES
    (1, 'Wallet Admin', 'admin', 'adminpassword', 'ROLE_ADMIN'),
    (2, 'Tineo', 'tineo', 'password', 'ROLE_USER'),
    (3, 'Luis', 'luiss', 'password', 'ROLE_USER'),
    (4, 'Juan', 'juann', 'password', 'ROLE_USER'),
    (5, 'Pedro', 'pedro', 'password', 'ROLE_USER'),
    (6, 'Maria', 'maria', 'password', 'ROLE_USER');

-- Table card
INSERT INTO card (alias, available_balance, user_id) VALUES
    ('Card 1', 100.00, 1),
    ('Card 2', 200.00, 1),
    ('Card 3', 300.00, 3),
    ('Card 4', 400.00, 4),
    ('Card 5', 500.00, 5),
    ('Card 6', 600.00, 6);
