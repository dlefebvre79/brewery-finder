BEGIN TRANSACTION;

-- drop tables
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS breweries;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS images;

-- drop sequences
DROP SEQUENCE IF EXISTS seq_user_id;

--create sequences manually - so that you can set the value after inserting seed data
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


-- create tables
CREATE TABLE roles (
        role_id VARCHAR(50) NOT NULL,
        role_name VARCHAR (30) NOT NULL,
        CONSTRAINT pk_role_role_id PRIMARY KEY (role_id)
);

CREATE TABLE users (
	user_id INT DEFAULT nextval('seq_user_id') NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
	password_hash VARCHAR(200) NOT NULL,
	role_id VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
        last_name VARCHAR (50) NOT NULL,
        date_of_birth DATE NOT NULL,
        email VARCHAR(50) NOT NULL,
        is_subscribed BOOLEAN NOT NULL DEFAULT FALSE,
        zip_code VARCHAR(5) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT fk_role_role_id FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE breweries (
        brewery_id SERIAL,
        name VARCHAR(255) NOT NULL,
        address VARCHAR(255) NOT NULL,
        city VARCHAR(50) NOT NULL,
        zipcode VARCHAR(15) NOT NULL,
        phone_number VARCHAR(15),
        days_of_operation VARCHAR (255),
        business_hours VARCHAR(255),
        history_desc TEXT NOT NULL,
        atmosphere VARCHAR(255),
        is_family_friendly BOOLEAN NOT NULL,
        is_patio BOOLEAN NOT NULL,
        is_food BOOLEAN NOT NULL,
        is_active BOOLEAN NOT NULL,
        website_url VARCHAR(255),
        google_maps_url VARCHAR(255),
        user_id INTEGER,
        CONSTRAINT pk_brewery_brewery_id PRIMARY KEY (brewery_id),
        CONSTRAINT fk_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);

CREATE TABLE beer (
        beer_id SERIAL NOT NULL,
        name VARCHAR(255) NOT NULL,
        abv DECIMAL,
        ibu INTEGER,
        type VARCHAR(255) NOT NULL,
        info TEXT NOT NULL,
        brewery_id INTEGER,
        is_active BOOLEAN,
        CONSTRAINT pk_beer_beer_id PRIMARY KEY (beer_id),
        CONSTRAINT fk_brewery_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id) 
);

CREATE TABLE reviews (
        review_id SERIAL NOT NULL,
        beer_id INTEGER, 
        user_id INTEGER, 
        subject_title VARCHAR(100) NOT NULL,
        review TEXT NOT NULL,
        rating INT NOT NULL,
        create_date TIMESTAMP NOT NULL DEFAULT NOW(),
        CONSTRAINT pk_review_review_id PRIMARY KEY (review_id),
        CONSTRAINT fk_beer_beer_id FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT fk_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);

CREATE TABLE images (
        image_id SERIAL NOT NULL,
        url VARCHAR (255),
        description TEXT NOT NULL,
        alt_text TEXT,
        beer_id INTEGER,
        brewery_id INTEGER,
        CONSTRAINT pk_image_image_id PRIMARY KEY (image_id),
        CONSTRAINT fk_beer_beer_id FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT fk_brewery_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);




COMMIT TRANSACTION;

ROLLBACK TRANSACTION;