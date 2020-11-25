-- assuming this is created in the docker-compose up setup!
use kn_db;
CREATE TABLE `contacts` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(70),
	`image_url` VARCHAR(2083),
	PRIMARY KEY (`id`)
);
