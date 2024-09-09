INSERT INTO `users` (`id`, `email`, `name`, `password`, `surname`, `username`) VALUES
(1, 'nikos4222@outlook.com.gr', 'nikos', '$2a$12$wIUTI.cpLwqke.LwjLZ84OmknBjs9Zh7xT7PpivtPuAFpLV8Rmtjm', 'panago', 'panaras254'),
(2, 'nikos42222@outlook.com.gr', 'nikos', '$2a$10$Im08Wl9SCLY1MqUnCDy6COOMc5A0EFIJDeugJYxx4QlUpe9t.tAA6', 'panago', 'nikos42222');

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 1),
(1, 1);