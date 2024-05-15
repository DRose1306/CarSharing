-- Вставка ролей в таблицу "roles"
INSERT INTO roles (role_id, role_name)
VALUES
    (X'a91521fa12544467bfc1543adb21a1a1', 'ADMIN'),
    (X'c4f448972aca4bf19d40c002d18bad39', 'MANAGER'),
    (X'b3143434372f4793833cb85fc6fa9352', 'USER'),
    (X'9056be056f664a058459ce9df8b13bc8', 'GUEST');

-- Вставка прав в таблицу "authorities"
INSERT INTO authorities (auth_id, authority)
VALUES
    (X'd43f3c7d510f43ac9b5c6d36645729cc', 'CREATE'),
    (X'1cc35d03ac234cf4989e9d3d93e50395', 'READ'),
    (X'199a7c890ea844189b6d9c47cb75e6fc', 'UPDATE'),
    (X'8f32c432a13242b78f60be099e2f41f0', 'DELETE');

-- Вставка данных в таблицу "addresses"
INSERT INTO addresses (address_id, street, house_number, city, zip_code, country)
VALUES
    (X'665714b8d6d84e3399ce38e0560faaa6', 'Alexanderplatz', '10', 'Berlin', '10178', 'Germany'),
    (X'8efaced09f0a427c86a90545abda1e8c', 'Reeperbahn', '20', 'Hamburg', '20359', 'Germany'),
    (X'08c994be1712411e887aac52f109f919', 'Domplatz', '30', 'Cologne', '50667', 'Germany'),
    (X'eef03e06b85b4c01ae76fd227a6bd849', 'Königstraße', '40', 'Stuttgart', '70173', 'Germany'),
    (X'3d4a00650e7e434eab10735a964a2dfd', 'Neumarkt', '50', 'Osnabrück', '49074', 'Germany');

-- Вставка персональных данных в таблицу "user_info"
INSERT INTO user_info (user_info_id, date_of_birth, phone_number, email, user_login, user_password, card_number, driver_license, driver_licence_id, address_id)
VALUES
    (X'9faef735c9254161ae158a0dda166bf3', '1992-06-30', '111-222-333', 'michael@example.com', 'MJ' ,'d2ecce31ab3f5a11d0f6d389f68686c75c80ead1857c7f38d21fecd4da1209b5', '1234 5678 9012 3456','B', 'DE123456789012', X'665714b8d6d84e3399ce38e0560faaa6'),
    (X'09ff1f4c1d5c4168ab86f1651e9bab69', '1987-09-25', '444-555-666', 'emily@example.com', 'Emmy' ,'8dce5f0012304c06d98cfb0cd11bc7eaaafaf32c4da26df424bcdd59ccfe0a5d', '9876 5432 1098 7654','B', 'DE987654321098' , X'8efaced09f0a427c86a90545abda1e8c'),
    (X'd2ed908738fb4789806a7a4c07deb7de', '1980-12-10', '777-888-999', 'daniel@example.com', 'Di_Brown' ,'15acd26889bccf5865c22aa472ec069cf0a31512f9a53dadc0bc8f2aea3f84cd', '2468 1357 8024 6813','B', 'DE654321098765' , X'08c994be1712411e887aac52f109f919'),
    (X'97daabac610d4578a4afe95e1e07d1ad', '1995-04-05', '123-456-789', 'olivia@example.com', 'Oli' ,'c7b702284ddd187ddb240fa0ee6d77019e280eedbec7290c886a7dd193fb0e99', '8642 9753 2106 4832','B', 'DE321098765432' , X'eef03e06b85b4c01ae76fd227a6bd849'),
    (X'6e4d3bb66f114634bca1f9d90e1574da', '1990-10-20', '987-654-321', 'william@example.com', 'WillWilson' ,'37c60d066e0fc140a4751f274112a25d5b263f4f0dec1e6c555ee2178387b1c8', '7531 8642 3069 1752','B', 'DE789012345678' , X'3d4a00650e7e434eab10735a964a2dfd');

