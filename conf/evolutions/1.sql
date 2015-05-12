# --- !Ups
CREATE TABLE `Movie` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT
);

# --- !Downs
DROP TABLE Movie