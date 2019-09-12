INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_TEACHER');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_SUBTEACHER');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_PARENT');


INSERT INTO `user` (`created`, `username`, `password`, `enabled`) VALUES ('2019-09-09 18:00:00', 'admin@test.pl', '$2a$10$7yludK5VllyEIDmzaCpXaOBtk7eJ30kxx/vOoBz.1Twk1vZe3XuD.', 1);
INSERT INTO `user` (`created`, `username`, `password`, `enabled`) VALUES ('2019-09-09 18:00:00', 'teacher@test.pl', '$2a$10$7yludK5VllyEIDmzaCpXaOBtk7eJ30kxx/vOoBz.1Twk1vZe3XuD.', 1);
INSERT INTO `user` (`created`, `username`, `password`, `enabled`) VALUES ('2019-09-09 18:00:00', 'subteacher@test.pl', '$2a$10$7yludK5VllyEIDmzaCpXaOBtk7eJ30kxx/vOoBz.1Twk1vZe3XuD.', 1);
INSERT INTO `user` (`created`, `username`, `password`, `enabled`) VALUES ('2019-09-09 18:00:00', 'parent@test.pl', '$2a$10$7yludK5VllyEIDmzaCpXaOBtk7eJ30kxx/vOoBz.1Twk1vZe3XuD.', 1);

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 3);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (4, 4);

INSERT INTO `children` (`first_name`, `last_name`, `phone`, `pesel`, `active`) VALUES ('Jan', 'Nowak', '5805739', '86052815599', 1);
INSERT INTO `children` (`first_name`, `last_name`, `phone`, `pesel`, `active`) VALUES ('Jan', 'Nowak', '5805739', '86052815599', 1);
INSERT INTO `children` (`first_name`, `last_name`, `phone`, `pesel`, `active`) VALUES ('Jan', 'Nowak', '5805739', '86052815599', 1);
