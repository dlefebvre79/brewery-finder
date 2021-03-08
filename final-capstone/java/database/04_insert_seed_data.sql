-- ********************************************
-- this script inserts seed data for all tables
-- ********************************************

BEGIN TRANSACTION;


-- users
	-- the default password for both users is password
INSERT INTO users (user_id, username, password_hash, role, first_name, last_name, date_of_birth, email, is_subscribed, zip_code) 
VALUES (1, 'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'Janet', 'Marino', '1980-06-23', 'dolphins@yahoo.com', 'true', '44192')
	 , (2, 'admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'Admin', 'Admin', '1969-03-17', 'admin@gmail.com', 'true', '44109'),
	 (3, 'brewer', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER', 'Beery', 'Brewer', '1999-12-12','brewer@aol.com', 'true', '44687' );
	 
--Breweries	 
INSERT INTO breweries (brewery_id, name, address, city, zipcode, phone_number, days_of_operation, business_hours, history_desc, atmosphere, is_family_friendly, is_patio, is_food, is_active, website_url, google_maps_url, user_id)
VALUES (1, 'Great Lakes Brewing', '2516 Market Avenue', 'Cleveland', '44113', '216-771-4404',
         'Monday - Saturday', '12:00 AM - 6:00 PM.', 
         'Two Irish brothers with limited brewing experience. A city that shuttered its last production brewery in the early 80s.
          A neighborhood in serious need of a facelift. In 1986 when Patrick and Daniel Conway opened their fledgling operation in Cleveland`s
          Ohio City neighborhood the odds were stacked against them. Fortunately they surrounded themselves with a staff of passionate knowledgeable people and
          from the start committed themselves to bringing a sophisticated diverse selection of craft beer to their home state. Two decades multiple awards and a 
          whole lot of stories later Pat and Dan Conway celebrate over two decades of brewing exceptional beer for their adventurous and discerning customers.', 
         'Iconic microbrewery with house suds, an updated American menu, free tours & a patio.','true', 'true', 'true', 'true','https://www.greatlakesbrewing.com',
         'https://www.google.com/maps/place/Great+Lakes+Brewing+Company/@41.4843847,-81.7067074,17z/data=!3m1!4b1!4m5!3m4!1s0x8830f06dbb76e503:0xe4f2621c30edaa7b!8m2!3d41.4843807!4d-81.7045187', 2);
         
INSERT INTO breweries (brewery_id, name, address, city, zipcode, phone_number, days_of_operation, business_hours, history_desc, atmosphere, is_family_friendly, is_patio, is_food, is_active, website_url, google_maps_url, user_id)
VALUES (2, 'Saucy Brew Works', '2885 Detroit Rd', 'Cleveland', 44113,'(216) 666-2568',
        'Sunday - Saturday', '11:00 AM - 10:00 PM', 'The story starts with our Brew Master Eric Anderson and Entrepreneur Brent Zimmerman. 
         Beer fanatics. Always up for adventure. They decided to transform the Steelman Building into the kind of place that delivers nothing less than crazy good beer and legendary good times.',
        'Two-tiered brewery offering housemade European-style beer & customizable thin-crust pizzas.', 'true', 'true', 'true', 'true', 'https://www.saucybrewworks.com/',
        'https://www.google.com/maps/place/Saucy+Brew+Works/@41.489633,-81.7128136,17z/data=!3m1!4b1!4m5!3m4!1s0x8830f06856cfcaf7:0xb7cc96529c4594ea!8m2!3d41.489629!4d-81.7106249', 2);
        
--Beer        
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (1, 'Christmas Ale', 7.5, 30, 
        'Ale with spices and honey', 'A Yuletide`s worth of holiday spices and sweet honey
        to keep you a-wassailing all season long'
        , 1, 'true');
        
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active) 
VALUES (2, 'Eliot Ness', 6.1, 27, 'Amber Lager',
        'Almost untouchably smooth--armed with lightly toasted malts and noble hops.'
        , 1, 'true');
        
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (3, 'Burning river', 6.0, 35, 'Pale Ale',
        'Crisp and bright, with refreshing flickers of citrus and pine that ignite the senses (not our local waterways).'
        , 1, 'true');
        
INSERT INTO beer (beer_id, name, abv,ibu, type, info,  brewery_id, is_active) 
VALUES (4, 'Habituale', 5.3, 21, 'Kölsch',
        'This crisp, clean Kölsch is a true German classic. Its the perfect tribute to our fair-minded friend: sweet balanced with faint bitterness. Appeals to crossover mainstream drinkers and beer connoisseurs alike.'
        , 2, 'true');
        
INSERT INTO beer (beer_id, name, abv,ibu, type, info,  brewery_id, is_active) 
VALUES (5, 'B.F.Hefe', 5.5, 11, 'Wheat',
        'This Bavarian classic offers that kind of balance. Its not overly bitter or sweet. The original gangster of cloudy beers is thirst quenching and effervescent.'
        , 2, 'true');
        
INSERT INTO beer (beer_id, name, abv,ibu, type, info, brewery_id, is_active) 
VALUES (6, 'Punchline', 5.7, 12, 'Wheat',
        ' Our fruit beer is a tribute to fruit punch. Tons of fruit flavors come together in a haymaker that hits you right in the red rubber nose.'
        , 2, 'true');
        
--Reviews
INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date) 
VALUES (1, 1, 'Best Christmas present ever!','Who needs a bunch of presents when you only need a six pack of Christmas Ale!',
5,now());

INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date) 
VALUES (2, 1, 'One of the greatest I have tasted.','Greeat taste, better balance, best to drink before drunk!',
5,now());

INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date)
 VALUES (3, 1, 'It is the piney taste for me',
'I think this is the perfect beer for my taste buds!',
5,now());

INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date)
 VALUES (4, 1, 'I see why they call it Habituale',
'It is becoming a habit drinking these, everyday!',
4,now());

INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date)
 VALUES (5, 1, 'This wheat beer is better than wheat bread!',
'I think I will just slap my lunchmeat in between two bottles of these from now on, now that is a sandwich!',
4,now());

INSERT INTO reviews (beer_id, user_id, subject_title, review, rating, create_date)
 VALUES (6, 1, 'Good for when you can not come up with the right words',
'Punchline is a great balance of taste, full body, and dancing without having to be nervous, lol!',
5,now());
        


   

-- reset the sequence numbering for the users table
ALTER SEQUENCE seq_user_id RESTART WITH 3;



COMMIT TRANSACTION;

ROLLBACK TRANSACTION;