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
CREATE TABLE auditing.schedule_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  begin_hour character varying(255),
  end_hour character varying(255),
  week_day integer,
  classroom_id bigint,
  CONSTRAINT schedule_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_schedule_audited_revision FOREIGN KEY (revision)
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
CREATE TABLE auditing.matriculation_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  matriculation_date timestamp without time zone,
  matriculation_number bigint,
  number_of_installment bigint,
  payment_date timestamp without time zone,
  status integer,
  ticket numeric(19,2),
  value numeric(19,2),
  classroom_id bigint,
  student_id bigint,
  CONSTRAINT matriculation_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_matriculation_audited_revision FOREIGN KEY (revision)
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
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(144) NOT NULL,
  name character varying(60) NOT NULL,
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
  status integer NOT NULL,
  course_id bigint NOT NULL,
  CONSTRAINT classroom_pkey PRIMARY KEY (id),
  CONSTRAINT fk_classroom_course_id FOREIGN KEY (course_id)
      REFERENCES course (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE "public"."schedule"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  begin_hour character varying(255) NOT NULL,
  end_hour character varying(255) NOT NULL,
  week_day integer NOT NULL,
  classroom_id bigint NOT NULL,
  CONSTRAINT schedule_pkey PRIMARY KEY (id),
  CONSTRAINT fk_schedule_classroom_id FOREIGN KEY (classroom_id)
      REFERENCES classroom (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE "public"."discipline"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  description character varying(144) NOT NULL,
  name character varying(60) NOT NULL,
  course_id bigint NOT NULL,
  CONSTRAINT discipline_pkey PRIMARY KEY (id),
  CONSTRAINT fk_discipline_course_id FOREIGN KEY (course_id)
      REFERENCES course (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE "public"."matriculation"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  matriculation_date timestamp without time zone,
  matriculation_number bigint NOT NULL,
  number_of_installment bigint NOT NULL,
  payment_date timestamp without time zone,
  status integer NOT NULL,
  ticket numeric(19,2) NOT NULL,
  value numeric(19,2) NOT NULL,
  classroom_id bigint,
  student_id bigint,
  CONSTRAINT matriculation_pkey PRIMARY KEY (id),
  CONSTRAINT fk_matriculation_classroom_id FOREIGN KEY (classroom_id)
      REFERENCES classroom (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_matriculation_student_id FOREIGN KEY (student_id)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
