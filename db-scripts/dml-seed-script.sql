BEGIN;

    INSERT INTO users (username, password, contact_email, contact_phone, role, created_at, updated_at)
    VALUES
    ('john_doe', 'password123', 'john@example.com', '123-456-7890', 'ROLE_USER', NOW(), NOW()),
    ('jane_doe', 'password456', 'jane@example.com', '098-765-4321', 'ROLE_ADMIN', NOW(), NOW());


    INSERT INTO club (name, description, address, contact_email, contact_phone, created_at, updated_at)
    VALUES
    ('Sunshine Padel Club', 'A vibrant padel club with outdoor and indoor courts.', '123 Sunshine Ave', 'info@sunshinepadel.com', '123-456-7890', NOW(), NOW()),
    ('Moonlight Padel Club', 'An exclusive padel club with premium facilities.', '456 Moonlight St', 'contact@moonlightpadel.com', '098-765-4321', NOW(), NOW());


    INSERT INTO court (name, surface, court_environment, club_id, created_at, updated_at)
    VALUES
    ('Court 1', 'Artificial Grass', 'Outdoor', 1, NOW(), NOW()),
    ('Court 2', 'Concrete', 'Indoor', 1, NOW(), NOW()),
    ('Court 3', 'Clay', 'Outdoor', 2, NOW(), NOW()),
    ('Court 4', 'Artificial Grass', 'Indoor', 2, NOW(), NOW());


    INSERT INTO reservation (reservation_start_time, reservation_end_time, status, user_id, court_id, created_at, updated_at)
    VALUES
    ('2024-08-28 10:00:00', '2024-08-28 11:30:00', 'CONFIRMED', 1, 1, NOW(), NOW()),
    ('2024-08-29 14:00:00', '2024-08-29 15:00:00', 'CANCELLED', 2, 2, NOW(), NOW()),
    ('2024-08-30 09:00:00', '2024-08-30 10:30:00', 'PENDING', 1, 3, NOW(), NOW()),
    ('2024-08-31 18:00:00', '2024-08-31 19:30:00', 'CONFIRMED', 2, 4, NOW(), NOW());

END;