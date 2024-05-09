DO
$$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'student_compass') THEN
            CREATE DATABASE student_compass;
        END IF;
    END
$$;
