-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2022 at 08:28 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `students`
--

-- --------------------------------------------------------

--
-- Table structure for table `campus`
--

CREATE TABLE `campus` (
  `campus_code` int(15) NOT NULL,
  `county` varchar(70) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `venue_code` int(32) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `capacity` int(10) DEFAULT NULL,
  `available` int(4) DEFAULT NULL,
  `campus_code` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`venue_code`, `class_name`, `capacity`, `available`, `campus_code`) VALUES
(1, '1A', 20, 0, 1),
(2, '280B6', 80, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_code` int(15) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `lecturer_code` int(27) DEFAULT NULL,
  `venue_code` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_code`, `course_name`, `lecturer_code`, `venue_code`) VALUES
(1, 'Degree in Information Technology', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `lecturer_code` int(27) NOT NULL,
  `lecturer_name` varchar(255) DEFAULT NULL,
  `salary` int(8) DEFAULT NULL,
  `course_code` int(15) DEFAULT NULL,
  `unit_code` int(32) DEFAULT NULL,
  `campus_code` int(15) DEFAULT NULL,
  `user_code` int(23) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`lecturer_code`, `lecturer_name`, `salary`, `course_code`, `unit_code`, `campus_code`, `user_code`) VALUES
(1, 'Mr Onzere', 70000, NULL, NULL, NULL, NULL),
(2, 'Mrs Were', 785999, NULL, NULL, NULL, NULL),
(3, 'Ms Lady', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_code` int(32) NOT NULL,
  `studname` varchar(255) DEFAULT NULL,
  `course_code` int(15) DEFAULT NULL,
  `campus_code` int(15) DEFAULT NULL,
  `academic_year` int(3) DEFAULT NULL,
  `stud_email` varchar(50) DEFAULT NULL,
  `user_code` int(23) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE `units` (
  `unit_code` int(32) NOT NULL,
  `unit_name` varchar(255) DEFAULT NULL,
  `duration` int(32) DEFAULT NULL,
  `course_code` int(15) DEFAULT NULL,
  `campus_code` int(15) DEFAULT NULL,
  `lecturer_code` int(27) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_code` int(23) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `u_type` int(4) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `campus`
--
ALTER TABLE `campus`
  ADD PRIMARY KEY (`campus_code`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`venue_code`),
  ADD KEY `campus_code` (`campus_code`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_code`),
  ADD KEY `venue_code` (`venue_code`),
  ADD KEY `lecturer_code` (`lecturer_code`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`lecturer_code`),
  ADD KEY `course_code` (`course_code`),
  ADD KEY `unit_code` (`unit_code`),
  ADD KEY `user_code` (`user_code`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_code`),
  ADD KEY `campus_code` (`campus_code`),
  ADD KEY `user_code` (`user_code`),
  ADD KEY `course_code` (`course_code`);

--
-- Indexes for table `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`unit_code`),
  ADD KEY `campus_code` (`campus_code`),
  ADD KEY `lecturer_code` (`lecturer_code`),
  ADD KEY `course_code` (`course_code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `campus`
--
ALTER TABLE `campus`
  MODIFY `campus_code` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `venue_code` int(32) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `course_code` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `lecturer_code` int(27) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_code` int(32) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `units`
--
ALTER TABLE `units`
  MODIFY `unit_code` int(32) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_code` int(23) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`campus_code`) REFERENCES `campus` (`campus_code`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `lecturer_code` FOREIGN KEY (`lecturer_code`) REFERENCES `lecturer` (`lecturer_code`),
  ADD CONSTRAINT `venue_code` FOREIGN KEY (`venue_code`) REFERENCES `class` (`venue_code`);

--
-- Constraints for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD CONSTRAINT `lecturer_ibfk_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`),
  ADD CONSTRAINT `user_code` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `course_code` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`),
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`campus_code`) REFERENCES `campus` (`campus_code`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`);

--
-- Constraints for table `units`
--
ALTER TABLE `units`
  ADD CONSTRAINT `units_ibfk_1` FOREIGN KEY (`campus_code`) REFERENCES `campus` (`campus_code`),
  ADD CONSTRAINT `units_ibfk_2` FOREIGN KEY (`lecturer_code`) REFERENCES `lecturer` (`lecturer_code`),
  ADD CONSTRAINT `units_ibfk_3` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
