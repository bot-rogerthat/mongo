-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
	id bigint NOT NULL DEFAULT nextval('"User_id_seq"'::regclass),
	name character varying(255),
	email character varying(255),
	day_of_birth timestamp without time zone,
	image bytea,
	base64 character varying(255),
	CONSTRAINT pk_user_id PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;
