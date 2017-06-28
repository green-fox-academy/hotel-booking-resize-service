CREATE TABLE health_check (
  id BIGINT NOT NULL,
  ok INT NOT NULL,
  primary key (id)
);
CREATE SEQUENCE hibernate_sequence;