--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE emprunt (
    cle integer NOT NULL,
    cle_equipement integer NOT NULL,
    cle_personne integer NOT NULL,
    date_emprunt date DEFAULT now() NOT NULL,
    date_retour_prevue date NOT NULL,
    date_retour date,
    etat_emprunt integer NOT NULL,
    etat_retour integer NOT NULL,
    CONSTRAINT chk_date_retour CHECK ((date_retour >= date_emprunt)),
    CONSTRAINT chk_date_retour_prevue CHECK ((date_retour_prevue >= date_emprunt))
);


ALTER TABLE public.emprunt OWNER TO silifl;

--
-- Name: emprunt_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE emprunt_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunt_cle_seq OWNER TO silifl;

--
-- Name: emprunt_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE emprunt_cle_seq OWNED BY emprunt.cle;


--
-- Name: equipement; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE equipement (
    cle integer NOT NULL,
    nom character varying NOT NULL,
    cle_rangement integer NOT NULL,
    photo character varying,
    cle_etat integer NOT NULL,
    description character varying,
    cle_sous_categorie integer NOT NULL,
    cle_infrastructure integer NOT NULL
);


ALTER TABLE public.equipement OWNER TO silifl;

--
-- Name: equipement_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE equipement_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.equipement_cle_seq OWNER TO silifl;

--
-- Name: equipement_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE equipement_cle_seq OWNED BY equipement.cle;


--
-- Name: infrastructure; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE infrastructure (
    cle integer NOT NULL,
    nom character varying NOT NULL,
    cle_plateforme integer NOT NULL
);


ALTER TABLE public.infrastructure OWNER TO silifl;

--
-- Name: infrastructure_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE infrastructure_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.infrastructure_cle_seq OWNER TO silifl;

--
-- Name: infrastructure_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE infrastructure_cle_seq OWNED BY infrastructure.cle;


--
-- Name: n_categories; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE n_categories (
    cle integer NOT NULL,
    nom character varying NOT NULL
);


ALTER TABLE public.n_categories OWNER TO silifl;

--
-- Name: n_categories_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE n_categories_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.n_categories_cle_seq OWNER TO silifl;

--
-- Name: n_categories_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE n_categories_cle_seq OWNED BY n_categories.cle;


--
-- Name: n_etats; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE n_etats (
    cle integer NOT NULL,
    nom character varying NOT NULL
);


ALTER TABLE public.n_etats OWNER TO silifl;

--
-- Name: n_etats_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE n_etats_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.n_etats_cle_seq OWNER TO silifl;

--
-- Name: n_etats_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE n_etats_cle_seq OWNED BY n_etats.cle;


--
-- Name: n_sous_categories; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE n_sous_categories (
    cle integer NOT NULL,
    nom character varying NOT NULL,
    cle_categorie integer NOT NULL
);


ALTER TABLE public.n_sous_categories OWNER TO silifl;

--
-- Name: n_sous_categorie_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE n_sous_categorie_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.n_sous_categorie_cle_seq OWNER TO silifl;

--
-- Name: n_sous_categorie_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE n_sous_categorie_cle_seq OWNED BY n_sous_categories.cle;


--
-- Name: n_types_references; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE n_types_references (
    cle integer NOT NULL,
    nom character varying NOT NULL
);


ALTER TABLE public.n_types_references OWNER TO silifl;

--
-- Name: n_types_references_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE n_types_references_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.n_types_references_cle_seq OWNER TO silifl;

--
-- Name: n_types_references_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE n_types_references_cle_seq OWNED BY n_types_references.cle;


--
-- Name: position; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE "position" (
    cle integer NOT NULL,
    date date DEFAULT now() NOT NULL,
    longitude real NOT NULL,
    latitude real NOT NULL,
    cle_equipement integer NOT NULL
);


ALTER TABLE public."position" OWNER TO silifl;

--
-- Name: position_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE position_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.position_cle_seq OWNER TO silifl;

--
-- Name: position_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE position_cle_seq OWNED BY "position".cle;


--
-- Name: r_equ_ref; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE r_equ_ref (
    cle integer NOT NULL,
    cle_equipement integer NOT NULL,
    cle_reference integer NOT NULL
);


ALTER TABLE public.r_equ_ref OWNER TO silifl;

--
-- Name: r_equ_ref_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE r_equ_ref_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.r_equ_ref_cle_seq OWNER TO silifl;

--
-- Name: r_equ_ref_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE r_equ_ref_cle_seq OWNED BY r_equ_ref.cle;


--
-- Name: reference; Type: TABLE; Schema: public; Owner: silifl; Tablespace: 
--

CREATE TABLE reference (
    cle integer NOT NULL,
    intitule character varying NOT NULL,
    cle_type_reference integer NOT NULL
);


