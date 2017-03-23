CREATE TABLE "Categorie" (
    id integer NOT NULL,
    nom character varying(255) NOT NULL
);


CREATE TABLE "Emprunt" (
    id integer NOT NULL,
    id_materiel integer NOT NULL,
    id_emprunteur integer NOT NULL,
    "dateEmprunt" date NOT NULL,
    "heureEmprunt" time with time zone NOT NULL,
    "dateRetour" date NOT NULL,
    "heureRetour" time with time zone NOT NULL,
    "etatEmprunt" character varying(255) NOT NULL,
    "etatRetour" character varying(255)
);

CREATE TABLE "Materiel" (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    reference character varying(255) NOT NULL,
    id_rangement character varying(255) NOT NULL,
    photo character varying(255),
    etat character varying(255) NOT NULL,
    description character varying(255),
    id_categorie integer NOT NULL,
    id_plateforme integer NOT NULL,
    id_position integer NOT NULL
);

CREATE TABLE "Personne" (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    tel character varying(255) NOT NULL
);

CREATE TABLE "Plateforme" (
    id integer NOT NULL
);

CREATE TABLE "Position" (
    id integer NOT NULL,
    date date,
    longitude character varying(255),
    lattitude character varying(255),
    id_materiel integer
);

CREATE TABLE "Souscategorie" (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    id_categorie integer NOT NULL
);
