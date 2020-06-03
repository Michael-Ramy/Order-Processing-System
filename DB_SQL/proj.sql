Create Schema eBookstore;
use eBookstore;

Create table user(
	username varchar(15) PRIMARY KEY NOT NULl,
    pass varchar(10) NOT NULL,
    firstname varchar(15) NOT NULL,
    lastname varchar(15) not null,
    email varchar(30) not null,
    phone char(10) not null,
    address varchar(40) NOT NULL,
    privilege boolean NOT NULL
);

CREATE TABLE Publisher (
    pname VARCHAR(30) PRIMARY KEY NOT NULL,
    address VARCHAR(40) NOT NULL,
    telephone CHAR(10) NOT NULL
);

CREATE TABLE Book (
    ISBN INT PRIMARY KEY NOT NULL,
    title VARCHAR(20) NOT NULL,
    author VARCHAR(30) NOT NULL,
    publisher VARCHAR(30) NOT NULL,
    publicationYear DATE NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    threshold INT NOT NULL,
    FOREIGN KEY (publisher)
        REFERENCES Publisher (pname)
);

CREATE TABLE CustomersOrders (
    orderId INT PRIMARY KEY NOT NULL,
    userId CHAR(5) NOT NULL,
    purchaseDate DATE NOT NULL,
    purchaseTime TIME NOT NULL,
    amount DOUBLE NOT NULL
);

CREATE TABLE Orders (
    orderId INT NOT NULL,
    bookISBN INT NOT NULL,
    PRIMARY KEY (orderId , bookISBN),
    FOREIGN KEY (orderId)
        REFERENCES CustomersOrders (orderId),
    FOREIGN KEY (bookISBN)
        REFERENCES Book (ISBN)
);

CREATE TABLE BooksOrders (
    bookISBN INT PRIMARY KEY NOT NULL,
    orderDate DATE NOT NULL,
    orderTime TIME NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (bookISBN)
        REFERENCES Book (ISBN)
);

