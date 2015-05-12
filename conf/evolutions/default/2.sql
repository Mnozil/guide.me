# --- !Ups

CREATE TABLE `Genre` (
  `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `name`	TEXT
);

# --- !Downs
DROP TABLE Genre