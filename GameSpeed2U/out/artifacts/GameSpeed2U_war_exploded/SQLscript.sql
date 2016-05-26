/* drop old tables */
DROP TABLE OrderHistory CASCADE;
DROP TABLE Orders CASCADE;
DROP TABLE ConsoleAccess CASCADE;
DROP TABLE Item CASCADE;
DROP TABLE Cond CASCADE;
DROP TABLE Retailer CASCADE;
DROP TABLE Catalog CASCADE;


/* create tables */
CREATE TABLE Catalog (
    id      INT,
    name    VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Retailer (
    id  	INT,
    name    VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Cond (
    id      INT,
    name    VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Item (
	id		        VARCHAR(30),
    name		    VARCHAR(70) NOT NULL,
    price           FLOAT CHECK (0.0 <= price AND price <= 999.99),
    discount 	    FLOAT CHECK (0.0 <= discount AND discount <= 100.0),
    image 		    VARCHAR(100),
    retailer_id	    INT,
    condition_id	INT,
    catalog_id      INT,
	PRIMARY KEY(id),
	FOREIGN KEY(catalog_id) REFERENCES Catalog(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(retailer_id) REFERENCES Retailer(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(condition_id) REFERENCES Cond(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ConsoleAccess (
    console_id  VARCHAR(30),
    access_id   VARCHAR(30),
    PRIMARY KEY(console_id, access_id),
    FOREIGN KEY(console_id) REFERENCES Item(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(access_id) REFERENCES Item(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Orders (
	id	        VARCHAR(60),
    date		CHAR(10),
    delivery 	CHAR(10),
    user		VARCHAR(30),
    status 		VARCHAR(20),
    total_price FLOAT,
    PRIMARY KEY (id)
);

CREATE TABLE OrderHistory (
	order_id	VARCHAR(60),
    item_id		VARCHAR(30),
    FOREIGN KEY(order_id) REFERENCES Orders(id),
    FOREIGN KEY(item_id) REFERENCES Item(id)
);

/* populate catalog */
INSERT INTO catalog (id, name) VALUES (0, "Other");
INSERT INTO catalog (id, name) VALUES (1, "Game");
INSERT INTO catalog (id, name) VALUES (2, "Console");
INSERT INTO catalog (id, name) VALUES (3, "Accessory");
INSERT INTO catalog (id, name) VALUES (4, "Tablet");

/* populate retailer */
INSERT INTO retailer (id, name) VALUES (0, "No Retailer");
INSERT INTO retailer (id, name) VALUES (1, "Microsoft");
INSERT INTO retailer (id, name) VALUES (2, "Sony");
INSERT INTO retailer (id, name) VALUES (3, "Nintendo");
INSERT INTO retailer (id, name) VALUES (4, "Electronic Arts");
INSERT INTO retailer (id, name) VALUES (5, "Activision");
INSERT INTO retailer (id, name) VALUES (6, "Take-Two Interactive");
INSERT INTO retailer (id, name) VALUES (7, "Apple");
INSERT INTO retailer (id, name) VALUES (8, "Samsung");

/* populate condition */
INSERT INTO cond (id, name) VALUES (0, "General");
INSERT INTO cond (id, name) VALUES (1, "New");
INSERT INTO cond (id, name) VALUES (2, "Pre-owned");


/* populate games */
INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ea_fifa", "FIFA 2016", 59.99, 10.0, "ea_fifa.jpg", 4, 1, 1);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ea_nfs", "Need for Speed", 59.99, 15.0, "Need for Speed", 4, 1, 1);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("activision_cod", "Call Of Duty", 54.99, 5.0, "activision_cod.jpg", 5, 1, 1);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("tti_evolve", "Evolve", 49.99, 10.0, "tti_evolve.jpg", 6, 1, 1);

/* popular consoles */
INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xboxone", "Xbox One", 399.99, 0.0, "xbox1.jpg", 1, 1, 2);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xbox360", "Xbox 360", 299.99, 20.0, "xbox360.jpg", 1, 2, 2);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ps4", "PS4", 349.99, 0.0, "PS4-console-bundle.jpg", 2, 1, 2);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("wii", "Wii", 189.99, 20.0, "wii.jpg", 3, 2, 2);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("wiiu", "Wii U", 299.99, 0.0, "wiiu.jpg", 3, 1, 2);

/* popular accessory */
INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xboxone_wc", "Controller", 40.0, 10.0, "XBOX controller.jpg", 1, 1, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xboxone_sh", "Turtle Beach Headset", 50.0, 5.0, "Turtle Beach Headset.jpg", 1, 1, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xbox360_mr", "Speeding Wheel", 40.0, 10.0, "XBOX360-SpeedWheel.jpg", 1, 2, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("xbox360_wa", "Wireless Adapter", 50.0, 5.0, "xbox360_wa.png", 1, 1, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("wii_rp", "Nintendo Wii Remote Plus", 39.99, 20.0, "wii_rp.jpg", 3, 2, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("wiiu_pc", "Nintendo Pro Controller", 49.99, 10.0, "wiiu_pc.jpg", 3, 1, 3);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ps4_wc", "DualShock 4 Wireless Controller", 59.99, 10.0, "ps4_wc.jpg", 2, 1, 3);

/* popular tablets */
INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ipad_mini_4", "iPad mini 4", 399.99, 10.0, "ipad_mini_4.jpg", 7, 1, 4);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("ipad_pro_12", "iPad Pro - 12.9", 799.99, 10.0, "ipad_pro_12.jpg", 7, 1, 4);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("surface_pro_4", "Surface Pro 4", 899.99, 15.0, "surface_pro_4.jpg", 1, 1, 4);

INSERT INTO item
(id, name, price, discount, image, retailer_id, condition_id, catalog_id)
VALUES
("galaxy_tabpro_s", "iPad mini 4", 899.99, 12.0, "galaxy_tabpro_s.png", 8, 1, 4);

/* popular console and accessory */
INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("xboxone", "xboxone_wc");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("xboxone", "xboxone_sh");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("xbox360", "xbox360_mr");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("xbox360", "xbox360_wa");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("wii", "wii_rp");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("wiiu", "wii_rp");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("wiiu", "wiiu_pc");

INSERT INTO ConsoleAccess
(console_id, access_id)
VALUES
("ps4", "ps4_wc");
