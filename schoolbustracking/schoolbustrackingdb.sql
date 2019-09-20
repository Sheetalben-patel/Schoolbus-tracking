-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 20, 2016 at 08:44 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `schoolbustrackingdb1`
--
CREATE DATABASE IF NOT EXISTS `schoolbustrackingdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `schoolbustrackingdb`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `st_id` int(10) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  `standard` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `parents_name` varchar(100) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `blood_group` varchar(100) NOT NULL,
  `driver_name` varchar(100) NOT NULL,
  `driver_number` varchar(100) NOT NULL,
  `bus_timing` varchar(100) NOT NULL,
  `bus_number` varchar(100) NOT NULL,
  `studentId` varchar(100) NOT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`st_id`, `fname`, `standard`, `password`, `parents_name`, `phone`, `address`, `email`, `blood_group`, `driver_name`, `driver_number`, `bus_timing`, `bus_number`, `studentId`) VALUES
(1, 'aaaaaaaaaaaa', '', '', '', '', '', '', '', '', '', '', '', ''),
(11, 'solanki', '1', '123', 'ssssss', '123455', 'aaa', 'solankidivya17@gmail.com', 'a', 'a', '1', '1', 'a', 'student154'),
(12, 'solanki dvya', '2nd', '12345', 'radha', '8460368720', 'anand road', 'patelsheetal12@gmail.com', 'a+', 'ramu', '7877899090', 'morning', 'A001', 'student108'),
(13, 'solanki', '1st', '12345', 'disha', '8460368720', 'ananad road', 'patelsheetal1211@gmail.com', 'a+', 'ramu', '7877899090', 'morning', 'A001', 'student337');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
