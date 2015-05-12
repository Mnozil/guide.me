# --- !Ups
CREATE TABLE `Movie` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`title`	TEXT
);

# --- !Downs
DROP TABLE Movie