USE poseidon_prod;

-- Insert into Users
INSERT INTO Users(fullname, username, password, role) values
("Test Admin", "admin", "$2a$10$dJhDgfA1FgSQHS7yInAwS.1QwTw0dx68lG2YM3VMK8jXIqBErZVTu", "ADMIN");

INSERT INTO Users(fullname, username, password, role) values
("John Doe", "johndoe", "$2a$10$ih8/a/idN6W01Q/v6hW0QeHpeQeQZWLgiAqfq/GTn29ahzqVIb/2O", "USER");

-- Insert into BidList
INSERT INTO BidList (
  account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, 
  security, status, trader, book, creationName, creationDate, revisionName, revisionDate, 
  dealName, dealType, sourceListId, side
) VALUES
('Account1', 'TypeA', 1000.0, 1200.0, 99.5, 100.5, 'Benchmark1', '2025-05-20 10:00:00', 'Comment 1',
 'SecurityA', 'OPEN', 'TraderJoe', 'Book1', 'Admin', NOW(), 'Admin', NOW(), 'DealX', 'Type1', 'Src01', 'BUY');

-- Insert into Trade
INSERT INTO Trade (
  account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate,
  security, status, trader, benchmark, book,
  creationName, creationDate, revisionName, revisionDate,
  dealName, dealType, sourceListId, side
) VALUES
('Account1', 'TypeA', 1000, NULL, 100.0, NULL, NOW(),
 'SecurityA', 'EXECUTED', 'TraderJoe', 'Benchmark1', 'Book1',
 'Admin', NOW(), 'Admin', NOW(), 'DealX', 'Type1', 'Src01', 'BUY');

-- Insert into CurvePoint
INSERT INTO CurvePoint (
  CurveId, asOfDate, term, value, creationDate
) VALUES
(1, NOW(), 1.0, 0.5, NOW());

-- Insert into Rating
INSERT INTO Rating (
  moodysRating, sandPRating, fitchRating, orderNumber, creationDate
) VALUES
('Aaa', 'AAA', 'AAA', 1, NOW());


-- Insert into RuleName
INSERT INTO RuleName (
  name, description, json, template, sqlStr, sqlPart, creationDate
) VALUES
('Test Rule', 'Simple test rule description', '{"min": 100}', 'Template content here',
 'SELECT * FROM trade', 'WHERE amount > 100', NOW());

