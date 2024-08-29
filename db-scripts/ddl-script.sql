BEGIN;

    CREATE TABLE IF NOT EXISTS public.users
    (
        id bigserial NOT NULL,
        username character varying NOT NULL,
        password character varying NOT NULL,
        contact_email character varying NOT NULL,
        contact_phone character varying,
        role character varying NOT NULL,
        created_at timestamp without time zone NOT NULL,
        updated_at timestamp without time zone NOT NULL,
        CONSTRAINT user_pk PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS public.club
    (
        id bigserial NOT NULL,
        name character varying NOT NULL,
        description character varying NOT NULL,
        address character varying NOT NULL,
        contact_email character varying NOT NULL,
        contact_phone character varying NOT NULL,
        created_at timestamp without time zone NOT NULL,
        updated_at timestamp without time zone NOT NULL,
        CONSTRAINT club_pk PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS public.court
    (
        id bigserial NOT NULL,
        name character varying NOT NULL,
        surface character varying,
        court_environment character varying NOT NULL,
        club_id bigint,
        created_at timestamp without time zone NOT NULL,
        updated_at timestamp without time zone NOT NULL,
        CONSTRAINT court_pk PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS public.reservation
    (
        id bigserial NOT NULL,
        user_id bigint,
        court_id bigint,
        reservation_start_time timestamp without time zone NOT NULL,
        reservation_end_time timestamp without time zone NOT NULL,
        status character varying NOT NULL,
        created_at timestamp without time zone NOT NULL,
        updated_at timestamp without time zone NOT NULL,
        CONSTRAINT reservation_pk PRIMARY KEY (id)
    );

    ALTER TABLE IF EXISTS public.court
        ADD CONSTRAINT fk_club_id FOREIGN KEY (club_id)
        REFERENCES public.club (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


    ALTER TABLE IF EXISTS public.reservation
        ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;
    CREATE INDEX IF NOT EXISTS fki_fk_user_id
        ON public.reservation(user_id);


    ALTER TABLE IF EXISTS public.reservation
        ADD CONSTRAINT fk_court_id FOREIGN KEY (court_id)
        REFERENCES public.court (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;
    CREATE INDEX IF NOT EXISTS fki_fk_court_id
        ON public.reservation(court_id);

END;