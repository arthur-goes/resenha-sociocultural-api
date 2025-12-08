INSERT INTO youths
    (first_name, surname, birth_date, cpf, mother_name, father_name, emergency_contact_name, emergency_contact_description, emergency_contact_phone, creation_date, active)
VALUES
    ('John', 'Smith', '2005-03-15', '68367216032', 'Mary Smith', 'Joseph Smith', 'Mary Smith', 'Mother', '11987654321', '2025-12-07', true),
    ('Anne', 'Johnson', '2006-07-20', '88250153057', 'Joanne Johnson', 'Charles Johnson', 'Charles Johnson', 'Father', '11987654322', '2025-12-07', true),
    ('Luke', 'Williams', '2004-11-01', '67685100029', 'Frances Williams', 'Richard Williams', 'Richard Williams', 'Father', '11987654323', '2025-12-07', true),
    ('Marianne', 'Brown', '2007-01-30', '45623066020', 'Patricia Brown', 'Mark Brown', 'Patricia Brown', 'Mother', '11987654324', '2025-12-07', true),
    ('Peter', 'Jones', '2005-09-10', '41024411079', 'Adriana Jones', 'Paul Jones', 'Adriana Jones', 'Mother', '11987654325', '2025-12-07', false),
    ('Beatrice', 'Garcia', '2006-05-25', '93100355016', 'Sandra Garcia', 'Robert Garcia', 'Robert Garcia', 'Father', '11987654326', '2025-12-07', true),
    ('William', 'Miller', '2004-02-12', '69877889013', 'Christine Miller', 'Anthony Miller', 'Anthony Miller', 'Father', '11987654327', '2025-12-07', true),
    ('Larissa', 'Davis', '2007-08-08', '85993981059', 'Elaine Davis', 'Sergio Davis', 'Elaine Davis', 'Mother', '11987654328', '2025-12-07', true),
    ('Raphael', 'Rodriguez', '2005-12-18', '51302210017', 'Deborah Rodriguez', 'Ferdinand Rodriguez', 'Deborah Rodriguez', 'Mother', '11987654329', '2025-12-07', true),
    ('Julia', 'Martinez', '2006-10-05', '31588928047', 'Vanessa Martinez', 'Leander Martinez', 'Leander Martinez', 'Father', '11987654330', '2025-12-07', false);

INSERT INTO meetings
    (date, minutos_de_sabedoria_lesson, theme)
VALUES
    ('2025-10-29', 'Message 88', 'Self-improvement'),
    ('2025-11-05', 'Message 127', 'Religions'),
    ('2025-11-12', 'Message 37', 'Personal and future planning'),
    ('2025-11-19', 'Message 20', 'Fame'),
    ('2025-11-26', 'Message 54', 'Willpower'),
    ('2025-12-03', 'Message 100', 'Own your sucess');

INSERT INTO attendances
    (absence_excuse, attendance_status, meeting_id, youth_id)
VALUES
    --MEETING 1
    (NULL, 'PRESENT', 1, 1),
    (NULL, 'PRESENT', 1, 2),
    (NULL, 'PRESENT', 1, 3),
    (NULL, 'ABSENT', 1, 4),
    (NULL, 'PRESENT', 1, 5),
    (NULL, 'PRESENT', 1, 6),
    (NULL, 'PRESENT', 1, 7),
    (NULL, 'PRESENT', 1, 8),
    (NULL, 'PRESENT', 1, 9),
    ('Mother''s birthday', 'EXCUSED_ABSENCE', 1, 10),

    --MEETING 2
    (NULL, 'PRESENT', 2, 1),
    (NULL, 'ABSENT', 2, 2),
    (NULL, 'PRESENT', 2, 3),
    (NULL, 'PRESENT', 2, 4),
    (NULL, 'PRESENT', 2, 5),
    (NULL, 'PRESENT', 2, 6),
    (NULL, 'PRESENT', 2, 7),
    (NULL, 'ABSENT', 2, 8),
    (NULL, 'PRESENT', 2, 9),
    (NULL, 'PRESENT', 2, 10),

    --MEETING 3
    (NULL, 'ABSENT', 3, 1),
    (NULL, 'ABSENT', 3, 2),
    (NULL, 'PRESENT', 3, 3),
    (NULL, 'PRESENT', 3, 4),
    (NULL, 'PRESENT', 3, 5),
    (NULL, 'PRESENT', 3, 6),
    (NULL, 'PRESENT', 3, 7),
    (NULL, 'PRESENT', 3, 8),
    (NULL, 'PRESENT', 3, 9),
    (NULL, 'ABSENT', 3, 10),

    --MEETING 4
    (NULL, 'PRESENT', 4, 1),
    (NULL, 'ABSENT', 4, 2),
    (NULL, 'ABSENT', 4, 3),
    (NULL, 'PRESENT', 4, 4),
    (NULL, 'PRESENT', 4, 5),
    ('Sickness', 'EXCUSED_ABSENCE', 4, 6),
    (NULL, 'PRESENT', 4, 7),
    (NULL, 'PRESENT', 4, 8),
    (NULL, 'PRESENT', 4, 9),
    (NULL, 'PRESENT', 4, 10),

    --MEETING 5
    (NULL, 'PRESENT', 5, 1),
    (NULL, 'PRESENT', 5, 2),
    (NULL, 'ABSENT', 5, 3),
    (NULL, 'PRESENT', 5, 4),
    (NULL, 'PRESENT', 5, 5),
    (NULL, 'PRESENT', 5, 6),
    (NULL, 'ABSENT', 5, 7),
    (NULL, 'PRESENT', 5, 8),
    (NULL, 'PRESENT', 5, 9),
    (NULL, 'ABSENT', 5, 10),

    --MEETING 6
    ('School event', 'EXCUSED_ABSENCE', 6, 1),
    (NULL, 'PRESENT', 6, 2),
    (NULL, 'PRESENT', 6, 3),
    (NULL, 'PRESENT', 6, 4),
    (NULL, 'PRESENT', 6, 5),
    (NULL, 'PRESENT', 6, 6),
    (NULL, 'PRESENT', 6, 7),
    (NULL, 'PRESENT', 6, 8),
    (NULL, 'ABSENT', 6, 9),
    (NULL, 'PRESENT', 6, 10);

