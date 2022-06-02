--Add extension to generate random uuid as sequences to primary keys automatically
CREATE EXTENSION IF NOT EXISTS pgcrypto;

--Drop tables in case they existed
DROP TABLE IF EXISTS public.user CASCADE;
DROP TABLE IF EXISTS public.role CASCADE;
DROP TABLE IF EXISTS public.userrole CASCADE;

-- Table: public.user
CREATE TABLE IF NOT EXISTS public.user
(
    id character varying(36) NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(25) NOT NULL,
    active boolean NOT NULL,
	creation_date TIMESTAMP,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user
ALTER COLUMN active
SET DEFAULT TRUE;

ALTER TABLE public.user
    OWNER to postgres;
	
-- Table: public.role
CREATE TABLE IF NOT EXISTS public.role
(
    id character varying(36) NOT NULL,
    role character varying(25) NOT NULL,
	creation_date TIMESTAMP,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;
	
-- Table: public.userrole
CREATE TABLE IF NOT EXISTS public.userrole
(
    user_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL,
    CONSTRAINT userroles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT rolefk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT userfk FOREIGN KEY (user_id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.userrole
    OWNER to postgres;