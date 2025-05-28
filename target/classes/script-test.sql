
DROP DATABASE IF EXISTS poseidon_test;

CREATE DATABASE IF NOT EXISTS poseidon_test;

USE poseidon_test;

CREATE TABLE BidList (
  BidListId INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DECIMAL(11,1) NOT NULL,
  askQuantity DOUBLE,
  bid DOUBLE,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidListId)
);

CREATE TABLE Trade (
  TradeId INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

CREATE TABLE CurvePoint (
  Id INT NOT NULL AUTO_INCREMENT,
  CurveId INT NOT NULL,
  asOfDate TIMESTAMP,
  term DOUBLE,
  value DOUBLE,
  creationDate TIMESTAMP,

  PRIMARY KEY (Id)
);

CREATE TABLE Rating (
  Id INT NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125) NOT NULL,
  sandPRating VARCHAR(125) NOT NULL,
  fitchRating VARCHAR(125) NOT NULL,
  orderNumber INT NOT NULL,
  creationDate TIMESTAMP,
  
  PRIMARY KEY (Id)
);

CREATE TABLE RuleName (
  Id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(125) NOT NULL,
  description VARCHAR(125) NOT NULL,
  json VARCHAR(125) NOT NULL,
  template VARCHAR(512) NOT NULL,
  sqlStr VARCHAR(125) NOT NULL,
  sqlPart VARCHAR(125) NOT NULL,
  creationDate TIMESTAMP,
  
  PRIMARY KEY (Id)
);
CREATE TABLE users (
    Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(125) NOT NULL,
    password VARCHAR(125) NOT NULL,
    fullname VARCHAR(125) NOT NULL,
    role VARCHAR(125) DEFAULT NULL
);
