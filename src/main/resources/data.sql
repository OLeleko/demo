DELETE FROM vote;
DELETE FROM meal;
DELETE FROM menu;
DELETE FROM RESTAURANT;
DELETE FROM USERS;

INSERT INTO users (NAME, PASSWORD, REGISTERED, ENABLED, admin)
VALUES ('user1', '12345', '2020-05-02', 'true', 'false'),
       ('user2', '12365', '2020-06-11', 'true', 'false'),
       ('user3', '12745', '2020-07-12', 'true', 'false'),
       ('admin1', '52345', '2020-08-13', 'true', 'true'),
       ('admin2', '17345', '2020-09-14', 'true', 'true');

INSERT INTO RESTAURANT (NAME)
VALUES ('rest1'),
       ('rest2'),
       ('rest3');

INSERT INTO MENU (restaurant_id, regist_date)
VALUES('100005', '2020-11-16'),
       ('100005','2020-11-17'),
       ('100005','2020-11-18'),
       ('100006','2020-11-16'),
       ('100006','2020-11-17'),
       ('100006','2020-11-18'),
       ('100007','2020-11-16'),
       ('100007','2020-11-17'),
       ('100007','2020-11-18');



INSERT INTO MEAL (NAME, PRICE, MENU_ID)
VALUES ('meal1_1_1', '3.23', '100008'),
       ('meal1_1_2', '15.23', '100008'),
       ('meal1_2_1', '25.24', '100009'),
       ('meal1_2_2', '35.25', '100009'),
       ('meal1_2_3', '15.26', '100009'),
       ('meal1_3_1', '15.27', '100010'),
       ('meal1_3_2', '18.28', '100010'),
       ('meal1_3_3', '15.29', '100010'),
       ('meal1_3_4', '95.30', '100010'),
       ('meal2_1_1', '43.31', '100011'),
       ('meal2_1_2', '25.32', '100011'),
       ('meal2_2_1', '8.33', '100012'),
       ('meal2_2_2', '15.34', '100012'),
       ('meal2_2_3', '55.35', '100012'),
       ('meal2_2_4', '95.36', '100012'),
       ('meal2_3_1', '95.37', '100013'),
       ('meal2_3_2', '95.38', '100013'),
       ('meal3_1_1', '67.13', '100014'),
       ('meal3_1_2', '67.14', '100014'),
       ('meal3_1_3', '67.15', '100014'),
       ('meal3_2_1', '45.78', '100015'),
       ('meal3_2_2', '54.79', '100015'),
       ('meal3_3_1', '35.80', '100016'),
       ('meal3_3_2', '25.81', '100016'),
       ('meal3_3_3', '95.82', '100016');

INSERT INTO VOTE(VOTE_DATE, USER_ID, MENU_ID)
VALUES('2020-11-16','100000','100008'),
      ('2020-11-17','100000','100015'),
      ('2020-11-18','100000','100010'),
      ('2020-11-16','100001','100011'),
      ('2020-11-17','100001','100012'),
      ('2020-11-18','100001','100010'),
      ('2020-11-16','100002','100014'),
      ('2020-11-17','100002','100009'),
      ('2020-11-18','100002','100013'),
      ('2020-11-16','100003','100008'),
      ('2020-11-17','100003','100009'),
      ('2020-11-18','100003','100013');

INSERT INTO MYTRY(ID, NAME, REGISTERED)
VALUES(111000, 'one', '2020-11-16'),
      (111001, 'two', '2020-11-17');






