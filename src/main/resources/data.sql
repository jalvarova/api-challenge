DROP TABLE IF EXISTS championship.players CASCADE;

CREATE TABLE IF NOT EXISTS championship.players (
  --dispatch_id BIGSERIAL NOT NULL,
  document VARCHAR(10) NOT NULL,
  name VARCHAR(50) NOT NULL,
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
  name_championship VARCHAR(20) NOT NULL,
  amount_match SMALLINT NOT NULL,
  type CHARACTER VARYING(1) NOT NULL,
  firstAward DOUBLE PRECISION NOT NULL,
  secondAward DOUBLE PRECISION NOT NULL,
  date_championship TIMESTAMP,
  CONSTRAINT pk_configuration_id PRIMARY KEY (configuration_id)
);

INSERT INTO championship.configurations
(name_championship,amount_match,type,firstAward,secondAward,date_championship)
VALUES
('ATP World Tour 2022',8,'M',17000,15000,'2022-11-18'),
('Wimbledon 2022',8,'M',17000,15000,'2022-11-18'),
('ATP World Tour 2022',8,'F',20000,17000,'2022-11-18'),
('Wimbledon 2022',8,'F',20000,17000,'2022-11-18');


DROP TABLE IF EXISTS championship.championships CASCADE;

CREATE TABLE IF NOT EXISTS championship.championships (
  identifier VARCHAR(50) NOT NULL,
  configuration_id BIGSERIAL NOT NULL,
  country VARCHAR(50),
  watch_tv VARCHAR(50),
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  CONSTRAINT pk_championships_identifier PRIMARY KEY (identifier),
  CONSTRAINT fk_configuration_id FOREIGN KEY (configuration_id)
  REFERENCES championship.configurations(configuration_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

DROP TABLE IF EXISTS championship.matches CASCADE;

CREATE TABLE IF NOT EXISTS championship.matches (
  identifier VARCHAR(50) NOT NULL,
  nameMatch VARCHAR(100) NOT NULL,
  championship_id VARCHAR(50) NOT NULL,
  player_one VARCHAR(10) NOT NULL,
  player_two VARCHAR(10) NOT NULL,
  player_winner VARCHAR(10) NOT NULL,
  score VARCHAR(10) NOT NULL,
  phase SMALLINT NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  CONSTRAINT pk_identifier PRIMARY KEY (identifier),
  CONSTRAINT fk_player_one FOREIGN KEY (player_one)
  REFERENCES championship.players(document) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT fk_player_two FOREIGN KEY (player_two)
  REFERENCES championship.players(document) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT fk_player_winner FOREIGN KEY (player_winner)
  REFERENCES championship.players(document) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT fk_championships_id FOREIGN KEY (championship_id)
  REFERENCES championship.championships(identifier) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);
