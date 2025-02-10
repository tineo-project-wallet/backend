-- -----------------------------------------------------
-- Table `app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) DEFAULT 'default_user_name',
  `username` VARCHAR(45) NOT NULL CHECK (CHARACTER_LENGTH(`username`) >= 5),
  `password` VARCHAR(255) NOT NULL CHECK (CHARACTER_LENGTH(`password`) >= 8),
  `role` VARCHAR(40) NOT NULL CHECK (`role` IN ('ROLE_ADMIN', 'ROLE_USER')),
  PRIMARY KEY (`user_id`)
);

-- -----------------------------------------------------
-- Table `card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `card` (
  `card_id` BIGINT NOT NULL AUTO_INCREMENT,
  `alias` VARCHAR(45) NOT NULL,
  `available_balance` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`card_id`),
  CONSTRAINT `fk_card_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `app_user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Indexes
CREATE UNIQUE INDEX `username_UNIQUE` ON `app_user` (`username` ASC);
CREATE INDEX `fk_card_user_idx` ON `card` (`user_id` ASC);
