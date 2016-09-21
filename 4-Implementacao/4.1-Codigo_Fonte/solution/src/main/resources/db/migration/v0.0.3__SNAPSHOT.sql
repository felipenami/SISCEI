----------------------- 
-- AUDITING STRUCTURE
-----------------------
--

CREATE TABLE auditing.course_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  description character varying(255),
  name character varying(255),
  CONSTRAINT course_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_course_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.classroom_audited
(
   id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  name character varying(60),
  schedule timestamp without time zone,
  status integer,
  course_id bigint,
  CONSTRAINT classroom_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_classroom_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.discipline_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  description character varying(144),
  name character varying(144),
  course_id bigint,
  CONSTRAINT discipline_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_discipline_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.student_audited
(
	dtype character varying(31) NOT NULL,
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  cpf character varying(16),
  rg character varying(25),
  birth_date timestamp without time zone,
  phone character varying(19),
  father_name character varying(255),
  mother_name character varying(255),
  address_id bigint,
  user_id bigint,
  CONSTRAINT student_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_student_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
----------------------- 
-- PUBLIC STRUCTURE
-----------------------
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE "public"."course"
(
  id bigint NOT NULL DEFAULT nextval('course_id_seq'::regclass),
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT course_pkey PRIMARY KEY (id)
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE "public"."classroom"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  name character varying(60) NOT NULL,
  schedule timestamp without time zone,
  status integer NOT NULL,
  course_id bigint,
  CONSTRAINT classroom_pkey PRIMARY KEY (id),
  CONSTRAINT fk_classroom_course_id FOREIGN KEY (course_id)
      REFERENCES course (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE "public"."discipline"
(
  id bigint NOT NULL DEFAULT nextval('discipline_id_seq'::regclass),
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(144) NOT NULL,
  name character varying(144) NOT NULL,
  course_id bigint NOT NULL,
  CONSTRAINT discipline_pkey PRIMARY KEY (id),
  CONSTRAINT fk_discipline_course_id FOREIGN KEY (course_id)
      REFERENCES public.course (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE "public"."student"
(
  dtype character varying(31) NOT NULL,
  id bigint NOT NULL DEFAULT nextval('student_id_seq'::regclass),
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  cpf character varying(16) NOT NULL,
  rg character varying(25) NOT NULL,
  birth_date timestamp without time zone NOT NULL,
  phone character varying(19) NOT NULL,
  father_name character varying(255) NOT NULL,
  mother_name character varying(255) NOT NULL,
  address_id bigint,
  user_id bigint,
  CONSTRAINT student_pkey PRIMARY KEY (id),
  CONSTRAINT fk_student_address_id FOREIGN KEY (address_id)
      REFERENCES public.address (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_student_user_id FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
