SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

CREATE SCHEMA IF NOT EXISTS "public";
CREATE SCHEMA IF NOT EXISTS "auditing";
CREATE EXTENSION IF NOT EXISTS UNACCENT SCHEMA PG_CATALOG;

----------------------- 
-- AUDITING STRUCTURE
-----------------------
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.revision
(
  id bigserial NOT NULL,
  "timestamp" bigint NOT NULL,
  user_id bigint,
  CONSTRAINT revision_pkey PRIMARY KEY (id)
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.country_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(50),
  CONSTRAINT country_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_country_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.state_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(300),
  country_id bigint,
  CONSTRAINT state_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_state_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.city_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(300),
  state_id bigint,
  CONSTRAINT city_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_city_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.address_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  cep character varying(144),
  neighborhood character varying(144),
  "number" smallint,
  street character varying(144),
  city_id bigint,
  CONSTRAINT address_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_address_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.responsible_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(50),
  cpf character varying(16),
  rg character varying(25),
  birth_date timestamp without time zone,
  phone character varying(19),
  CONSTRAINT responsible_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_responsible_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.user_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  email character varying(144),
  enabled boolean,
  father_name character varying(255),
  last_login timestamp without time zone,
  mother_name character varying(255),
  name character varying(50),
  password character varying(100),
  role integer,
  phone character varying(19),
  birth_date timestamp without time zone,
  cpf character varying(16),
  rg character varying(25),
  address_id bigint,
  responsible_id bigint,
  CONSTRAINT user_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_user_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -

CREATE TABLE auditing.bank_account_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  balance numeric(19,2),
  description character varying(500),
  name character varying(50),
  CONSTRAINT bank_account_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_bank_account_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.category_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(50),
  CONSTRAINT category_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_category_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.supplier_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  cnpj character varying(20),
  company_name character varying(144),
  contact character varying(144),
  phone character varying(15),
  trade_name character varying(144),
  address_id bigint,
  CONSTRAINT supplier_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_supplier_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.account_payable_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  description character varying(500),
  due_date timestamp without time zone,
  entry_date timestamp without time zone,
  payment_date timestamp without time zone,
  status integer,
  value numeric(19,2),
  bank_account_id bigint,
  category_id bigint,
  supplier_id bigint,
  CONSTRAINT account_payable_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_account_payable_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
CREATE TABLE auditing.account_receivable_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  description character varying(500),
  due_date timestamp without time zone,
  entry_date timestamp without time zone,
  receivement_date timestamp without time zone,
  status integer,
  value numeric(19,2),
  bank_account_id bigint,
  category_id bigint,
  student_id bigint,
  CONSTRAINT account_receivable_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_account_receivable_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
----------------------- 
-- PUBLIC STRUCTURE
-----------------------
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."country"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(50) NOT NULL,
  CONSTRAINT country_pkey PRIMARY KEY (id)
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."state"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(300) NOT NULL,
  country_id bigint NOT NULL,
  CONSTRAINT state_pkey PRIMARY KEY (id),
  CONSTRAINT fk_state_country_id FOREIGN KEY (country_id)
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."city"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(300) NOT NULL,
  state_id bigint NOT NULL,
  CONSTRAINT city_pkey PRIMARY KEY (id),
  CONSTRAINT fk_city_state_id FOREIGN KEY (state_id)
      REFERENCES state (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."address"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  cep character varying(144),
  neighborhood character varying(144) NOT NULL,
  street character varying(144) NOT NULL,
  city_id bigint NOT NULL,
  "number" smallint,
  CONSTRAINT address_pkey PRIMARY KEY (id),
  CONSTRAINT fk_address_city_id FOREIGN KEY (city_id)
      REFERENCES city (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -

CREATE TABLE "public"."responsible"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(50) NOT NULL,
  cpf character varying(16),
  rg character varying(25),
  birth_date timestamp without time zone NOT NULL,
  phone character varying(19) NOT NULL,
  CONSTRAINT responsible_pkey PRIMARY KEY (id)
);


--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--
CREATE TABLE "public"."user"
(
 id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  email character varying(144) NOT NULL,
  enabled boolean NOT NULL,
  father_name character varying(255),
  last_login timestamp without time zone,
  mother_name character varying(255) ,
  name character varying(50) NOT NULL,
  password character varying(100) NOT NULL,
  role integer NOT NULL,
  phone character varying(19),
  birth_date timestamp without time zone,
  cpf character varying(16),
  rg character varying(25),
  address_id bigint,
  responsible_id bigint,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT fk_user_address_id FOREIGN KEY (address_id)
      REFERENCES address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user_responsible_id FOREIGN KEY (responsible_id)
      REFERENCES responsible (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_user_email UNIQUE (email)
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -

CREATE TABLE "public"."bank_account"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  balance numeric(19,2) NOT NULL,
  description character varying(500) NOT NULL,
  name character varying(50) NOT NULL,
  CONSTRAINT bank_account_pkey PRIMARY KEY (id)
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."category"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(50) NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (id)
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."supplier"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  cnpj character varying(20),
  company_name character varying(144) NOT NULL,
  contact character varying(144),
  phone character varying(15),
  trade_name character varying(144) NOT NULL,
  address_id bigint ,
  CONSTRAINT supplier_pkey PRIMARY KEY (id)
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
CREATE TABLE "public"."account_payable"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(500) NOT NULL,
  due_date timestamp without time zone NOT NULL,
  entry_date timestamp without time zone NOT NULL,
  payment_date timestamp without time zone,
  status integer,
  value numeric(19,2) NOT NULL,
  bank_account_id bigint NOT NULL,
  category_id bigint NOT NULL,
  supplier_id bigint,
  CONSTRAINT account_payable_pkey PRIMARY KEY (id),
  CONSTRAINT fk_account_payable_bank_account_id FOREIGN KEY (bank_account_id)
      REFERENCES bank_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_account_payable_category_id FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_account_payable_supplier_id FOREIGN KEY (supplier_id)
      REFERENCES supplier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -

CREATE TABLE "public"."account_receivable"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(500) NOT NULL,
  due_date timestamp without time zone NOT NULL,
  entry_date timestamp without time zone NOT NULL,
  receivement_date timestamp without time zone,
  status integer,
  value numeric(19,2) NOT NULL,
  bank_account_id bigint NOT NULL,
  category_id bigint NOT NULL,
  student_id bigint,
  CONSTRAINT account_receivable_pkey PRIMARY KEY (id),
  CONSTRAINT fk_account_receivable_bank_account_id FOREIGN KEY (bank_account_id)
      REFERENCES bank_account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_account_payable_category_id FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_accouns_receivable_student_id FOREIGN KEY (student_id)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
