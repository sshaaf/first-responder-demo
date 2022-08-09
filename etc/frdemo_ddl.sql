--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

-- Started on 2022-08-09 17:35:23 CEST

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

--
-- TOC entry 216 (class 1259 OID 16518)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: frdemo
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO frdemo;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 213 (class 1259 OID 16501)
-- Name: mission_step; Type: TABLE; Schema: public; Owner: frdemo
--

CREATE TABLE public.mission_step (
                                     id bigint NOT NULL,
                                     destination boolean,
                                     latitude numeric(19,2),
                                     longitude numeric(19,2),
                                     way_point boolean,
                                     mission_id character varying(255)
);


ALTER TABLE public.mission_step OWNER TO frdemo;

--
-- TOC entry 214 (class 1259 OID 16506)
-- Name: mission_table; Type: TABLE; Schema: public; Owner: frdemo
--

CREATE TABLE public.mission_table (
                                      mission_id character varying(255) NOT NULL,
                                      destination_latitude numeric(7,5),
                                      destination_longitude numeric(7,5),
                                      incident_id character varying(255),
                                      incident_latitude numeric(7,5),
                                      incident_longitude numeric(7,5),
                                      responder_id character varying(255),
                                      responder_start_latitude numeric(7,5),
                                      responder_start_longitude numeric(7,5),
                                      mission_status character varying(255)
);


ALTER TABLE public.mission_table OWNER TO frdemo;

--
-- TOC entry 209 (class 1259 OID 16477)
-- Name: reported_incident; Type: TABLE; Schema: public; Owner: frdemo
--

CREATE TABLE public.reported_incident (
                                          id bigint NOT NULL,
                                          incident_id character varying(255),
                                          latitude character varying(255),
                                          longitude character varying(255),
                                          medical_needed boolean,
                                          number_of_people integer,
                                          incident_status character varying(255),
                                          victim_name character varying(255),
                                          victim_phone character varying(255),
                                          reported_time timestamp(3) without time zone,
                                          version bigint DEFAULT 0
);


ALTER TABLE public.reported_incident OWNER TO frdemo;

--
-- TOC entry 210 (class 1259 OID 16483)
-- Name: reported_incident_seq; Type: SEQUENCE; Schema: public; Owner: frdemo
--

CREATE SEQUENCE public.reported_incident_seq
    START WITH 1
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reported_incident_seq OWNER TO frdemo;

--
-- TOC entry 215 (class 1259 OID 16513)
-- Name: responder_location_history; Type: TABLE; Schema: public; Owner: frdemo
--

CREATE TABLE public.responder_location_history (
                                                   id bigint NOT NULL,
                                                   latitude numeric(19,2),
                                                   longitude numeric(19,2),
                                                   time_stamp bigint,
                                                   mission_id character varying(255)
);


ALTER TABLE public.responder_location_history OWNER TO frdemo;

--
-- TOC entry 211 (class 1259 OID 16484)
-- Name: responder_table; Type: TABLE; Schema: public; Owner: frdemo
--

CREATE TABLE public.responder_table (
                                        responder_id bigint NOT NULL,
                                        responder_name text,
                                        responder_phone_number text,
                                        responder_current_gps_lat numeric(7,5),
                                        responder_current_gps_long numeric(8,5),
                                        boat_capacity integer,
                                        has_medical_kit boolean,
                                        available boolean DEFAULT true,
                                        person boolean DEFAULT false,
                                        enrolled boolean DEFAULT true,
                                        version bigint DEFAULT 0
);


ALTER TABLE public.responder_table OWNER TO frdemo;

--
-- TOC entry 212 (class 1259 OID 16493)
-- Name: responder_table_sequence; Type: SEQUENCE; Schema: public; Owner: frdemo
--

CREATE SEQUENCE public.responder_table_sequence
    START WITH 1
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.responder_table_sequence OWNER TO frdemo;

--
-- TOC entry 4362 (class 0 OID 0)
-- Dependencies: 212
-- Name: responder_table_sequence; Type: SEQUENCE OWNED BY; Schema: public; Owner: frdemo
--

ALTER SEQUENCE public.responder_table_sequence OWNED BY public.responder_table.responder_id;


--
-- TOC entry 4195 (class 2604 OID 16494)
-- Name: responder_table responder_id; Type: DEFAULT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.responder_table ALTER COLUMN responder_id SET DEFAULT nextval('public.responder_table_sequence'::regclass);


--
-- TOC entry 4363 (class 0 OID 0)
-- Dependencies: 216
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: frdemo
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 4364 (class 0 OID 0)
-- Dependencies: 210
-- Name: reported_incident_seq; Type: SEQUENCE SET; Schema: public; Owner: frdemo
--

SELECT pg_catalog.setval('public.reported_incident_seq', 1, false);


--
-- TOC entry 4365 (class 0 OID 0)
-- Dependencies: 212
-- Name: responder_table_sequence; Type: SEQUENCE SET; Schema: public; Owner: frdemo
--

SELECT pg_catalog.setval('public.responder_table_sequence', 11, true);


--
-- TOC entry 4203 (class 2606 OID 16505)
-- Name: mission_step mission_step_pkey; Type: CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.mission_step
    ADD CONSTRAINT mission_step_pkey PRIMARY KEY (id);


--
-- TOC entry 4205 (class 2606 OID 16512)
-- Name: mission_table mission_table_pkey; Type: CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.mission_table
    ADD CONSTRAINT mission_table_pkey PRIMARY KEY (mission_id);


--
-- TOC entry 4199 (class 2606 OID 16498)
-- Name: reported_incident reported_incident_pkey; Type: CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.reported_incident
    ADD CONSTRAINT reported_incident_pkey PRIMARY KEY (id);


--
-- TOC entry 4207 (class 2606 OID 16517)
-- Name: responder_location_history responder_location_history_pkey; Type: CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.responder_location_history
    ADD CONSTRAINT responder_location_history_pkey PRIMARY KEY (id);


--
-- TOC entry 4201 (class 2606 OID 16496)
-- Name: responder_table responder_pkey; Type: CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.responder_table
    ADD CONSTRAINT responder_pkey PRIMARY KEY (responder_id);


--
-- TOC entry 4196 (class 1259 OID 16499)
-- Name: idx_reported_incident_incident_id; Type: INDEX; Schema: public; Owner: frdemo
--

CREATE INDEX idx_reported_incident_incident_id ON public.reported_incident USING btree (incident_id);


--
-- TOC entry 4197 (class 1259 OID 16500)
-- Name: idx_reported_incident_victim_name; Type: INDEX; Schema: public; Owner: frdemo
--

CREATE INDEX idx_reported_incident_victim_name ON public.reported_incident USING btree (victim_name);


--
-- TOC entry 4209 (class 2606 OID 16524)
-- Name: responder_location_history fkaulq4yv1ckv446bc9d07n3wj7; Type: FK CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.responder_location_history
    ADD CONSTRAINT fkaulq4yv1ckv446bc9d07n3wj7 FOREIGN KEY (mission_id) REFERENCES public.mission_table(mission_id);


--
-- TOC entry 4208 (class 2606 OID 16519)
-- Name: mission_step fkt60cpytuxsomyd6nmre5sa85t; Type: FK CONSTRAINT; Schema: public; Owner: frdemo
--

ALTER TABLE ONLY public.mission_step
    ADD CONSTRAINT fkt60cpytuxsomyd6nmre5sa85t FOREIGN KEY (mission_id) REFERENCES public.mission_table(mission_id);


-- Completed on 2022-08-09 17:35:23 CEST

--
-- PostgreSQL database dump complete
--
