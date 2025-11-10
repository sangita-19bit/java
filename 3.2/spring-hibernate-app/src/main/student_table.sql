-- student_table.sql (Part B)
CREATE TABLE students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INT
);


-- account_table.sql (Part C)
CREATE TABLE accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  accountNumber VARCHAR(50) NOT NULL UNIQUE,
  balance DOUBLE NOT NULL
);


-- transaction_table.sql (Part C)
CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fromAccount VARCHAR(50),
  toAccount VARCHAR(50),
  amount DOUBLE,
  createdAt DATETIME
);
