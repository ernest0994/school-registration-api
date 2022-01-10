CREATE SCHEMA IF NOT EXISTS school_db;
CREATE TABLE student
(
    id               BIGINT auto_increment,
    first_name       VARCHAR(50),
    last_name        VARCHAR(50),
    email            VARCHAR(255),
    created_at       TIMESTAMP,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE course
(
    id               BIGINT auto_increment,
    description      VARCHAR(255),
    mnemonic         VARCHAR(50),
    created_at       TIMESTAMP,
    last_modified_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE student_course
(
    student_id BIGINT NOT NULL,
    course_id  BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

INSERT INTO student
VALUES (1, 'Anish', 'Visrolia', 'anish.visrolia@metadata.io', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO student
VALUES (2, 'Emily', 'Hoang', 'emily.hoang@metadata.io', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO student
VALUES (3, 'Jaime', 'Rojas', 'jaime.rojas@metadata.io', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO student
VALUES (4, 'Francisco', 'Martin', 'francisco.martin@metadata.io', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO student
VALUES (5, 'Ernesto', 'Acosta', 'ernestox09@gmail.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO course
VALUES (1, 'CSS 101', 'CSS101', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (2, 'Class 1', 'CL101', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (3, 'Class 2', 'CL201', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (4, 'Class 3', 'CL301', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (5, 'Class 4', 'CL401', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (6, 'Class 5', 'CL501', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (7, 'Class 6', 'CL601', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (8, 'Class 7', 'CL701', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO course
VALUES (9, 'Class 8', 'CL801', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO student_course
VALUES (1, 2);
INSERT INTO student_course
VALUES (2, 3);
INSERT INTO student_course
VALUES (3, 1);
INSERT INTO student_course
VALUES (4, 4);