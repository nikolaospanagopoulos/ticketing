INSERT INTO `users` (`id`, `email`, `name`, `password`, `surname`, `username`) VALUES
(1, 'tester@outlook.com.gr', 'nikos', '$2a$12$XvB8AkTs8xXI28Af/0TJN.Nm3X0kDa6dYuIZfO4cpSH.ZqdFt505C', 'tester-surname', 'admin'),
(2, 'tester-user@outlook.com.gr', 'nikos', '$2a$12$wwlPcduh1axqg9o4Fjx3Y.0Zkfo/EFwz5eh.zDrHw1GHpnLA1s7HC', 'user-surname', 'tester');

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(1, 1);