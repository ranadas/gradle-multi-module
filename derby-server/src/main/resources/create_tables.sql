CREATE TABLE security_question (
  id  INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  question_en VARCHAR(200) NOT NULL,
  question_fr VARCHAR(200) NOT NULL,
  created_date timestamp DEFAULT current_timestamp,
  last_modified timestamp DEFAULT current_timestamp
);

CREATE TABLE Product (
  id  INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(200) NOT NULL,
  price NUMERIC not null,
  created_date timestamp DEFAULT current_timestamp,
  last_modified timestamp DEFAULT current_timestamp
);