-- Вставка данных в таблицу "cars"
INSERT INTO cars (car_id, year_of_release, license_plate, car_status, car_brand)
VALUES
    (X'ef6869b7240248c7bff4141563be2d8c', '2020', 'B-B1', 'IN_USE', 'AUDI'),
    (X'3c004a2b3ff344138ce3e72ec557b6fc', '2023', 'HB-HB2', 'RESERVED', 'FORD'),
    (X'f80a04a4801541b784588ca40416b4a3', '2018', 'K-K3', 'IN_USE', 'KIA'),
    (X'88a71c7ed01140e3b9b578315c983b21', '2022', 'S-S4', 'IN_USE', 'SKODA'),
    (X'2e88a78db4a74a00b5904d0f7abe6c04', '2019', 'OS-OS5', 'IN_USE', 'HONDA');

-- Вставка в таблицу "users"
INSERT INTO users (user_id, first_name, last_name, user_info_id)
VALUES
    (X'7270910ccc71463497a0a242eb5b6064', 'Michael', 'Johnson', X'9faef735c9254161ae158a0dda166bf3'),
    (X'dfb0689f5f694825a15433c897fa1b38', 'Emily', 'Anderson', X'09ff1f4c1d5c4168ab86f1651e9bab69'),
    (X'55035fe937e3466fba4a197f23fc5700', 'Daniel', 'Brown', X'd2ed908738fb4789806a7a4c07deb7de'),
    (X'cd8edecd0d2742288fe6911c1cf7fd7c','Olivia', 'Martinez', X'97daabac610d4578a4afe95e1e07d1ad'),
    (X'7881bf3e73a947da8baee2e253a30ddd','William', 'Wilson', X'6e4d3bb66f114634bca1f9d90e1574da');

-- Вставка данных таблицу "trips"
INSERT INTO trips (trip_id, start_time, end_time, distance, cost, user_id, car_id)
VALUES
    (X'99b464245a6045a0b378cbe9f8a49c73', '2024-04-20 09:00:00', '2024-04-10 19:00:00', 100.0, 100.00, X'7270910ccc71463497a0a242eb5b6064', X'ef6869b7240248c7bff4141563be2d8c'),
    (X'45c423a12a2f4d2eb5f39a844866164d', '2024-05-10 13:00:00', '2024-05-25 22:30:00', 200.0, 200.00, X'dfb0689f5f694825a15433c897fa1b38', X'3c004a2b3ff344138ce3e72ec557b6fc'),
    (X'530ddcadd61d4fc196143d3f526f3675', '2024-06-01 00:00:00', '2024-06-20 14:00:00', 300.0, 300.00, X'55035fe937e3466fba4a197f23fc5700', X'f80a04a4801541b784588ca40416b4a3'),
    (X'7df490a208a94c448fa0476c904cd4d5', '2024-07-15 10:00:00', '2024-07-10 23:00:00', 400.0, 400.00, X'cd8edecd0d2742288fe6911c1cf7fd7c', X'88a71c7ed01140e3b9b578315c983b21'),
    (X'0628ad729f214dd498eaee08bcfbd36e', '2024-08-10 00:00:00', '2024-08-25 17:30:00', 500.0, 500.00, X'7881bf3e73a947da8baee2e253a30ddd', X'2e88a78db4a74a00b5904d0f7abe6c04');

