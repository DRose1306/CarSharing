INSERT INTO roles (role_id, role_name)
VALUES
    (UUID_TO_BIN('a91521fa-1254-4467-bfc1-543adb21a1a1'), 'ROLE_ADMIN'),
    (UUID_TO_BIN('c4f44897-2aca-4bf1-9d40-c002d18bad39'), 'ROLE_MANAGER'),
    (UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352'), 'ROLE_USER'),
    (UUID_TO_BIN('9056be05-6f66-4a05-8459-ce9df8b13bc8'), 'ROLE_GUEST');

INSERT INTO authorities (auth_id, authority)
VALUES
    (UUID_TO_BIN('d43f3c7d-510f-43ac-9b5c-6d36645729cc'), 'CREATE'),
    (UUID_TO_BIN('1cc35d03-ac23-4cf4-989e-9d3d93e50395'), 'READ'),
    (UUID_TO_BIN('199a7c89-0ea8-4418-9b6d-9c47cb75e6fc'), 'UPDATE'),
    (UUID_TO_BIN('8f32c432-a132-42b7-8f60-be099e2f41f0'), 'DELETE');

INSERT INTO addresses (address_id, street, house_number, city, zip_code, country)
VALUES
    (UUID_TO_BIN('665714b8-d6d8-4e33-99ce-38e0560faaa6'), 'Alexanderplatz', '10', 'Berlin', '10178', 'Germany'),
    (UUID_TO_BIN('8efaced0-9f0a-427c-86a9-0545abda1e8c'), 'Reeperbahn', '20', 'Hamburg', '20359', 'Germany'),
    (UUID_TO_BIN('08c994be-1712-411e-887a-ac52f109f919'), 'Domplatz', '30', 'Cologne', '50667', 'Germany'),
    (UUID_TO_BIN('eef03e06-b85b-4c01-ae76-fd227a6bd849'), 'Königstraße', '40', 'Stuttgart', '70173', 'Germany'),
    (UUID_TO_BIN('3d4a0065-0e7e-434e-ab10-735a964a2dfd'), 'Neumarkt', '50', 'Osnabrück', '49074', 'Germany');

INSERT INTO user_info (user_info_id, date_of_birth, phone_number, email, user_login, user_password, card_number, driver_license, driver_licence_id, address_id)
VALUES
    (UUID_TO_BIN('9faef735-c925-4161-ae15-8a0dda166bf3'), '1992-06-30', '111-222-333', 'michael@example.com', 'MJ' ,'$2y$10$LJ/.uDhBhec1htk6TSnh3.dL1zMJr2yCLn2kWsPXS3jEmWQpM/jUe', '1234 5678 9012 3456','B', 'DE123456789012', UUID_TO_BIN('665714b8-d6d8-4e33-99ce-38e0560faaa6')),
    (UUID_TO_BIN('09ff1f4c-1d5c-4168-ab86-f1651e9bab69'), '1987-09-25', '444-555-666', 'emily@example.com', 'Emmy' ,'$2y$10$PD5ROkjigdUOVA4MH6tK2OX2isRBK9F8kPC8I8T0mbNZqYiPGKXx.', '9876 5432 1098 7654','B', 'DE987654321098' , UUID_TO_BIN('8efaced0-9f0a-427c-86a9-0545abda1e8c')),
    (UUID_TO_BIN('d2ed9087-38fb-4789-806a-7a4c07deb7de'), '1980-12-10', '777-888-999', 'daniel@example.com', 'Di_Brown' ,'$2y$10$rG/0Yq2WgDyVYuGpfzCMYOIT8ssyF.l20DF6lA1iwwBjXSLRq611C', '2468 1357 8024 6813','B', 'DE654321098765' , UUID_TO_BIN('08c994be-1712-411e-887a-ac52f109f919')),
    (UUID_TO_BIN('97daabac-610d-4578-a4af-e95e1e07d1ad'), '1995-04-05', '123-456-789', 'olivia@example.com', 'Oli' ,'$2y$10$L4eo73DJDbTtzRLH1IoS.uC9IqU0doSwBXVO9cbUIM/.PuOmudIa2', '8642 9753 2106 4832','B', 'DE321098765432' , UUID_TO_BIN('eef03e06-b85b-4c01-ae76-fd227a6bd849')),
    (UUID_TO_BIN('6e4d3bb6-6f11-4634-bca1-f9d90e1574da'), '1990-10-20', '987-654-321', 'william@example.com', 'WillWilson' ,'$2y$10$q8GEmrbSGZkgHGvcNchLi.w2cAMjOJeUcDtfJ91VVMzipOT0oHN1O', '7531 8642 3069 1752','B', 'DE789012345678' , UUID_TO_BIN('3d4a0065-0e7e-434e-ab10-735a964a2dfd'));

INSERT INTO cars (car_id, year_of_release, license_plate, car_status, car_brand)
VALUES
    (UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), '2020', 'B-B1', 'IN_USE', 'AUDI'),
    (UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), '2023', 'HB-HB2', 'RESERVED', 'FORD'),
    (UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), '2018', 'K-K3', 'IN_USE', 'KIA'),
    (UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), '2022', 'S-S4', 'IN_USE', 'SKODA'),
    (UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), '2019', 'OS-OS5', 'IN_USE', 'HONDA');

