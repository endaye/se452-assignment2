DROP TABLE CustomerOrder CASCADE;
CREATE TABLE Orders (
	  id	        VARCHAR(60),
    date		    CHAR(10),
    delivery 	  CHAR(10),
    user		    VARCHAR(30),
    status 		  VARCHAR(20),
    total_price FLOAT,
    items       VARCHAR(500),
    PRIMARY KEY (id)
);

INSERT INTO CustomerOrders
(id, date, delivery, user, status, total_price, items)
VALUES
  ("2016-05-26-AD-11-46-14-CDT-CUSTOMER", "05/26/2016", "06/09/2016", "customer", "comfirmed", 399.99, "xbox360");

INSERT INTO CustomerOrders
(id, date, delivery, user, status, total_price, items)
VALUES
  ("2016-05-26-AD-15-29-53-CDT-CUSTOMER", "05/26/2016", "06/09/2016", "customer", "comfirmed", 1349.97, "tti_evolve xboxone surface_pro_4");

DELETE FROM CustomerOrders
WHERE id = "2016-05-26-AD-16-51-41-CDT-CUSTOMER";
