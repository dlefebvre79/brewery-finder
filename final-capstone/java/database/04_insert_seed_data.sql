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
VALUES (1, 'Great Lakes Brewing'
		, '2516 Market Avenue'
		, 'Cleveland'
		, '44113'
		, '216-771-4404'
		, 'Monday,Tuesday,Wednesday,Thursday,Friday,Saturday'
		, '1200-1800,1200-1800,1200-1800,1200-1800,1200-1800,1200-1800'
		, 'Two Irish brothers with limited brewing experience. A city that shuttered its last production brewery in the early 80s.
          A neighborhood in serious need of a facelift. In 1986 when Patrick and Daniel Conway opened their fledgling operation in Cleveland`s
          Ohio City neighborhood the odds were stacked against them. Fortunately they surrounded themselves with a staff of passionate knowledgeable people and
          from the start committed themselves to bringing a sophisticated diverse selection of craft beer to their home state. Two decades multiple awards and a 
          whole lot of stories later Pat and Dan Conway celebrate over two decades of brewing exceptional beer for their adventurous and discerning customers.', 
         'Iconic microbrewery with house suds, an updated American menu, free tours & a patio.','true', 'true', 'true', 'true','https://www.greatlakesbrewing.com',
         'https://www.google.com/maps/place/Great+Lakes+Brewing+Company/@41.4843847,-81.7067074,17z/data=!3m1!4b1!4m5!3m4!1s0x8830f06dbb76e503:0xe4f2621c30edaa7b!8m2!3d41.4843807!4d-81.7045187', 2);
         
INSERT INTO breweries (brewery_id, name, address, city, zipcode, phone_number, days_of_operation, business_hours, history_desc, atmosphere, is_family_friendly, is_patio, is_food, is_active, website_url, google_maps_url, user_id)
VALUES (2, 'Saucy Brew Works'
		, '2885 Detroit Rd'
		, 'Cleveland'
		, 44113
		,'(216) 666-2568'
		,
        'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday'
		, '1100-2200,1100-2200,1100-2200,1100-2200,1100-2200,1100-2200,1100-2200'
		, 'The story starts with our Brew Master Eric Anderson and Entrepreneur Brent Zimmerman. 
         Beer fanatics. Always up for adventure. They decided to transform the Steelman Building into the kind of place that delivers nothing less than crazy good beer and legendary good times.'
		 , 'Two-tiered brewery offering housemade European-style beer & customizable thin-crust pizzas.'
		 , 'true'
		 , 'true'
		 , 'true'
		 , 'true'
		 , 'https://www.saucybrewworks.com/'
		 , 'https://www.google.com/maps/place/Saucy+Brew+Works/@41.489633,-81.7128136,17z/data=!3m1!4b1!4m5!3m4!1s0x8830f06856cfcaf7:0xb7cc96529c4594ea!8m2!3d41.489629!4d-81.7106249'
		 , 2);
--insert John        
INSERT INTO breweries (brewery_id, name, address, city, zipcode, phone_number, days_of_operation, business_hours, history_desc, atmosphere, is_family_friendly, is_patio, is_food, is_active, website_url, google_maps_url, user_id)
VALUES (3, 'Rocky River Brewing Company'
	, '21290 Center Ridge Road'
	, 'Rocky River'
	, '44116'
	, '440-895-2739'
	, 'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday'
	, '1130-2200,1130-2200,1130-2200,1130-2200,1130-2200,1130-2300,1130-2300'
	, 'Pleasing Palettes Since 1998 - AWARD-WINNING HANDCRAFTED FOOD & BEER - BrewPub & Restaurant.'
	, 'Classic Bar and Restaurant'
	, 'true'
	, 'true'
	, 'true'
	, 'true'
	, 'https://www.rockyriverbrewco.com/'
	, 'https://www.google.com/maps/place/21290+Center+Ridge+Rd,+Rocky+River,+OH+44116/@41.4610007,-81.8583192,17z/data=!3m1!4b1!4m5!3m4!1s0x8830ed4f8cc1d2c9:0xa3b50ec4ba97919a!8m2!3d41.4610007!4d-81.8561305'
	, 2
	);
	
INSERT INTO breweries (brewery_id, name, address, city, zipcode, phone_number, days_of_operation, business_hours, history_desc, atmosphere, is_family_friendly, is_patio, is_food, is_active, website_url, google_maps_url, user_id)
VALUES (4, 'Sibling Revelry Brewing'
	, '29305 Clemens Rd'
	, 'Westlake'
	, '44145'
	, '440-471-8589'
	, 'Thursday,Friday,Saturday'
	, '1600-2100,1600-2100,1200-2100'
	, 'Sibling Revelry Brewing was founded by a family of brothers and cousins from Cleveland who wanted to share their passion for great beer with others.
	 Noticing a lack of breweries in the west side suburbs - the relatives set out to create a unique taproom and drinking experience rooted right in their own backyards. 
	 Sibling Revelry''s sophisticated taproom offers a full selection of craft beers including year-round brews - seasonal varieties - limited releases and taproom exclusives. 
	 Our outdoor patio provides additional space to enjoy our brews with friends and dogs.'
	, 'Warehouse Brewery'
	, 'true'
	, 'true'
	, 'true'
	, 'true'
	, 'https://www.siblingrevelrybrewing.com/'
	, 'https://www.google.com/maps/dir//sibling+revelry+brewing/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x88309204eae51f31:0x2652f1d2afc72563?sa=X&ved=2ahUKEwjjnv2XtaHvAhWTGs0KHVtYDvwQ9RcwE3oECCkQBQ'
	, 2
	);
        
--Beer        
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (1, 'Christmas Ale', 7.5, 30, 'Winter Warmer Ale',
        'Ale with spices and honey and a Yuletide`s worth of holiday spices and sweet honey to keep you a-wassailing all season long'
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
VALUES (4, 'Habituale', 5.3, 21, 'Kolsch',
        'This crisp, clean Kolsch is a true German classic. Its the perfect tribute to our fair-minded friend: sweet balanced with faint bitterness. Appeals to crossover mainstream drinkers and beer connoisseurs alike.'
        , 2, 'true');
        
INSERT INTO beer (beer_id, name, abv,ibu, type, info,  brewery_id, is_active) 
VALUES (5, 'B.F.Hefe', 5.5, 11, 'Wheat',
        'This Bavarian classic offers that kind of balance. Its not overly bitter or sweet. The original gangster of cloudy beers is thirst quenching and effervescent.'
        , 2, 'true');
        
INSERT INTO beer (beer_id, name, abv,ibu, type, info, brewery_id, is_active) 
VALUES (6, 'Punchline', 5.7, 12, 'Wheat',
        'Our fruit beer is a tribute to fruit punch. Tons of fruit flavors come together in a haymaker that hits you right in the red rubber nose.'
        , 2, 'true');

--John's inserts
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (7, 'Rocky River Pirate Light'
	,  4.8
	,  16
	, 'Blonde Ale'
	, 'This American blonde ale will grow on you. This brew is smooth, golden, and has fewer calories.'
	,  3
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES	(8, 'Hop Goblin IPA'
	,  7.6
	,  67
	, 'IPA'
	, 'This West Coast-style IPA is our new flagship IPA. It has a high level of hop bitterness, flavor and aroma, and just enough malt to balance it.'
	,  3
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES	(9, 'Blarney Rubble Irish Red'
	,  5.4
	,  20
	, 'Irish Red'
	, 'This is what happened when Bam Bam went to Ireland and had a few too many irish reds and went to visit the Blarney Stone.'
	,  3
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES	(10, 'Cooper''s Gold Kolsch'
	,  6.0
	,  28
	, 'German Ale'
	, 'This German ale (alt-style) utilizes a unique blend of seven different German malts and two different German hops to produce a golden color.'
	,  3
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES	(11, 'Punchin The Nuts'
	,  5.3
	,  20
	, 'Amber Ale'
	, 'Our amber ale brewed with peanut butter. Which brings out a nice  peanut butter aroma and a light peanut butter taste. It''s everyone''s favorite...everyone loves a good Punchin the Nuts!!!!!!'
	,  3
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES  (12, 'Tropic Funk IPA'
	,  10.2
	,  70
	, 'IPA'
	, 'Citrus is what you smell and taste with a little ipa bitterness. The alcohol is what you will feel.'
	,  3
	, 'true');
        
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (13, 'IPA'
	,  6.5
	,  60
	, 'India Pale Ale'
	, 'Our Indai Pale Ale has big pine, citrus and stone fruit aromas with a clean, dry finish.  It is like Grandpa Bert - not that bitter once you get to know him.'
	,  4
	, 'true');

INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES 	(14, 'Red'
	, 5.5
	, 35
	, 'American Red Ale'
	, 'An American red ale brewed in the Irish tradition.'
	,  4
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES 	(15, 'Swing State'
	, 4.5
	, 40
	, 'Dry Hopped Pale Ale'
	, 'A dry-hopped, light bodied American Pale Ale.'
	,  4
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES 	(16, 'Coffee Red'
	,  5.5
	,  25
	, 'Coffee Red Ale'
	, 'We took a few barrels of our Flagship red ale and conditioned it with freshly roasted Rwandan coffee from our friends at Duck-rabbit Coffee. Using whole beans for a softer rounder flavor.'
	,  4
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (17, 'Siblime'
	,  5.0
	,  20
	, 'Lager brewed with Lime'
	, 'Light and refereshing, this summer seasonal lager is brewed with just enough lime and spice to remind you that the Cleveland winter is almost over.'
	,  4
	, 'true');
	
INSERT INTO beer (beer_id, name, abv, ibu, type, info, brewery_id, is_active)
VALUES (18, 'Mocha Oatmeal Stout'
	,  6.5
	,  25
	, 'Oatmeal Stout'
	, 'This special batch of our Oatmeal Stout was conditioned with cacao nibs and coffee sourced from Clevelands own Duck-rabbit coffee roasters.'
	,  4
	, 'true');
	

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

-- images       
INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(1, 
        'https://www.greatlakesbrewing.com/sites/default/files/styles/beer_page/public/christmas_ale_bottle_glass_can_0.png?itok=gsAGIklG', 
        'Ale with spices and honey and a Yuletide`s worth of holiday spices and sweet honey to keep you a-wassailing all season long',
        'Christmas Ale',
        1,
        1);

INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(2,
        'https://www.greatlakesbrewing.com/sites/default/files/styles/beer_page/public/eliot-ness-2021_0.jpg?itok=tb8D-ZHX',
        'Almost untouchably smooth--armed with lightly toasted malts and noble hops.',
        'Elliot Ness',
        2,
        1);

INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(3,
       'https://www.greatlakesbrewing.com/sites/default/files/styles/beer_page/public/burning-river-cans-fixed-580x960_0.jpg?itok=AOcghGqh',
       'Crisp and bright, with refreshing flickers of citrus and pine that ignite the senses (not our local waterways).',
       'Burning River',
       3,
       1);

INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(4,
       'https://www.saucybrewworks.com/media/images/Beer-Page_Core-Cans_Habituale.width-400.png',
       'This crisp, clean Kolsch is a true German classic. Its the perfect tribute to our fair-minded friend: sweet balanced with faint bitterness. Appeals to crossover mainstream drinkers and beer connoisseurs alike.',
       'Habituale',
       4,
       2);

INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(5,
       'https://www.saucybrewworks.com/media/images/Beer-Page_Core-Cans_BfHefe.width-400.png',
       'Light to medium bodied with a wheaty, fruity flavor',
       'B.F. Hefe',
       5,
       2);

INSERT INTO images (image_id, url, description, alt_text, beer_id, brewery_id)
VALUES(6,
       'https://www.saucybrewworks.com/media/images/Beer-Page_Core-Cans_IPA.width-400.png',
       'Our fruit beer is a tribute to fruit punch. Tons of fruit flavors come together in a haymaker that hits you right in the red rubber nose.',
       'Punchline',
       6,
       2);
   

-- reset the sequence numbering for the users table
ALTER SEQUENCE seq_user_id RESTART WITH 4;



COMMIT TRANSACTION;

--ROLLBACK TRANSACTION;