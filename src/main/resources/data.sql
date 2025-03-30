
-- DELETE FROM flight *;
-- DELETE
-- FROM airport *;

INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (1, 'TLL', 'Tallinna Lennujaam', 'Tallinn', 'Estonia');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (2, 'URE', 'Kuressaare Airport', 'Kuressaare', 'Estonia');

-- Kasutasin näidisandmete loomiseks tehisintellekti
-- Microsoft CoPilot . Kasutatud 2025-3-17. Prompt: 'This is my data.sql. Add airports from around the world to the list. [lisasin data.sql sisu]'
-- Loomisel kasutatud: https://copilot.microsoft.com/.
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (3, 'ATL', 'Hartsfield-Jackson Atlanta International Airport', 'Atlanta', 'United States');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (4, 'PEK', 'Beijing Capital International Airport', 'Beijing', 'China');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (5, 'DXB', 'Dubai International Airport', 'Dubai', 'United Arab Emirates');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (6, 'LAX', 'Los Angeles International Airport', 'Los Angeles', 'United States');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (7, 'HND', 'Tokyo Haneda Airport', 'Tokyo', 'Japan');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (8, 'ORD', 'O Hare International Airport', 'Chicago', 'United States');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (9, 'LHR', 'Heathrow Airport', 'London', 'United Kingdom');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (10, 'PVG', 'Shanghai Pudong International Airport', 'Shanghai', 'China');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (11, 'CDG', 'Charles de Gaulle Airport', 'Paris', 'France');
INSERT INTO airport(id, airport_code, airport_full_name, city_name, country)
VALUES (12, 'DFW', 'Dallas/Fort Worth International Airport', 'Dallas-Fort Worth', 'United States');


-- DELETE
-- FROM plane *;

-- Loomisel kasutatud: https://copilot.microsoft.com/.
INSERT INTO plane (id, name, number_of_seats)
VALUES (1, 'Boeing 737', 189);
INSERT INTO plane (id, name, number_of_seats)
VALUES (2, 'Airbus A320', 180);
INSERT INTO plane (id, name, number_of_seats)
VALUES (3, 'Boeing 777', 396);
INSERT INTO plane (id, name, number_of_seats)
VALUES (4, 'Airbus A380', 853);
INSERT INTO plane (id, name, number_of_seats)
VALUES (5, 'Embraer E190', 114);





-- Loomisel kasutatud: https://copilot.microsoft.com/.
INSERT INTO flight (departure_airport_id, destination_airport_id, price, departure_time, arrival_time, plane, occupied_seats)
VALUES (1, 2, 150.00, '2025-03-18 08:00:00', '2025-03-18 09:00:00', 1, 100),
       (3, 4, 1200.00, '2025-03-19 14:00:00', '2025-03-19 18:00:00', 2, 180),
       (5, 6, 900.00, '2025-03-20 10:00:00', '2025-03-20 14:00:00', 3, 150),
       (7, 8, 1100.00, '2025-03-21 16:00:00', '2025-03-21 20:00:00', 4, 200),
       (9, 10, 1300.00, '2025-03-22 12:00:00', '2025-03-22 16:00:00', 5, 20);