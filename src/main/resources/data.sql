DROP TABLE IF EXISTS championship.players CASCADE;

CREATE TABLE IF NOT EXISTS championship.players (
  --dispatch_id BIGSERIAL NOT NULL,
  document VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  skill INT NULL,
  country VARCHAR(50) NOT NULL,
  gender CHARACTER VARYING(1),
  age INT NOT NULL,
  CONSTRAINT pk_document PRIMARY KEY (document)
);

INSERT INTO championship.players VALUES
('47082903','Alvaro Aguinaga',80,'Peru','M',30),
('47082909','Diego Aguinaga',70,'Peru','M',27),
('47082907','Gonzalo Aguinaga',90,'Peru','M',33);

DROP TABLE IF EXISTS championship.configurations CASCADE;

CREATE TABLE IF NOT EXISTS championship.configurations (
  configuration_id BIGSERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(50) NOT NULL,
  amount_match SMALLINT NOT NULL,
  type CHARACTER VARYING(1) NOT NULL,
  first_award DOUBLE PRECISION NOT NULL,
  second_award DOUBLE PRECISION NOT NULL,
  date_championship TIMESTAMP,
  CONSTRAINT pk_configuration_id PRIMARY KEY (configuration_id)
);

INSERT INTO championship.configurations
(name,description,amount_match,type,first_award,second_award,date_championship)
VALUES
('atp-world-tour','ATP World Tour 2022',8,'M',17000,15000,'2022-11-18'),
('wimbledon','Wimbledon 2022',8,'M',17000,15000,'2022-11-18'),
('atp-world-tour','ATP World Tour 2022',8,'F',20000,17000,'2022-11-18'),
('wimbledon','Wimbledon 2022',8,'F',20000,17000,'2022-11-18');


DROP TABLE IF EXISTS championship.championships CASCADE;

CREATE TABLE IF NOT EXISTS championship.championships (
  identifier VARCHAR(100) NOT NULL,
  configuration_name VARCHAR(50) NOT NULL,
  country VARCHAR(50) NULL,
  watch_tv VARCHAR(50) NULL,
  winner VARCHAR(10) NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  CONSTRAINT pk_championships_identifier PRIMARY KEY (identifier)
);

DROP TABLE IF EXISTS championship.matches CASCADE;

CREATE TABLE IF NOT EXISTS championship.matches (
  identifier VARCHAR(100) NOT NULL,
  number_match INT NOT NULL,
  name_match VARCHAR(100) NOT NULL,
  championship VARCHAR(100) NOT NULL,
  player_one VARCHAR(10) NOT NULL,
  player_two VARCHAR(10) NOT NULL,
  player_winner VARCHAR(10) NOT NULL,
  score VARCHAR(10) NOT NULL,
  phase SMALLINT NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  CONSTRAINT pk_identifier PRIMARY KEY (identifier)
);