INSERT INTO users (user_id, first_name, last_name, user_info_id)
VALUES
    (UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), 'Michael', 'Johnson', UUID_TO_BIN('9faef735-c925-4161-ae15-8a0dda166bf3')),
    (UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), 'Emily', 'Anderson', UUID_TO_BIN('09ff1f4c-1d5c-4168-ab86-f1651e9bab69')),
    (UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), 'Daniel', 'Brown', UUID_TO_BIN('d2ed9087-38fb-4789-806a-7a4c07deb7de')),
    (UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'),'Olivia', 'Martinez', UUID_TO_BIN('97daabac-610d-4578-a4af-e95e1e07d1ad')),
    (UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'),'William', 'Wilson', UUID_TO_BIN('6e4d3bb6-6f11-4634-bca1-f9d90e1574da'));

INSERT INTO trips (trip_id, start_time, end_time, distance, cost, user_id, car_id)
VALUES
    (UUID_TO_BIN('99b46424-5a60-45a0-b378-cbe9f8a49c73'), '2024-04-20 09:00:00', '2024-04-10 19:00:00', 100.0, 100.00, UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c')),
    (UUID_TO_BIN('45c423a1-2a2f-4d2e-b5f3-9a844866164d'), '2024-05-10 13:00:00', '2024-05-25 22:30:00', 200.0, 200.00, UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc')),
    (UUID_TO_BIN('530ddcad-d61d-4fc1-9614-3d3f526f3675'), '2024-06-01 00:00:00', '2024-06-20 14:00:00', 300.0, 300.00, UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3')),
    (UUID_TO_BIN('7df490a2-08a9-4c44-8fa0-476c904cd4d5'), '2024-07-15 10:00:00', '2024-07-10 23:00:00', 400.0, 400.00, UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21')),
    (UUID_TO_BIN('0628ad72-9f21-4dd4-98ea-ee08bcfbd36e'), '2024-08-10 00:00:00', '2024-08-25 17:30:00', 500.0, 500.00, UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'));

INSERT INTO payments (payment_id, amount, payment_date, payment_method,payment_status, user_id)
VALUES
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), 100.00, '2024-04-10 19:00:00', 'VISA', 1, UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')), -- Michael Johnson
    (UUID_TO_BIN('d4379c09-f871-45a1-93b4-a52d9f91ac57'), 200.00, '2024-05-25 22:30:00', 'VISA', 1, UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')), -- Emily Anderson
    (UUID_TO_BIN('ea243166-62d3-4fa1-b168-ae9fd0dc5d6b'), 300.00, '2024-06-20 14:00:00', 'MASTERCARD', 0, UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')), -- Daniel Brown
    (UUID_TO_BIN('e4931cb9-2dea-4a75-bdf2-262d7361f343'), 400.00, '2024-07-10 23:00:00', 'PAYPAL', 0, UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')), -- Olivia Martinez
    (UUID_TO_BIN('ced886c7-61fd-4d5e-9be3-edcfb2efa0c8'), 500.00, '2024-08-25 17:30:00', 'GOOGLE_PAY', 1, UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd')); -- William Wilson

INSERT INTO reservations (reservation_id, start_time, end_time, car_id, user_id)
VALUES
    (UUID_TO_BIN('25203a16-193a-41e8-8a2d-d0ef66738d54'), '2024-04-20 09:00:00', '2024-04-10 19:00:00', UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')),
    (UUID_TO_BIN('353066d4-36a0-4641-b2bb-3e215fc2b2a5'), '2024-05-10 13:00:00', '2024-05-25 22:30:00', UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')),
    (UUID_TO_BIN('80f84502-bfa1-44d8-938b-0fe7d1fc192d'), '2024-06-01 00:00:00', '2024-06-20 14:00:00', UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')),
    (UUID_TO_BIN('f1f461ce-b791-415e-a2e8-91cda67feb02'), '2024-07-15 10:00:00', '2024-07-10 23:00:00', UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')),
    (UUID_TO_BIN('4e6656f5-ebc2-45a2-a181-4253819e2040'), '2024-08-10 00:00:00', '2024-08-25 17:30:00', UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'));

INSERT INTO user_info_role (user_info_id, role_id)
VALUES
    (UUID_TO_BIN('9faef735-c925-4161-ae15-8a0dda166bf3'), UUID_TO_BIN('a91521fa-1254-4467-bfc1-543adb21a1a1')),
    (UUID_TO_BIN('09ff1f4c-1d5c-4168-ab86-f1651e9bab69'), UUID_TO_BIN('c4f44897-2aca-4bf1-9d40-c002d18bad39')),
    (UUID_TO_BIN('d2ed9087-38fb-4789-806a-7a4c07deb7de'), UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352')),
    (UUID_TO_BIN('97daabac-610d-4578-a4af-e95e1e07d1ad'), UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352')),
    (UUID_TO_BIN('6e4d3bb6-6f11-4634-bca1-f9d90e1574da'), UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352'));

INSERT INTO role_authority (role_id, auth_id)
VALUES
    (UUID_TO_BIN('a91521fa-1254-4467-bfc1-543adb21a1a1'), UUID_TO_BIN('d43f3c7d-510f-43ac-9b5c-6d36645729cc')),
    (UUID_TO_BIN('c4f44897-2aca-4bf1-9d40-c002d18bad39'), UUID_TO_BIN('199a7c89-0ea8-4418-9b6d-9c47cb75e6fc')),
    (UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352'), UUID_TO_BIN('8f32c432-a132-42b7-8f60-be099e2f41f0')),
    (UUID_TO_BIN('b3143434-372f-4793-833c-b85fc6fa9352'), UUID_TO_BIN('1cc35d03-ac23-4cf4-989e-9d3d93e50395')),
    (UUID_TO_BIN('9056be05-6f66-4a05-8459-ce9df8b13bc8'), UUID_TO_BIN('1cc35d03-ac23-4cf4-989e-9d3d93e50395'));