# AngularBoot Spring Rest 
APPLICATION

To setup the MySQL db. Run these commands:
create database 'angularBoot';

CREATE USER 'smccarthy'@'localhost' IDENTIFIED BY 'passwordSetByYou';

GRANT ALL PRIVILEGES ON * . * TO 'smccarthy'@'localhost';

INSERT INTO User (id, username, password, first_name, last_name) VALUES (1, 'username', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'userFirst', 'userLast');

The basic user for the app is:
username: username
password: password

To serve the angular material frontend: navigate to the frontend and run: ng serve

To run the spring boot backend: build with maven and deploy to localhost:8080/

Main App logic flow:
login at: 
http://localhost:4200/login
Redirects to the car list screen at:
http://localhost:4200/car


