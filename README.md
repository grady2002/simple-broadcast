# simple-broadcast

A simple broadcast application written in Java with MySQL as the database..

# DB Setup

```sql
CREATE DATABASE broadcast;
CREATE TABLE main (
     id INT AUTO_INCREMENT PRIMARY KEY,
     sender VARCHAR(20),
     broadcast VARCHAR(255)
);
```
