CREATE TABLE IF NOT EXISTS student (
    rollno INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    marks INT CHECK (marks >= 0 AND marks <= 100)
);
