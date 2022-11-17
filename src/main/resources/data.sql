DROP TABLE IF EXISTS player;

CREATE TABLE IF NOT EXISTS public.player (
  --dispatch_id BIGSERIAL NOT NULL,
  document VARCHAR(10) NOT NULL,
  name VARCHAR(50) NOT NULL,
  skill INT NULL,
  gender CHARACTER VARYING(1),
  age INT NULL,
  CONSTRAINT pk_document PRIMARY KEY (document)
);

INSERT INTO public.player VALUES
('47082903','Alvaro Aguinaga',80,'M',30),
('47082909','Diego Aguinaga',70,'M',27),
('47082907','Gonzalo Aguinaga',90,'M',33);

--DROP TABLE IF EXISTS championship;
--
--CREATE TABLE IF NOT EXISTS public.championship (
--  --dispatch_id BIGSERIAL NOT NULL,
--  company_code CHARACTER VARYING(10) NOT NULL,
--  payment_order_id BIGINT NOT NULL,
--  state_id SMALLINT NULL,
--  dispatch_number VARCHAR(200),
--  delivery_date TIMESTAMP,
--  delivery_mode delivery_mode,
--  schedule_id SMALLINT NULL,
--  origin VARCHAR(20) NOT NULL,
--  origin_description CHARACTER VARYING(200),
--  origin_type entity_type,
--  destination VARCHAR(20),
--  destination_description CHARACTER VARYING(200),
--  route TEXT,--Ruta Integral del despacho
--  route_detail VARCHAR(200)[],--Detalle de la ruta integral
--  destination_type entity_type,
--  department VARCHAR(200),
--  province VARCHAR(200),
--  ubigeo_code VARCHAR(100),
--  way_type VARCHAR(200),
--  department_number VARCHAR(10),
--  floor VARCHAR(10),
--  address VARCHAR(200),
--  reference VARCHAR(255),
--  tracking_number SMALLINT NULL,
--  dispatch_amount DOUBLE PRECISION,
--  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
--  updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
--  active BOOLEAN DEFAULT TRUE,
--  CONSTRAINT pk_dispatch_id PRIMARY KEY (dispatch_id)
--);
