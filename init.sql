--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-24 17:09:32

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET SESSION AUTHORIZATION 'Library_Api';

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 19529)
-- Name: book; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.book (
    author_id integer NOT NULL,
    editor_id integer NOT NULL,
    format character varying(20),
    isbn character varying(20) NOT NULL,
    pages integer,
    summary character varying(2048),
    type character varying(20),
    media_id integer NOT NULL
);


--
-- TOC entry 213 (class 1259 OID 19539)
-- Name: borrowing; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.borrowing (
    id integer NOT NULL,
    borrowing_date date,
    extended integer,
    media_id integer,
    return_date timestamp without time zone,
    user_id integer
);


--
-- TOC entry 212 (class 1259 OID 19537)
-- Name: borrowing_id_seq; Type: SEQUENCE; Schema: public; Owner: Library_Api
--

CREATE SEQUENCE public.borrowing_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 212
-- Name: borrowing_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Library_Api
--

ALTER SEQUENCE public.borrowing_id_seq OWNED BY public.borrowing.id;


--
-- TOC entry 214 (class 1259 OID 19545)
-- Name: game; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.game (
    editor_id integer,
    format character varying(20),
    pegi character varying(4),
    summary character varying(2048),
    type character varying(20),
    url character varying(255),
    media_id integer NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 19555)
-- Name: media; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.media (
    id integer NOT NULL,
    ean character varying(20) NOT NULL,
    height integer,
    length integer,
    media_type character varying(10) NOT NULL,
    publication_date date,
    remaining integer,
    return_date date,
    stock integer,
    title character varying(50) NOT NULL,
    weight integer,
    width integer
);


--
-- TOC entry 215 (class 1259 OID 19553)
-- Name: media_id_seq; Type: SEQUENCE; Schema: public; Owner: Library_Api
--

CREATE SEQUENCE public.media_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 215
-- Name: media_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Library_Api
--

ALTER SEQUENCE public.media_id_seq OWNED BY public.media.id;


--
-- TOC entry 217 (class 1259 OID 19561)
-- Name: music; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.music (
    author_id integer,
    composer_id integer,
    duration integer,
    format character varying(20),
    interpreter_id integer,
    type character varying(20),
    url character varying(255),
    media_id integer NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 19568)
-- Name: person; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.person (
    id integer NOT NULL,
    birth_date date,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    photo_url character varying(255),
    url character varying(255)
);


--
-- TOC entry 218 (class 1259 OID 19566)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: Library_Api
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 218
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Library_Api
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- TOC entry 207 (class 1259 OID 19493)
-- Name: role; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 19491)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: Library_Api
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 206
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Library_Api
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 209 (class 1259 OID 19501)
-- Name: users; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.users (
    id integer NOT NULL,
    city character varying(50) NOT NULL,
    counter integer,
    country character varying(50) DEFAULT 'FRANCE'::character varying NOT NULL,
    email character varying(255) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    phone character varying(14),
    photo_url character varying(255) DEFAULT NULL::character varying,
    status character varying(10),
    street1 character varying(50) NOT NULL,
    street2 character varying(50),
    street3 character varying(50),
    zip_code character varying(6) NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 19499)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Library_Api
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 208
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Library_Api
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 210 (class 1259 OID 19512)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 19577)
-- Name: video; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.video (
    audience character varying(20),
    audio character varying(255),
    director_id integer NOT NULL,
    duration integer,
    format character varying(20),
    image character varying(255),
    summary character varying(2048),
    type character varying(20),
    url character varying(255),
    media_id integer NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 19585)
-- Name: video_actors; Type: TABLE; Schema: public; Owner: Library_Api
--

CREATE TABLE public.video_actors (
    video_id integer NOT NULL,
    actor_id integer NOT NULL
);


