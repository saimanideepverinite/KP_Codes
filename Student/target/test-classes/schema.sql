DROP TABLE IF EXISTS mytable;

CREATE TABLE mytable (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  marks INT,
  percentage INT,
  gender VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL
);