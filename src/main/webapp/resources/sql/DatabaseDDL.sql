CREATE TABLE author
(
  authorId INT AUTO_INCREMENT
    PRIMARY KEY,
  author1  VARCHAR(30) NULL,
  author2  VARCHAR(30) NULL,
  author3  VARCHAR(30) NULL,
  author4  VARCHAR(30) NULL
);

CREATE TABLE book
(
  bookId         INT AUTO_INCREMENT
    PRIMARY KEY,
  coverImage     VARCHAR(30)    NULL,
  price          DOUBLE         NULL,
  bookTitle      VARCHAR(250)   NULL,
  categoryId     INT            NULL,
  publisher      VARCHAR(30)    NULL,
  ISBN           VARCHAR(30)    NULL,
  lang           VARCHAR(20)    NULL,
  details        VARCHAR(20000) NULL,
  authorId       INT            NULL,
  bookQuantity   INT            NULL,
  inventoryStock INT            NULL
);

CREATE TABLE bookxauthor
(
  bookId   INT NULL,
  authorId INT NULL,
  id       INT AUTO_INCREMENT
    PRIMARY KEY
);

CREATE TABLE cart
(
  bookId       INT                                 NULL,
  bookQuantity INT                                 NULL,
  globalId     VARCHAR(36)                         NOT NULL
    PRIMARY KEY,
  creationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  machineName  VARCHAR(5000)                       NULL
);

CREATE TABLE category
(
  categoryId INT AUTO_INCREMENT
    PRIMARY KEY,
  category   VARCHAR(30) NULL
);

CREATE TABLE customer
(
  customerId  INT AUTO_INCREMENT
    PRIMARY KEY,
  firstName   VARCHAR(50)  NULL,
  lastName    VARCHAR(50)  NULL,
  address     VARCHAR(60)  NULL,
  city        VARCHAR(20)  NULL,
  zip         VARCHAR(10)  NULL,
  country     VARCHAR(20)  NULL,
  phoneHome   VARCHAR(20)  NULL,
  phoneMobile VARCHAR(20)  NULL,
  email       VARCHAR(50)  NULL,
  login       VARCHAR(255) NULL
);

CREATE TABLE ordertable
(
  orderId    INT AUTO_INCREMENT
    PRIMARY KEY,
  customerId INT         NULL,
  orderDate  DATETIME    NULL,
  globalId   VARCHAR(36) NULL,
  bookId     INT         NULL
);

CREATE TABLE persistent_logins
(
  username  VARCHAR(64)                         NOT NULL,
  series    VARCHAR(64)                         NOT NULL
    PRIMARY KEY,
  token     VARCHAR(64)                         NOT NULL,
  last_used TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE user_roles
(
  user_role_id INT AUTO_INCREMENT
    PRIMARY KEY,
  username     VARCHAR(45) NOT NULL,
  role         VARCHAR(45) NOT NULL,
  CONSTRAINT uni_username_role
  UNIQUE (role, username),
  CONSTRAINT fk_username
  FOREIGN KEY (username) REFERENCES users (username)
);
CREATE INDEX fk_username_idx
  ON user_roles (username);

CREATE TABLE users
(
  username VARCHAR(45)         NOT NULL
    PRIMARY KEY,
  password VARCHAR(60)         NOT NULL,
  enabled  TINYINT DEFAULT '1' NOT NULL,
  role     VARCHAR(255)        NULL
);