--
-- TOC entry 2748 (class 2604 OID 19542)
-- Name: borrowing id; Type: DEFAULT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.borrowing ALTER COLUMN id SET DEFAULT nextval('public.borrowing_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 19558)
-- Name: media id; Type: DEFAULT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.media ALTER COLUMN id SET DEFAULT nextval('public.media_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 19571)
-- Name: person id; Type: DEFAULT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- TOC entry 2744 (class 2604 OID 19496)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 19504)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2916 (class 0 OID 19529)
-- Dependencies: 211
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (1, 11, 'POCKET', '9782253004226', 538, 'Voici, dans la France moderne et industrielle, les " Misérables " de Zola. Ce roman des mineurs, c''est aussi l''Enfer, dans un monde dantesque, où l''on " voyage au bout de la nuit ". Mais à la fin du prodigieux itinéraire au centre de la terre, du fond du souterrain où il a vécu si longtemps écrasé, l''homme enfin se redresse et surgit dans une révolte pleine d''espoirs. C''est la plus belle et la plus grande œuvre de Zola, le poème de la fraternité dans la misère, et le roman de la condition humaine.', 'NOVEL', 1);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (1, 11, 'POCKET', '9782253002864', 270, 'Octave Mouret affole les femmes de désir. Son grand magasin parisien, Au Bonheur des Dames, est un paradis pour les sens. Les tissus s’amoncellent, éblouissants, délicats. Tout ce qu’une femme peut acheter en 1883, Octave Mouret le vend, avec des techniques révolutionnaires. Le succès est immense. Mais ce bazar est une catastrophe pour le quartier, les petits commerces meurent, les spéculations immobilières se multiplient. Et le personnel connaît une vie d’enfer. Denise échoue de Valognes dans cette fournaise, démunie mais tenace. Zola fait de la jeune fille et de son puissant patron amoureux d’elle le symbole du modernisme et des crises qu’il suscite. Personne ne pourra plus entrer dans un grand magasin sans ressentir ce que Zola raconte avec génie : les fourmillements de la vie.', 'NOVEL', 2);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (1, 11, 'POCKET', '9782253003656', 500, 'Dans les dernières années du Second Empire, quand Nana joue le rôle de Vénus au Théâtre des Variétés, son succès tient moins à son médiocre talent d’actrice qu’à la séduction de son corps nu, voilé d’une simple gaze. Elle aimante sur scène tous les regards comme elle attire chez elle tous les hommes : tentatrice solaire qui use de ses charmes pour mener une vie de luxure et de luxe, de paresse et de dépense. Grâce à elle, c’est tout un monde que le romancier parvient à évoquer, toute une époque et tout un style de vie. Ce neuvième volume des Rougon-Macquart est une satire cinglante des hautes sphères perverties par une fête qui ruine le peuple et détruit les valeurs.', 'NOVEL', 3);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (2, 11, 'POCKET', '9782253010692', 668, 'Un jeune provincial de dix-huit ans, plein de rêves et plutôt séduisant, vient faire ses études à Paris. De 1840 au soir du coup d’Etat de 1851, il fait l’apprentissage du monde dans une société en pleine convulsion. Sur son chemin, il rencontre le grand amour et les contingences du plaisir, la Révolution et ses faux apôtres, l’art, la puissance de l’argent et de la bêtise, la réversibilité des croyances, l’amitié fraternelle et la fatalité des trahisons, sans parvenir à s’engager pour une autre cause que celle de suivre la perte de ses illusions. Ecrit dans une langue éblouissante et selon des règles narratives inédites, L’Education sentimentale, publiée en 1869, est peut-être le chef-d’œuvre de Flaubert le plus abouti et le plus mystérieux. En cherchant à représenter l’essence même du temps vécu, l’auteur nous transmet une philosophie de l’histoire, une morale de l’existence et une esthétique de la mémoire qui restent d’une surprenante acuité pour élucider les énigmes d’aujourd’hui.', 'NOVEL', 4);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (2, 12, 'POCKET', '9782070413119', 541, 'C''est l''histoire d''une femme mal mariée, de son médiocre époux, de ses amants égoïstes et vains, de ses rêves, de ses chimères, de sa mort. C''est l''histoire d''une province étroite, dévote et bourgeoise. C''est, aussi, l''histoire du roman français. Rien, dans ce tableau, n''avait de quoi choquer la société du Second Empire. Mais, inexorable comme une tragédie, flamboyant comme un drame, mordant comme une comédie, le livre s''était donné une arme redoutable : le style. Pour ce vrai crime, Flaubert se retrouva en correctionnelle.Aucun roman n''est innocent : celui-là moins qu''un autre. Lire Madame Bovary, au XXIE siècle, c''est affronter le scandale que représente une œuvre aussi sincère qu''impérieuse. Dans chacune de ses phrases, Flaubert a versé une dose de cet arsenic dont Emma Bovary s''empoisonne : c''est un livre offensif, corrosif, dont l''ironie outrage toutes nos valeurs, et la littérature même, qui ne s''en est jamais vraiment remise.', 'NOVEL', 5);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (3, 13, 'POCKET', '9782253096337', 982, 'La bataille de Waterloo, Paris, les barricades, les bagnes et les usines… Fantine, Cosette, Jean Valjean, Gavroche, les Thénardier… Les événements, les lieux et les héros les plus célèbres de toute la littérature française dans un roman d’aventures, de passion et de haine, de vengeance et de pardon, tout à tour tragique et drôle, violent et sentimental, historique et légendaire, noir et poétique. Le chef-d’œuvre de Victor Hugo, mille fois adapté et traduit, à découvrir dans sa version originale.', 'NOVEL', 6);
INSERT INTO public.book (author_id, editor_id, format, isbn, pages, summary, type, media_id) VALUES (3, 13, 'POCKET', '9782253096344', 455, 'Les Misérables sont un étourdissant rappel à l''ordre d''une société trop amoureuse d''elle-même et trop peu soucieuse de l''immortelle loi de fraternité, un plaidoyer pour les Misérables (ceux qui souffrent de la misère et que la misère déshonore), proféré par la bouche la plus éloquente de ce temps. Le nouveau livre de Victor Hugo doit être le Bienvenu (comme l''évêque dont il raconte la victorieuse charité), le livre à applaudir, le livre à remercier. N''est-il pas utile que de temps à autre le poète, le philosophe prennent un peu le Bonheur égoïste aux cheveux, et lui disent, en lui secouant le mufle dans le sang et l''ordure : « Vois ton oeuvre et bois ton oeuvre » ? Charles Baudelaire.', 'NOVEL', 7);


--
-- TOC entry 2918 (class 0 OID 19539)
-- Dependencies: 213
-- Data for Name: borrowing; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (1, '2020-04-10', 0, 1, NULL, 1);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (2, '2020-04-15', 0, 1, NULL, 2);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (3, '2020-04-20', 3, 1, NULL, 3);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (4, '2020-04-10', 0, 2, NULL, 1);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (5, '2020-04-15', 2, 2, NULL, 2);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (6, '2020-04-20', 0, 2, NULL, 3);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (7, '2020-04-10', 0, 3, NULL, 1);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (8, '2020-04-15', 0, 3, NULL, 2);
INSERT INTO public.borrowing (id, borrowing_date, extended, media_id, return_date, user_id) VALUES (9, '2020-04-20', 0, 8, NULL, 3);


--
-- TOC entry 2919 (class 0 OID 19545)
-- Dependencies: 214
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.game (editor_id, format, pegi, summary, type, url, media_id) VALUES (16, 'SONY_PS3', '16+', 'Pilotez le jour et risquez tout la nuit dans Need for Speed™ Heat, une expérience palpitante qui vous met au défi d’intégrer l’élite de la course urbaine face à de redoutables policiers corrompus. Le jour, participez au Speedhunter Showdown, une compétition officielle où vous gagnerez de quoi personnaliser et améliorer les voitures performantes contenues dans votre garage. Une fois votre bagnole relookée et gonflée à bloc, et que vous vous sentez d’attaque pour vivre des moments intenses, affrontez d’autres pilotes la nuit, avec votre crew, lors de courses illégales grâce auxquelles vous vous taillerez une réputation et vous accéderez à de meilleures pièces et à des courses plus relevées. Mais sous couvert de patrouilles nocturnes, des flics corrompus veulent vous arrêter et confisquer tout ce que vous aurez gagné. Prenez des risques et devenez encore plus célèbre en leur tenant tête, ou rentrez à votre planque pour vivre une nouvelle journée de courses. Courses, risques, voitures : ici, les limites n’existent pas. Votre crew prend les mêmes risques que vous, votre garage déborde de belles voitures, et la ville est votre terrain de jeu, 24 heures sur 24.', 'COURSE', 'https://www.youtube.com/embed/p4Q3uh2RaZo', 13);
INSERT INTO public.game (editor_id, format, pegi, summary, type, url, media_id) VALUES (17, 'PC', '3+', 'Partez à la conquête des cieux dans Flight Simulator 2004 : Un Siècle d''Aviation sur PC. En plus des vols d''appareils classiques cet opus vous permet de voler avec des légendes de l''aviation, les premiers avions existants et de recréer leurs vols en temps réel.', 'SIMULATION', 'https://www.youtube.com/embed/myMYQeBqKLw', 14);


--
-- TOC entry 2921 (class 0 OID 19555)
-- Dependencies: 216
-- Data for Name: media; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (1, '978-2253004226', NULL, NULL, 'BOOK', '1971-11-01', 1, NULL, 4, 'Germinal', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (2, '978-2253002864', NULL, NULL, 'BOOK', '1971-10-01', 1, NULL, 4, 'Au bonheur des dames', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (3, '978-2253003656', NULL, NULL, 'BOOK', '1967-01-01', 1, NULL, 1, 'Nana', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (4, '978-2253010692', NULL, NULL, 'BOOK', '1972-03-01', 1, NULL, 1, 'L''éducation sentimentale', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (5, '978-2070413119', NULL, NULL, 'BOOK', '2001-05-16', 2, NULL, 5, 'Madame Bovary', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (6, '978-2253096337', NULL, NULL, 'BOOK', '1998-12-02', 1, NULL, 2, 'Les Misérables (Tome 1)', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (7, '978-2253096344', NULL, NULL, 'BOOK', '1998-12-02', 1, NULL, 2, 'Les Misérables (Tome 2)', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (8, '3475001058980', NULL, NULL, 'VIDEO', '2019-12-04', 2, NULL, 2, 'Parasite', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (9, '8809634380036', NULL, NULL, 'MUSIC', '2019-05-24', 1, NULL, 2, 'Kill This Love', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (10, '4988064587100', NULL, NULL, 'MUSIC', '2018-08-22', 1, NULL, 1, 'DDU-DU DDU-DU', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (11, '4988064585816', NULL, NULL, 'MUSIC', '2018-04-16', 1, NULL, 1, 'RE BLACKPINK', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (12, '8809269506764', NULL, NULL, 'MUSIC', '2017-09-15', 1, NULL, 1, 'MADE', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (13, '5035223122470', NULL, NULL, 'GAME', '2019-11-08', 1, NULL, 1, 'NFS Need for Speed™ Heat', NULL, NULL);
INSERT INTO public.media (id, ean, height, length, media_type, publication_date, remaining, return_date, stock, title, weight, width) VALUES (14, '0805529340299', NULL, NULL, 'GAME', '2003-08-29', 1, NULL, 1, 'Flight Simulator 2004 : Un Siècle d''Aviation', NULL, NULL);


--
-- TOC entry 2922 (class 0 OID 19561)
-- Dependencies: 217
-- Data for Name: music; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.music (author_id, composer_id, duration, format, interpreter_id, type, url, media_id) VALUES (14, 14, NULL, 'CD', 14, 'POP', 'https://www.youtube.com/embed/2S24-y0Ij3Y', 9);
INSERT INTO public.music (author_id, composer_id, duration, format, interpreter_id, type, url, media_id) VALUES (14, 14, NULL, 'CD', 14, 'POP', 'https://www.youtube.com/embed/IHNzOHi8sJs', 10);
INSERT INTO public.music (author_id, composer_id, duration, format, interpreter_id, type, url, media_id) VALUES (14, 14, NULL, 'CD', 14, 'POP', 'https://www.youtube.com/embed/qIQI8aoYPeQ', 11);
INSERT INTO public.music (author_id, composer_id, duration, format, interpreter_id, type, url, media_id) VALUES (15, 15, NULL, 'CD', 15, 'POP', 'https://www.youtube.com/embed/LNIQ57mxvGA&list=OLAK5uy_nmV9t7lKxIMIdQc7zCPgSYK1jI5AXQwnI&index=3', 12);


--
-- TOC entry 2924 (class 0 OID 19568)
-- Dependencies: 219
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (1, '1840-04-02', 'Emile', 'ZOLA', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Emile_Zola_1902.jpg/800px-Emile_Zola_1902.jpg', 'https://fr.wikipedia.org/wiki/Émile_Zola');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (2, '1821-12-12', 'Gustave', 'FLAUBERT', 'https://upload.wikimedia.org/wikipedia/commons/c/c6/Gustave_flaubert.jpg', 'https://fr.wikipedia.org/wiki/Gustave_Flaubert');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (3, '1802-02-26', 'Victor', 'HUGO', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Victor_Hugo_001.jpg/800px-Victor_Hugo_001.jpg', 'https://fr.wikipedia.org/wiki/Victor_Hugo');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (4, '1969-09-14', 'Joon-Ho', 'BONG', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Bong_Joon-ho_2017.jpg/800px-Bong_Joon-ho_2017.jpg', 'https://fr.wikipedia.org/wiki/Bong_Joon-ho');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (5, '1975-03-02', 'Sun-Kyun', 'LEE', 'https://upload.wikimedia.org/wikipedia/commons/4/44/161026_%EC%9D%B4%EC%84%A0%EA%B7%A0.jpg', 'https://fr.wikipedia.org/wiki/Lee_Sun-kyun');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (6, '1967-01-17', 'Kang-Ho', 'SONG', 'https://upload.wikimedia.org/wikipedia/commons/d/df/Song_Gangho_2016.jpg', 'https://fr.wikipedia.org/wiki/Song_Kang-ho');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (7, '1981-02-10', 'Yeo-Jeong', 'CHO', 'https://upload.wikimedia.org/wikipedia/commons/9/92/Cho_Yeo-jung_in_Dec_2019_%28Revised%29.png', 'https://fr.wikipedia.org/wiki/Cho_Yeo-jeong');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (8, '1986-03-26', 'Woo-Shik', 'CHOI', 'https://upload.wikimedia.org/wikipedia/commons/0/0f/Choi_U-shik_in_2018.jpg', 'https://fr.wikipedia.org/wiki/Choi_Woo-sik');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (9, '1991-09-08', 'So-Dam', 'PARK', 'https://upload.wikimedia.org/wikipedia/commons/4/44/Park_So-dam%2C_2015_%28cropped%29.jpg', 'https://fr.wikipedia.org/wiki/Park_So-dam');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (11, NULL, 'LGF', 'Librairie Générale Française', NULL, NULL);
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (12, NULL, 'Gallimard', 'Gallimard', NULL, 'http://www.gallimard.fr');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (13, NULL, 'Larousse', 'Larousse', NULL, 'https://www.larousse.fr/');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (14, '2016-06-01', 'Blackpink', 'Blackpink', 'https://pbs.twimg.com/media/EDYuf3PW4AEShXL?format=jpg&name=small', 'https://fr.wikipedia.org/wiki/Blackpink');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (15, '2006-08-19', 'BigBang', 'BigBang', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/BIGBANG_Extraordinary_20%27s.JPG/260px-BIGBANG_Extraordinary_20%27s.JPG', 'https://fr.wikipedia.org/wiki/BigBang_(groupe)');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (16, '1982-05-28', 'EA', 'Electronic Arts', NULL, 'https://www.ea.com/fr-fr');
INSERT INTO public.person (id, birth_date, firstname, lastname, photo_url, url) VALUES (17, '1976-11-26', 'Microsoft', 'Microsoft', NULL, 'https://www.microsoft.com/fr-fr');


--
-- TOC entry 2912 (class 0 OID 19493)
-- Dependencies: 207
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_STAFF');
INSERT INTO public.role (id, name) VALUES (3, 'ROLE_USER');


--
-- TOC entry 2914 (class 0 OID 19501)
-- Dependencies: 209
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.users (id, city, counter, country, email, firstname, lastname, password, phone, photo_url, status, street1, street2, street3, zip_code) VALUES (1, 'Paris', 3, 'FRANCE', 'admin@library.org', 'Admin', 'ADMIN', '$2a$10$iyH.Uiv1Rx67gBdEXIabqOHPzxBsfpjmC0zM9JMs6i4tU0ymvZZie', NULL, NULL, 'MEMBER', '22, rue de la Paix', NULL, NULL, '75111');
INSERT INTO public.users (id, city, counter, country, email, firstname, lastname, password, phone, photo_url, status, street1, street2, street3, zip_code) VALUES (2, 'Strasbourg', 3, 'FRANCE', 'staff@library.org', 'Staff', 'STAFF', '$2a$10$F14GUY0VFEuF0JepK/vQc.6w3vWGDbMJh0/Ji/hU2ujKLjvQzkGGG', '0324593874', NULL, 'MEMBER', '1, rue verte', NULL, NULL, '68121');
INSERT INTO public.users (id, city, counter, country, email, firstname, lastname, password, phone, photo_url, status, street1, street2, street3, zip_code) VALUES (3, 'Besançon', 3, 'FRANCE', 'martin.dupont@gmail.com', 'Martin', 'DUPONT', '$2a$10$PPVu0M.IdSTD.GwxbV6xZ.cP3EqlZRozxwrXkSF.fFUeweCaCQaSS', '0324593874', NULL, 'MEMBER', '3, chemin de l’Escale', NULL, NULL, '25000');


--
-- TOC entry 2915 (class 0 OID 19512)
-- Dependencies: 210
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 3);


--
-- TOC entry 2925 (class 0 OID 19577)
-- Dependencies: 220
-- Data for Name: video; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.video (audience, audio, director_id, duration, format, image, summary, type, url, media_id) VALUES ('Accord parental', 'Coréen DTS HD (Master audio) 5.1, Français DTS HD (Master audio) 5.1', 4, 132, 'BLU_RAY', 'HD 1080p 16:9 (1920x1080 progressif)', 'Toute la famille de Ki-taek est au chômage, et s’inte´resse fortement au train de vie de la richissime famille Park. Un jour, leur fils réussit à` se faire recommander pour donner des cours particuliers d’anglais chez les Park. C’est le début d’un engrenage incontrôlable, dont personne ne sortira véritablement indemne...', 'THRILLER', 'https://www.youtube.com/embed/-Yo_lxZ6Z0k', 8);


--
-- TOC entry 2926 (class 0 OID 19585)
-- Dependencies: 221
-- Data for Name: video_actors; Type: TABLE DATA; Schema: public; Owner: Library_Api
--

INSERT INTO public.video_actors (video_id, actor_id) VALUES (8, 5);
INSERT INTO public.video_actors (video_id, actor_id) VALUES (8, 6);
INSERT INTO public.video_actors (video_id, actor_id) VALUES (8, 7);
INSERT INTO public.video_actors (video_id, actor_id) VALUES (8, 8);
INSERT INTO public.video_actors (video_id, actor_id) VALUES (8, 9);


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 212
-- Name: borrowing_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Library_Api
--

SELECT pg_catalog.setval('public.borrowing_id_seq', 10, false);


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 215
-- Name: media_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Library_Api
--

SELECT pg_catalog.setval('public.media_id_seq', 15, false);


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 218
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Library_Api
--

SELECT pg_catalog.setval('public.person_id_seq', 18, false);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 206
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Library_Api
--

SELECT pg_catalog.setval('public.role_id_seq', 4, false);


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 208
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Library_Api
--

SELECT pg_catalog.setval('public.users_id_seq', 4, false);


--
-- TOC entry 2760 (class 2606 OID 19536)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (media_id);


--
-- TOC entry 2762 (class 2606 OID 19544)
-- Name: borrowing borrowing_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.borrowing
    ADD CONSTRAINT borrowing_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 19552)
-- Name: game game_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_pkey PRIMARY KEY (media_id);


--
-- TOC entry 2766 (class 2606 OID 19560)
-- Name: media media_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 19565)
-- Name: music music_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.music
    ADD CONSTRAINT music_pkey PRIMARY KEY (media_id);


--
-- TOC entry 2770 (class 2606 OID 19576)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 19498)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 19518)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2774 (class 2606 OID 19591)
-- Name: video_actors uk_i4aanecj9aceo9qkbamcv4cq9; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video_actors
    ADD CONSTRAINT uk_i4aanecj9aceo9qkbamcv4cq9 UNIQUE (actor_id);


--
-- TOC entry 2756 (class 2606 OID 19511)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2758 (class 2606 OID 19516)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2776 (class 2606 OID 19589)
-- Name: video_actors video_actors_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video_actors
    ADD CONSTRAINT video_actors_pkey PRIMARY KEY (video_id, actor_id);


--
-- TOC entry 2772 (class 2606 OID 19584)
-- Name: video video_pkey; Type: CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video
    ADD CONSTRAINT video_pkey PRIMARY KEY (media_id);


--
-- TOC entry 2778 (class 2606 OID 19524)
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2781 (class 2606 OID 19602)
-- Name: music fkcwj1s2gws5ugksuy73vpy0mss; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.music
    ADD CONSTRAINT fkcwj1s2gws5ugksuy73vpy0mss FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 2783 (class 2606 OID 19612)
-- Name: video_actors fkgcs2hwb81u36kinltoy9p95na; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video_actors
    ADD CONSTRAINT fkgcs2hwb81u36kinltoy9p95na FOREIGN KEY (actor_id) REFERENCES public.person(id);


--
-- TOC entry 2782 (class 2606 OID 19607)
-- Name: video fklfeo5bddw2mx8aar1clv6qg54; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video
    ADD CONSTRAINT fklfeo5bddw2mx8aar1clv6qg54 FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 2780 (class 2606 OID 19597)
-- Name: game fkmjl9gyti5fx3ntkpdkmv0nln7; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT fkmjl9gyti5fx3ntkpdkmv0nln7 FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 2779 (class 2606 OID 19592)
-- Name: book fkqeecsufk5g94uc2pyr48i2qmg; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkqeecsufk5g94uc2pyr48i2qmg FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 2784 (class 2606 OID 19617)
-- Name: video_actors fkqlv4f8i0f5hrlll253kudd0g1; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.video_actors
    ADD CONSTRAINT fkqlv4f8i0f5hrlll253kudd0g1 FOREIGN KEY (video_id) REFERENCES public.video(media_id);


--
-- TOC entry 2777 (class 2606 OID 19519)
-- Name: users_roles fkt4v0rrweyk393bdgt107vdx0x; Type: FK CONSTRAINT; Schema: public; Owner: Library_Api
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkt4v0rrweyk393bdgt107vdx0x FOREIGN KEY (role_id) REFERENCES public.role(id);


-- Completed on 2020-04-24 17:09:34

--
-- PostgreSQL database dump complete
--

