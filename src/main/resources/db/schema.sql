-- -----------------------------------------------------
-- Table `mydb`.`app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) DEFAULT 'default_user_name',
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(40) NOT NULL CHECK (`role` IN ('ROLE_ADMIN', 'ROLE_USER')),
  PRIMARY KEY (`user_id`)
);

CREATE UNIQUE INDEX `username_UNIQUE` ON `app_user` (`username` ASC);