-- Schema Script 
-- Author: Mohammad
-- Setup the database on SQL server


CREATE DATABASE StudentGrade;
GO

USE StudentGrade;
GO

CREATE TABLE Student(
	StudentID INT IDENTITY(1, 1) PRIMARY KEY,
	LastName VARCHAR(55) NOT NULL,
	FirstName VARCHAR(55) NOT NULL,
	Email VARCHAR(100) UNIQUE 
);

CREATE TABLE Classes(
	ClassID INT IDENTITY(1, 1) PRIMARY KEY,
	ClassName VARCHAR(50) NOT NULL,
	ClassDescription VARCHAR(255),
	Credits INT NOT NULL
);


-- Avoid orphan data

CREATE TABLE Grades(
	GradeID INT IDENTITY(1, 1) PRIMARY KEY,
	StudentID INT NOT NULL FOREIGN KEY REFERENCES Student(StudentID) ON DELETE CASCADE,
	ClassID INT NOT NULL FOREIGN KEY REFERENCES Classes(ClassID) ON DELETE CASCADE,
	Score DECIMAL(5, 2),
	FeedBack VARCHAR(50)
);

INSERT INTO Classes (ClassName, ClassDescription, Credits) VALUES
	('Mathematics', 'Calculus and Algebra', 4),
	('English', 'Literature and Composition', 3),
	('Computer Science', 'Intro to Programming', 4),
	('History', 'World History', 3),
	('Biology', 'General Biology', 4);
GO