-- Вставка данных в таблицу "payments". Связывание событий и сотрудников
INSERT INTO payments (payment_id, amount, payment_date, payment_method,payment_status, user_id)
VALUES
    (X'92683b96579e4fee9329b442639582e7', 100.00, '2024-04-10 19:00:00', 'VISA', 1, X'7270910ccc71463497a0a242eb5b6064'),
    (X'd4379c09f87145a193b4a52d9f91ac57', 200.00, '2024-05-25 22:30:00', 'VISA', 1, X'dfb0689f5f694825a15433c897fa1b38'),
    (X'ea24316662d34fa1b168ae9fd0dc5d6b', 300.00, '2024-06-20 14:00:00', 'MASTERCARD', 0, X'55035fe937e3466fba4a197f23fc5700'),
    (X'e4931cb92dea4a75bdf2262d7361f343', 400.00, '2024-07-10 23:00:00', 'PAYPAL', 0, X'cd8edecd0d2742288fe6911c1cf7fd7c'),
    (X'ced886c761fd4d5e9be3edcfb2efa0c8', 500.00, '2024-08-25 17:30:00', 'GOOGLE_PAY', 1, X'7881bf3e73a947da8baee2e253a30ddd');

-- Вставка данных о событиях компании в таблицу "reservations"
INSERT INTO reservations (reservation_id, start_time, end_time, car_id, user_id)
VALUES
    (X'25203a16193a41e88a2dd0ef66738d54', '2024-04-20 09:00:00', '2024-04-10 19:00:00', X'ef6869b7240248c7bff4141563be2d8c', X'7270910ccc71463497a0a242eb5b6064'),
    (X'353066d436a04641b2bb3e215fc2b2a5', '2024-05-10 13:00:00', '2024-05-25 22:30:00', X'3c004a2b3ff344138ce3e72ec557b6fc', X'dfb0689f5f694825a15433c897fa1b38'),
    (X'80f84502bfa144d8938b0fe7d1fc192d', '2024-06-01 00:00:00', '2024-06-20 14:00:00', X'f80a04a4801541b784588ca40416b4a3', X'55035fe937e3466fba4a197f23fc5700'),
    (X'3654b1b9b0fd490ab10b44e5d8b4d476', '2024-07-15 10:00:00', '2024-07-10 23:00:00', X'88a71c7ed01140e3b9b578315c983b21', X'cd8edecd0d2742288fe6911c1cf7fd7c'),
    (X'bd32d66b2a2f4e19862dc7a6377a390d', '2024-08-10 00:00:00', '2024-08-25 17:30:00', X'2e88a78db4a74a00b5904d0f7abe6c04', X'7881bf3e73a947da8baee2e253a30ddd');

-- Вставка данных в таблицу "user_info_role". Связывание пользователей и ролей
INSERT INTO user_info_role (user_info_id, role_id)
VALUES
    (X'9faef735c9254161ae158a0dda166bf3', X'a91521fa12544467bfc1543adb21a1a1'),
    (X'09ff1f4c1d5c4168ab86f1651e9bab69', X'c4f448972aca4bf19d40c002d18bad39'),
    (X'd2ed908738fb4789806a7a4c07deb7de', X'b3143434372f4793833cb85fc6fa9352'),
    (X'97daabac610d4578a4afe95e1e07d1ad', X'b3143434372f4793833cb85fc6fa9352'),
    (X'6e4d3bb66f114634bca1f9d90e1574da', X'b3143434372f4793833cb85fc6fa9352');

-- Вставка данных в таблицу "role_authority". Связывание ролей и прав
INSERT INTO role_authority (role_id, auth_id)
VALUES
    (X'a91521fa12544467bfc1543adb21a1a1', X'd43f3c7d510f43ac9b5c6d36645729cc'),
    (X'c4f448972aca4bf19d40c002d18bad39', X'199a7c890ea844189b6d9c47cb75e6fc'),
    (X'b3143434372f4793833cb85fc6fa9352', X'8f32c432a13242b78f60be099e2f41f0'),
    (X'b3143434372f4793833cb85fc6fa9352', X'1cc35d03ac234cf4989e9d3d93e50395'),
    (X'9056be056f664a058459ce9df8b13bc8', X'1cc35d03ac234cf4989e9d3d93e50395');
