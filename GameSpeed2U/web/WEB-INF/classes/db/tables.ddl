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
    date		    CHAR(10),
    delivery 	  CHAR(10),
    user		    VARCHAR(30),
    status 		  VARCHAR(20),
    total_price FLOAT,
    PRIMARY KEY (id)
);

CREATE TABLE OrderHistory (
	order_id	VARCHAR(60),
    item_id		VARCHAR(30),
    FOREIGN KEY(order_id) REFERENCES Orders(id),
    FOREIGN KEY(item_id) REFERENCES Item(id)
);