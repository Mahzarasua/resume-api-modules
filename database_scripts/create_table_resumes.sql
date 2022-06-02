--Drop tables in case they existed
DROP TABLE IF EXISTS public.resume CASCADE;
DROP TABLE IF EXISTS public.skill CASCADE;
DROP TABLE IF EXISTS public.school CASCADE;
DROP TABLE IF EXISTS public.work_experience CASCADE;

-- Table: public.resume
CREATE TABLE IF NOT EXISTS public.resume
(
    id character varying(36) NOT NULL,
    first_name character varying(50) NOT NULL,
	last_name character varying(50) NOT NULL,
    title character varying(30) NOT NULL,
	city character varying(20) NOT NULL,
	state character varying(20) NOT NULL,
	country character varying(20) NOT NULL,
	email character varying(100) NOT NULL,
	phone character varying(25) NOT NULL,
	summary character varying(300) NOT NULL,
	creation_date TIMESTAMP,
    CONSTRAINT resume_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.resume
    OWNER to postgres;

-- Table: public.skill
CREATE TABLE IF NOT EXISTS public.skill
(
    id character varying(36) NOT NULL,
	resume_id character varying(36) NOT NULL,
    name character varying(50) NOT NULL,
    percentage integer NOT NULL,
	type character varying(25) NOT NULL,
    CONSTRAINT skills_pkey PRIMARY KEY (resume_id, id),
	CONSTRAINT resume_skill_fk FOREIGN KEY (resume_id)
        REFERENCES public.resume (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.skill
    OWNER to postgres;

-- Table: public.work_experience
CREATE TABLE IF NOT EXISTS public.work_experience
(
    id character varying(36) NOT NULL,
	resume_id character varying(36) NOT NULL,
    title character VARYING(50) NOT NULL,
	company CHARACTER VARYING(75) NOT NULL,
	start_date DATE,
	end_date DATE,
	current_job BOOLEAN,
	description CHARACTER VARYING(400) NOT NULL,
    CONSTRAINT workexp_pkey PRIMARY KEY (resume_id, id),
	CONSTRAINT resume_workexp_fk FOREIGN KEY (resume_id)
        REFERENCES public.resume (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.work_experience
    OWNER to postgres;

-- Table: public.school
CREATE TABLE IF NOT EXISTS public.school
(
    id character varying(36) NOT NULL,
	resume_id character varying(36) NOT NULL,
    name CHARACTER VARYING(75) NOT NULL,
	career CHARACTER VARYING(75) not null,
	start_date date,
	end_date date,
	degree CHARACTER VARYING(25) not null,
    CONSTRAINT schools_pkey PRIMARY KEY (resume_id, id),
	CONSTRAINT resume_school_fk FOREIGN KEY (resume_id)
        REFERENCES public.resume (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.school
    OWNER to postgres;