DROP SCHEMA IF EXISTS project CASCADE;

CREATE SCHEMA project;

CREATE TABLE project.users(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  surname TEXT NOT NULL,
  email TEXT NOT NULL
);

INSERT INTO project.users (name, surname, email)
VALUES
  ('John', 'Doe', 'john.doe@gsemail.com'),
  ('Jane', 'Doe', 'jane.doe@girlmail.com');

CREATE TABLE project.location(
  id SERIAL PRIMARY KEY NOT NULL,
  city TEXT NOT NULL,
  postal_code TEXT NOT NULL,
  info TEXT
);

INSERT INTO project.location(city, postal_code)
VALUES
  ('Cape Town', '8001'),
  ('Tableview', '7441');

INSERT INTO project.location(city, postal_code, info)
VALUES
  ('Camps bay', '8012', 'Rich people live here'),
  ('Gardens', '8002', 'The old part of town');