INSERT INTO strikes
    (amount, reason, active, meeting_id, youth_id)
VALUES
    --MEETING 1
    (1, 'Late arrival', TRUE, 1, 2),
    (1, 'Disrupted the meeting', TRUE, 1, 8),

    --MEETING 2
    (1, 'Offended a colleague', TRUE, 2, 3),
    (1, 'Late arrival', TRUE, 2, 7),
    (1, 'Late arrival', TRUE, 2, 9),

    --MEETING 3
    (1, 'Late arrival', TRUE, 3, 3),
    (1, 'Late arrival', TRUE, 3, 6),

    --MEETING 4
    (1, 'Late arrival', TRUE, 4, 1),

    --MEETING 5
    (1, 'Late arrival', TRUE, 5, 6),
    (1, 'Disrespected the instructor', TRUE, 5, 8),
    (1, 'Late arrival', TRUE, 5, 9),

    --MEETING 6
    (1, 'Late arrival', TRUE, 6, 2);

INSERT INTO participation_points
    (amount, reason, active, meeting_id, youth_id)
VALUES
    --MEETING 1
    (1, 'Attendance', TRUE, 1, 1),
    (1, 'Attendance', TRUE, 1, 2),
    (1, 'Attendance', TRUE, 1, 3),
    (1, 'Attendance', TRUE, 1, 5),
    (1, 'Attendance', TRUE, 1, 6),
    (1, 'Attendance', TRUE, 1, 7),
    (1, 'Attendance', TRUE, 1, 8),
    (1, 'Attendance', TRUE, 1, 9),
    (1, 'Minutos de Sabedoria reading', TRUE, 1, 3),

    --MEETING 2
    (1, 'Attendance', TRUE, 2, 1),
    (1, 'Attendance', TRUE, 2, 3),
    (1, 'Attendance', TRUE, 2, 4),
    (1, 'Attendance', TRUE, 2, 5),
    (1, 'Attendance', TRUE, 2, 6),
    (1, 'Attendance', TRUE, 2, 7),
    (1, 'Attendance', TRUE, 2, 9),
    (1, 'Attendance', TRUE, 2, 10),
    (1, 'Minutos de Sabedoria reading', TRUE, 2, 9),

    --MEETING 3
    (1, 'Attendance', TRUE, 3, 3),
    (1, 'Attendance', TRUE, 3, 4),
    (1, 'Attendance', TRUE, 3, 5),
    (1, 'Attendance', TRUE, 3, 6),
    (1, 'Attendance', TRUE, 3, 7),
    (1, 'Attendance', TRUE, 3, 8),
    (1, 'Attendance', TRUE, 3, 9),
    (1, 'Minutos de Sabedoria reading', TRUE, 3, 6),

    --MEETING 4
    (1, 'Attendance', TRUE, 4, 1),
    (1, 'Attendance', TRUE, 4, 4),
    (1, 'Attendance', TRUE, 4, 5),
    (1, 'Attendance', TRUE, 4, 7),
    (1, 'Attendance', TRUE, 4, 8),
    (1, 'Attendance', TRUE, 4, 9),
    (1, 'Attendance', TRUE, 4, 10),
    (1, 'Minutos de Sabedoria reading', TRUE, 4, 1),

    --MEETING 5
    (1, 'Attendance', TRUE, 5, 1),
    (1, 'Attendance', TRUE, 5, 2),
    (1, 'Attendance', TRUE, 5, 4),
    (1, 'Attendance', TRUE, 5, 5),
    (1, 'Attendance', TRUE, 5, 6),
    (1, 'Attendance', TRUE, 5, 8),
    (1, 'Attendance', TRUE, 5, 9),
    (1, 'Minutos de Sabedoria reading', TRUE, 5, 2),

    --MEETING 6
    (1, 'Attendance', TRUE, 6, 2),
    (1, 'Attendance', TRUE, 6, 3),
    (1, 'Attendance', TRUE, 6, 4),
    (1, 'Attendance', TRUE, 6, 5),
    (1, 'Attendance', TRUE, 6, 6),
    (1, 'Attendance', TRUE, 6, 7),
    (1, 'Attendance', TRUE, 6, 8),
    (1, 'Attendance', TRUE, 6, 10),
    (1, 'Minutos de Sabedoria reading', TRUE, 6, 10);