ALTER TABLE public.reference OWNER TO silifl;

--
-- Name: reference_cle_seq; Type: SEQUENCE; Schema: public; Owner: silifl
--

CREATE SEQUENCE reference_cle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reference_cle_seq OWNER TO silifl;

--
-- Name: reference_cle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: silifl
--

ALTER SEQUENCE reference_cle_seq OWNED BY reference.cle;


--
-- Name: seq_n_etat; Type: SEQUENCE; Schema: public; Owner: flexinuser
--

CREATE SEQUENCE seq_n_etat
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_n_etat OWNER TO flexinuser;

--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY emprunt ALTER COLUMN cle SET DEFAULT nextval('emprunt_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY equipement ALTER COLUMN cle SET DEFAULT nextval('equipement_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY infrastructure ALTER COLUMN cle SET DEFAULT nextval('infrastructure_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY n_categories ALTER COLUMN cle SET DEFAULT nextval('n_categories_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY n_etats ALTER COLUMN cle SET DEFAULT nextval('n_etats_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY n_sous_categories ALTER COLUMN cle SET DEFAULT nextval('n_sous_categorie_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY n_types_references ALTER COLUMN cle SET DEFAULT nextval('n_types_references_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY "position" ALTER COLUMN cle SET DEFAULT nextval('position_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY r_equ_ref ALTER COLUMN cle SET DEFAULT nextval('r_equ_ref_cle_seq'::regclass);


--
-- Name: cle; Type: DEFAULT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY reference ALTER COLUMN cle SET DEFAULT nextval('reference_cle_seq'::regclass);


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY emprunt (cle, cle_equipement, cle_personne, date_emprunt, date_retour_prevue, date_retour, etat_emprunt, etat_retour) FROM stdin;
1	1	1	2017-06-07	2017-06-27	\N	1	1
\.


--
-- Name: emprunt_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('emprunt_cle_seq', 1, true);


--
-- Data for Name: equipement; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY equipement (cle, nom, cle_rangement, photo, cle_etat, description, cle_sous_categorie, cle_infrastructure) FROM stdin;
1	sqdqd	0	photos/startrek.jpeg	1	\N	1	2
\.


--
-- Name: equipement_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('equipement_cle_seq', 1, true);


--
-- Data for Name: infrastructure; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY infrastructure (cle, nom, cle_plateforme) FROM stdin;
1	STRAIN	0
2	CREEP	0
\.


--
-- Name: infrastructure_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('infrastructure_cle_seq', 2, true);


--
-- Data for Name: n_categories; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY n_categories (cle, nom) FROM stdin;
1	Sensors
2	Computers
\.


--
-- Name: n_categories_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('n_categories_cle_seq', 2, true);


--
-- Data for Name: n_etats; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY n_etats (cle, nom) FROM stdin;
1	Broken
2	Maintenance
3	Running
\.


--
-- Name: n_etats_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('n_etats_cle_seq', 3, true);


--
-- Name: n_sous_categorie_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('n_sous_categorie_cle_seq', 5, true);


--
-- Data for Name: n_sous_categories; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY n_sous_categories (cle, nom, cle_categorie) FROM stdin;
1	Lidar	1
2	Camera	1
3	Radar	1
4	Raspberry	2
5	Arduino	2
\.


--
-- Data for Name: n_types_references; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY n_types_references (cle, nom) FROM stdin;
1	Barcode
2	Serial Number
3	NFC
\.


--
-- Name: n_types_references_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('n_types_references_cle_seq', 3, true);


--
-- Data for Name: position; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY "position" (cle, date, longitude, latitude, cle_equipement) FROM stdin;
\.


--
-- Name: position_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('position_cle_seq', 1, false);


--
-- Data for Name: r_equ_ref; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY r_equ_ref (cle, cle_equipement, cle_reference) FROM stdin;
\.


--
-- Name: r_equ_ref_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('r_equ_ref_cle_seq', 1, false);


--
-- Data for Name: reference; Type: TABLE DATA; Schema: public; Owner: silifl
--

COPY reference (cle, intitule, cle_type_reference) FROM stdin;
\.


--
-- Name: reference_cle_seq; Type: SEQUENCE SET; Schema: public; Owner: silifl
--

SELECT pg_catalog.setval('reference_cle_seq', 1, false);


--
-- Name: seq_n_etat; Type: SEQUENCE SET; Schema: public; Owner: flexinuser
--

SELECT pg_catalog.setval('seq_n_etat', 1, false);


--
-- Name: emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (cle);


--
-- Name: equipement_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY equipement
    ADD CONSTRAINT equipement_pkey PRIMARY KEY (cle);


--
-- Name: etats_unique_nom; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_etats
    ADD CONSTRAINT etats_unique_nom UNIQUE (nom);


--
-- Name: infrastructure_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY infrastructure
    ADD CONSTRAINT infrastructure_pkey PRIMARY KEY (cle);


--
-- Name: n_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_categories
    ADD CONSTRAINT n_categories_pkey PRIMARY KEY (cle);


--
-- Name: n_etats_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_etats
    ADD CONSTRAINT n_etats_pkey PRIMARY KEY (cle);


--
-- Name: n_sous_categorie_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_sous_categories
    ADD CONSTRAINT n_sous_categorie_pkey PRIMARY KEY (cle);


--
-- Name: n_types_references_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_types_references
    ADD CONSTRAINT n_types_references_pkey PRIMARY KEY (cle);


--
-- Name: position_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY "position"
    ADD CONSTRAINT position_pkey PRIMARY KEY (cle);


--
-- Name: r_equ_ref_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY r_equ_ref
    ADD CONSTRAINT r_equ_ref_pkey PRIMARY KEY (cle);


--
-- Name: reference_pkey; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY reference
    ADD CONSTRAINT reference_pkey PRIMARY KEY (cle);


--
-- Name: unique_nom; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_categories
    ADD CONSTRAINT unique_nom UNIQUE (nom);


--
-- Name: unique_sous_categorie_nom; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_sous_categories
    ADD CONSTRAINT unique_sous_categorie_nom UNIQUE (nom);


--
-- Name: unique_type_refreence; Type: CONSTRAINT; Schema: public; Owner: silifl; Tablespace: 
--

ALTER TABLE ONLY n_types_references
    ADD CONSTRAINT unique_type_refreence UNIQUE (nom);


--
-- Name: equipement_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX equipement_cle_key ON equipement USING btree (cle);


--
-- Name: infrastructure_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX infrastructure_cle_key ON infrastructure USING btree (cle);


--
-- Name: n_categories_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX n_categories_cle_key ON n_categories USING btree (cle);


--
-- Name: n_etats_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX n_etats_cle_key ON n_etats USING btree (cle);


--
-- Name: n_sous_categories_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX n_sous_categories_cle_key ON n_sous_categories USING btree (cle);


--
-- Name: n_types_references_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX n_types_references_cle_key ON n_types_references USING btree (cle);


--
-- Name: reference_cle_key; Type: INDEX; Schema: public; Owner: silifl; Tablespace: 
--

CREATE UNIQUE INDEX reference_cle_key ON reference USING btree (cle);


--
-- Name: fk_cle_categorie; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY n_sous_categories
    ADD CONSTRAINT fk_cle_categorie FOREIGN KEY (cle_categorie) REFERENCES n_categories(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_equipement; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY emprunt
    ADD CONSTRAINT fk_cle_equipement FOREIGN KEY (cle_equipement) REFERENCES equipement(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_equipement; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY "position"
    ADD CONSTRAINT fk_cle_equipement FOREIGN KEY (cle_equipement) REFERENCES equipement(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_equipement; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY r_equ_ref
    ADD CONSTRAINT fk_cle_equipement FOREIGN KEY (cle_equipement) REFERENCES equipement(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_etat; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY equipement
    ADD CONSTRAINT fk_cle_etat FOREIGN KEY (cle_etat) REFERENCES n_etats(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_infrastructure; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY equipement
    ADD CONSTRAINT fk_cle_infrastructure FOREIGN KEY (cle_infrastructure) REFERENCES infrastructure(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_reference; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY r_equ_ref
    ADD CONSTRAINT fk_cle_reference FOREIGN KEY (cle_reference) REFERENCES reference(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_sous_categorie; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY equipement
    ADD CONSTRAINT fk_cle_sous_categorie FOREIGN KEY (cle_sous_categorie) REFERENCES n_sous_categories(cle) ON DELETE RESTRICT;


--
-- Name: fk_cle_type_reference; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY reference
    ADD CONSTRAINT fk_cle_type_reference FOREIGN KEY (cle_type_reference) REFERENCES n_types_references(cle) ON DELETE RESTRICT;


--
-- Name: fk_etat_emprunt; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY emprunt
    ADD CONSTRAINT fk_etat_emprunt FOREIGN KEY (etat_emprunt) REFERENCES n_etats(cle) ON DELETE RESTRICT;


--
-- Name: fk_etat_retour; Type: FK CONSTRAINT; Schema: public; Owner: silifl
--

ALTER TABLE ONLY emprunt
    ADD CONSTRAINT fk_etat_retour FOREIGN KEY (etat_retour) REFERENCES n_etats(cle) ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

