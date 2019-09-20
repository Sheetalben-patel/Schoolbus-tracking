-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2017 at 12:54 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `schoolbustrackingdb`
--

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
-- Table structure for table `annoucement`
--

CREATE TABLE IF NOT EXISTS `annoucement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(100) NOT NULL,
  `announcement` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `annoucement`
--

INSERT INTO `annoucement` (`id`, `date`, `announcement`) VALUES
(1, '18-03-2017', 'hey'),
(2, '18-03-2017', 'seminar');

-- --------------------------------------------------------

--
-- Table structure for table `attendence`
--

CREATE TABLE IF NOT EXISTS `attendence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(100) NOT NULL,
  `month` varchar(50) NOT NULL,
  `standard` varchar(100) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `present` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `attendence`
--

INSERT INTO `attendence` (`id`, `date`, `month`, `standard`, `fname`, `present`) VALUES
(21, '18/03/2017', 'feb', '2nd', 'patelsheetal1211@gmail.com', '1'),
(22, '2017-03-20', 'jan', '1St', 'krishna@gmail.com', '1'),
(23, '2017-03-20', 'feb', '1St', 'patelsheetal1211@gmail.com', '1'),
(24, '2017-03-20', 'feb', '2nd', 'patelsheetal12@gmail.com', '1'),
(25, '2017-03-27', 'feb', '1St', 'krishna@gmail.com', '1'),
(26, '2017-03-27', 'feb', '1St', 'krishna@gmail.com', '1');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `phone` varchar(10) NOT NULL,
  `busno` varchar(10) NOT NULL,
  `locationAddress` varchar(500) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `phone`, `busno`, `locationAddress`, `date`) VALUES
(1, '9409215457', '8798', 'Latitude: 22.51987656 Longitude: 72.92065799\r\n\r\nAddress:\r\nNew Vallabh Vidyanagar, GIDC\r\nAnand, Gujarat\r\nAnand\r\nnull\r\nIndia', ''),
(2, '7046690038', '8798', 'radhakrishna soc.', '2017-03-23 12:48:56'),
(3, '7046690038', '8798', 'radhakrishna soc.', '2017-03-23 12:59:16'),
(4, '7046690038', '8798', 'New vvn', '2017-03-23 13:01:45'),
(5, '9033431642', '001', 'anand mogri', '2017-04-17 07:35:07'),
(6, '9033431642', '001', 'anand mogri', '2017-04-17 07:35:07');

-- --------------------------------------------------------

--
-- Table structure for table `standard`
--

CREATE TABLE IF NOT EXISTS `standard` (
  `std_id` int(11) NOT NULL AUTO_INCREMENT,
  `standard` varchar(100) NOT NULL,
  PRIMARY KEY (`std_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `standard`
--

INSERT INTO `standard` (`std_id`, `standard`) VALUES
(1, '1St'),
(2, '2nd'),
(3, '3rd'),
(4, '4th');

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
  `session` varchar(100) NOT NULL,
  `studentId` varchar(100) NOT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`st_id`, `fname`, `standard`, `password`, `parents_name`, `phone`, `address`, `email`, `blood_group`, `driver_name`, `driver_number`, `bus_timing`, `bus_number`, `session`, `studentId`) VALUES
(12, 'solanki dvya', '2nd', '12345', 'radha', '8460368720', 'anand road', 'patelsheetal12@gmail.com', 'a+', 'ramu', '7877899090', 'morning', 'A001', '', 'student108'),
(13, 'solanki', '1st', '12345', 'disha', '8460368720', 'ananad road', 'patelsheetal1211@gmail.com', 'a+', 'ramu', '7877899090', 'morning', 'A001', '', 'student337'),
(17, 'divya', '1', '1234567', 'mahesh kumar', '7046690038', 'radhakrishna soc.', 'solankidivya17@gmail.com', 'B+', 'rajubhai', '933491642', '8:00 to 5:00', '001', 'evening', 'student208'),
(18, 'krishna patel', '1St', '1234567', 'asha ben', '6969697878', 'anand', 'krishna@gmail.com', 'B+', 'ramu bhai', '933491642', '8:00 to 5:00', '001', 'Morning', 'student127'),
(19, 'Riya patel', '3rd', '1234567', 'shamir bhai', '123456789', 'A/71 radhakrishna soc.', 'rits772.rp@gmail.com', 'ab+', 'ad', 'ds', 'dsds', 'ds', 'Morning', 'student453');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
