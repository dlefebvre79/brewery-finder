BEGIN TRANSACTION;

-- drop tables
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS breweries;
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

CREATE TABLE users (
	user_id INT DEFAULT nextval('seq_user_id') NOT NULL,
	username VARCHAR(50)  UNIQUE,
	password_hash VARCHAR(200) NOT NULL,
	role VARCHAR(20) NOT NULL,
	first_name VARCHAR(50),
        last_name VARCHAR (50),
        date_of_birth DATE,
        email VARCHAR(50) ,
        is_subscribed BOOLEAN DEFAULT FALSE,
        zip_code VARCHAR(5),
	CONSTRAINT PK_user PRIMARY KEY (user_id)
	
);

CREATE TABLE breweries (
        brewery_id SERIAL,
        name VARCHAR(255)NOT NULL,
        address VARCHAR(255),
        city VARCHAR(50),
        zipcode VARCHAR(15),
        phone_number VARCHAR(15),
        days_of_operation VARCHAR (255),
        business_hours VARCHAR(255),
        history_desc TEXT,
        atmosphere VARCHAR(255),
        is_family_friendly BOOLEAN,
        is_patio BOOLEAN,
        is_food BOOLEAN,
        is_active BOOLEAN,
        website_url VARCHAR(255),
        google_maps_url VARCHAR(255),
        user_id INTEGER NOT NULL,
        CONSTRAINT pk_brewery_brewery_id PRIMARY KEY (brewery_id),
        CONSTRAINT fk_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);

CREATE TABLE beer (
        beer_id SERIAL NOT NULL,
        name VARCHAR(255) NOT NULL,
        abv DECIMAL,
        ibu INTEGER,
        type VARCHAR(255),
        info TEXT,
        brewery_id INTEGER NOT NULL,
        is_active BOOLEAN,
        CONSTRAINT pk_beer_beer_id PRIMARY KEY (beer_id),
        CONSTRAINT fk_brewery_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id) 
);

CREATE TABLE reviews (
        review_id SERIAL NOT NULL,
        beer_id INTEGER NOT NULL,
        beer_name VARCHAR(255) NOT NULL, 
        user_id INTEGER NOT NULL, 
        subject_title VARCHAR(100),
        review TEXT,
        rating INT,
        create_date TIMESTAMP DEFAULT NOW(),
        CONSTRAINT pk_review_review_id PRIMARY KEY (review_id),
        CONSTRAINT fk_beer_beer_id FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT fk_user_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
    
);

CREATE TABLE images (
        image_id SERIAL NOT NULL,
        url VARCHAR (255),
        description TEXT,
        alt_text TEXT,
        beer_id INTEGER,
        brewery_id INTEGER,
        CONSTRAINT pk_image_image_id PRIMARY KEY (image_id),
        CONSTRAINT fk_beer_beer_id FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
        CONSTRAINT fk_brewery_brewery_id FOREIGN KEY (brewery_id) REFERENCES breweries(brewery_id)
);




COMMIT TRANSACTION;

--ROLLBACK TRANSACTION;