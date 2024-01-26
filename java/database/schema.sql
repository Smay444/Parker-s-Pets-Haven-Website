BEGIN TRANSACTION;

DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS adoptions;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	name varchar (50) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE pets (
	pet_id SERIAL PRIMARY KEY,
	pet_name varchar(50) NOT NULL,
	pet_breed varchar (50),
	pet_color varchar(50),
	pet_age INT,
	is_available BOOLEAN,
	pet_description TEXT,
	pet_weight INT,
	pet_zip INT,
	pet_city varchar (50),
	pet_state varchar (10),
	adopted_by varchar (100)
);

CREATE TABLE applications (
	applicant_id SERIAL PRIMARY KEY,
	user_id int NOT NULL,
	app_email varchar (50),
	app_name varchar (50),
	app_phonenumber varchar(50),
	status  varchar (25) DEFAULT 'pending',
	application_date TIMESTAMP,
	approved_by varchar(50),
	CONSTRAINT FK_user_Id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE adoptions (
	adoption_id SERIAL,
	pet_id INT REFERENCES pets(pet_id),
	user_id int NOT NULL,
	date_adopted DATE,
	CONSTRAINT FK_adoptions FOREIGN KEY  (pet_id) REFERENCES pets(pet_id),
	CONSTRAINT FK_user_Id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE photos (
	photo_id SERIAL PRIMARY KEY,
	photo_URL varchar (250),
	pet_id INT REFERENCES pets(pet_id)
);

INSERT INTO pets (pet_name, pet_breed, pet_color, pet_age, is_available,
				 pet_description, pet_weight, pet_zip, pet_city, pet_state)
VALUES ( 'Coco', 'Terrier', 'Brown', '3', 'TRUE', 'Coco is Cool', '12',
	   				'44440','Mineral Ridge', 'OH') RETURNING pet_id;

INSERT INTO pets (pet_name, pet_breed, pet_color, pet_age, is_available,
				 pet_description, pet_weight, pet_zip, pet_city, pet_state)
VALUES ( 'Khaleesi', 'Boston Terrier', 'Black', '5', 'TRUE', 'Khaleesi is Cool', '12',
	   				'12345','Laurel Springs', 'NC') RETURNING pet_id;

INSERT INTO pets (pet_name, pet_breed, pet_color, pet_age, is_available,
				 pet_description, pet_weight, pet_zip, pet_city, pet_state)
VALUES ( 'Bobo', 'Pomeranian', 'White', '2', 'TRUE', 'Bobo is Cool', '6',
	   				'33221','Chicago', 'IL') RETURNING pet_id;

INSERT INTO photos (photo_url, pet_id)
VALUES ('https://uploads-ssl.webflow.com/63464d3429ecebd6475caaf0/6359c61f36a20512ef5de371_63464d3429eceb3d1f5cb429_16.png', 1);

INSERT INTO photos (photo_url, pet_id)
VALUES ('https://www.thesprucepets.com/thmb/b_dt6JpFxaD6ROMYy7nVmwuFars=/3504x0/filters:no_upscale():strip_icc()/Pomeranian-GettyImages-1014940472-a6ba0030958a4bbba0eee3e982ee9bc6.jpg', 2);


INSERT INTO photos (photo_url, pet_id)
VALUES ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnyRyrOV_zdA7ywEOIhLofU1ho19ZAbb3L4xPVjHiXm8xRVKMa', 3);

INSERT INTO photos (photo_url, pet_id)
VALUES ('https://cdn.britannica.com/77/235277-050-E9162647/white-bull-terrier-dog.jpg', 3);

COMMIT TRANSACTION;