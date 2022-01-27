CREATE DATABASE  IF NOT EXISTS `crs_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crs_db`;
--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userid` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` int NOT NULL,
  `coursename` varchar(45) NOT NULL,
  `credit` int NOT NULL,
  `professoralloted` int NOT NULL,
  `prerequisites` varchar(45) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Table structure for table `coursecatalog`

DROP TABLE IF EXISTS `coursecatalog`;
CREATE TABLE `coursecatalog` (
  `coursecatalogid` int NOT NULL,
  `courseid` int NOT NULL,
  PRIMARY KEY (`coursecatalogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `userid` int NOT NULL,
  `amount` varchar(45) NOT NULL,
  `transactionid` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `userid` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `userid` int NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userid` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Age` int NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Contact` varchar(45) NOT NULL,
  `isregistered` int NOT NULL,
  `branch` varchar(45) NOT NULL,
  `paymentstatus` int NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int NOT NULL,
  `password` varchar(45) NOT NULL,
  `isapproved` int NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




ALTER TABLE `crs_db`.`admin` 
ADD CONSTRAINT `adminfk`
  FOREIGN KEY (`userid`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


ALTER TABLE `crs_db`.`professor` 
ADD CONSTRAINT `professorfk`
  FOREIGN KEY (`userid`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `crs_db`.`student` 
ADD CONSTRAINT `studentfk`
  FOREIGN KEY (`userid`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `crs_db`.`role` 
ADD CONSTRAINT `rolefk`
  FOREIGN KEY (`userid`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `crs_db`.`payment` 
ADD CONSTRAINT `paymentfk`
  FOREIGN KEY (`userid`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;



ALTER TABLE `crs_db`.`course` 
ADD INDEX `coursefk_idx` (`professoralloted` ASC) VISIBLE;
;
ALTER TABLE `crs_db`.`course` 
ADD CONSTRAINT `coursefk`
  FOREIGN KEY (`professoralloted`)
  REFERENCES `crs_db`.`user` (`userid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;



ALTER TABLE `crs_db`.`coursecatalog` 
ADD INDEX `coursecatalogfk_idx` (`courseid` ASC) VISIBLE;
;
ALTER TABLE `crs_db`.`coursecatalog` 
ADD CONSTRAINT `coursecatalogfk`
  FOREIGN KEY (`courseid`)
  REFERENCES `crs_db`.`course` (`courseid`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


INSERT INTO user
VALUES (0, "Password2", 1);

INSERT INTO user
VALUES (101, "Password2", 1);

INSERT INTO user
VALUES (102, "Password2", 0);

INSERT INTO user
VALUES (201, "Password2", 1);

INSERT INTO user
VALUES (202, "Password2", 1);

INSERT INTO user
VALUES (301, "Password2", 1);

INSERT INTO user
VALUES (302, "Password2", 1);

SELECT * FROM crs_db.user;


INSERT INTO admin
VALUES (301, "userName1", "Email1", "Address1" ,23, "Male","7983105686");

INSERT INTO admin
VALUES (302, "userName2", "Email2", "Address2" ,24, "Female","7983105686");

SELECT * FROM crs_db.admin;


INSERT INTO student
VALUES (101, "userName1", "Email1", "Address1" ,23, "Male","7983105686",0,"CS",0);

INSERT INTO student
VALUES (102, "userName2", "Email2", "Address2" ,24, "Fenale","7983105686",0,"EC",0);

SELECT * FROM crs_db.student;


INSERT INTO professor
VALUES (201, "userName1", "Email1", "Address1" ,23, "Male","7983105686","CS");

INSERT INTO professor
VALUES (202, "userName2", "Email2", "Address2" ,24, "Fenale","7983105686","EC");

SELECT * FROM crs_db.professor;


INSERT INTO role
VALUES (101, "Student");

INSERT INTO role
VALUES (102, "Student");

INSERT INTO role
VALUES (201, "Professor");

INSERT INTO role
VALUES (202, "Professor");

INSERT INTO role
VALUES (301, "Admin");

INSERT INTO role
VALUES (302, "Admin");

SELECT * FROM crs_db.role;


INSERT INTO course
VALUES (401, "Programming Basic", 5, 201, "Basic Maths");

INSERT INTO course
VALUES (402, "DS", 5, 201, "Programming Basic");

INSERT INTO course
VALUES (403, "Algo", 5, 201, "DS");

INSERT INTO course
VALUES (404, "Digital Electronis", 5, 202, "NA");

INSERT INTO course
VALUES (405, "Signal and systems", 5, 202, "NA");

INSERT INTO course
VALUES (406, "Communication", 5, 202, "NA");

INSERT INTO course
VALUES (407, "DM", 5, 0, "Basic Maths");

INSERT INTO course
VALUES (408, "CN", 5, 0, "Basic Maths");

SELECT * FROM crs_db.course;

ALTER TABLE `crs_db`.`coursecatalog` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`coursecatalogid`, `courseid`);
;


INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('501', '401');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('501', '402');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('501', '403');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('501', '407');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('501', '408');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('502', '404');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('502', '405');
INSERT INTO `crs_db`.`coursecatalog` (`coursecatalogid`, `courseid`) VALUES ('502', '406');

SELECT * FROM crs_db.coursecatalog;


CREATE TABLE `crs_db`.`registeredcourse` (
  `userid` INT NOT NULL,
  `courseid` INT NOT NULL,
  `grade` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userid`, `courseid`),
  INDEX `registeredcoursefk2_idx` (`courseid` ASC) VISIBLE,
  CONSTRAINT `registeredcoursefk1`
    FOREIGN KEY (`userid`)
    REFERENCES `crs_db`.`user` (`userid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `registeredcoursefk2`
    FOREIGN KEY (`courseid`)
    REFERENCES `crs_db`.`course` (`courseid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);





