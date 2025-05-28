USE poseidon_test;

-- Insert dans Users (test)
INSERT INTO Users (username, password, fullname, role) VALUES
('admin_user', 'AdminPass123', 'Admin User', 'ADMIN'),
('regular_user', 'UserPass456', 'Regular User', 'USER');


-- Insert dans BidList (test)
INSERT INTO BidList (account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, status, security, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES 
('TestAccount1', 'TypeA', 1000.5, 1200.75, 99.90, 100.10, 'BenchmarkValue1', '2025-05-20 10:00:00', 'First test entry', 'OPEN', 'SecurityA', 'TraderX', 'Book123', 'Admin', '2025-05-01 12:00:00', 'Manager1', '2025-05-15 14:00:00', 'DealX', 'Type1', 'SRC123', 'BUY'),
('TestAccount2', 'TypeB', 500.0, 600.25, 50.50, 51.00, 'BenchmarkValue2', '2025-05-21 14:30:00', 'Second test entry', 'CLOSED', 'SecurityB', 'TraderY', 'Book456', 'User1', '2025-04-30 08:30:00', 'Manager2', '2025-05-10 10:30:00', 'DealY', 'Type2', 'SRC456', 'SELL');


-- Insert dans Trade (test)
INSERT INTO Trade (account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side) VALUES
('TestAccount1', 'TypeA', 1000, NULL, 100.0, NULL, '2025-05-20 10:00:00', NULL, 'EXECUTED', NULL, NULL, NULL, 'Admin', '2025-05-01 12:00:00', NULL, NULL, NULL, NULL, NULL, NULL),
('TestAccount2', 'TypeB', NULL, 700, NULL, 50.5, '2025-05-21 14:30:00', NULL, 'EXECUTED', NULL, NULL, NULL, 'User1', '2025-04-30 08:30:00', NULL, NULL, NULL, NULL, NULL, NULL);


-- Insert dans CurvePoint (test)
INSERT INTO CurvePoint (CurveId, asOfDate, term, value, creationDate) VALUES
(1, '2025-05-20 00:00:00', 1.0, 0.5, '2025-05-01 12:00:00'),
(1, '2025-05-20 00:00:00', 5.0, 1.5, '2025-05-10 14:00:00');

-- Insert dans Rating (test)
INSERT INTO Rating (moodysRating, sandPRating, fitchRating, orderNumber, creationDate) VALUES
('Aaa', 'AAA', 'AAA', 1, '2025-05-01 12:00:00'),
('Baa2', 'BBB', 'BBB+', 2, '2025-05-10 14:00:00');

-- Insert dans RuleName (test)
INSERT INTO RuleName (name, description, json, template, sqlStr, sqlPart, creationDate) VALUES
('Rule1', 'Minimum amount validation', '{"min":100}', 'Template1', 'SELECT * FROM table WHERE amount > 100', 'amount > 100', '2025-05-01 12:00:00'),
('Rule2', 'Open status check', '{"status":"OPEN"}', 'Template2', 'SELECT * FROM table WHERE status = "OPEN"', 'status = "OPEN"', '2025-05-10 14:00:00');



