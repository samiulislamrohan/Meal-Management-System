-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 18, 2018 at 11:33 AM
-- Server version: 10.0.37-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `heapneverflow_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `Username` varchar(18) NOT NULL,
  `FirstName` varchar(18) NOT NULL,
  `LastName` varchar(18) NOT NULL,
  `Password` varchar(18) NOT NULL,
  `Role` int(1) NOT NULL DEFAULT '2',
  `SUser` varchar(18) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`Username`, `FirstName`, `LastName`, `Password`, `Role`, `SUser`) VALUES
('roslin', 'Roslin Mahmud', 'Joy', 'unlock', 0, 'roslin'),
('kabir', 'Kabir', 'Hossain', 'unlock', 2, 'roslin'),
('rohan', 'Samiul Islam', 'Rohan', 'unlock', 1, 'roslin'),
('a', 'a', 'a', 'a', 2, 'abcd'),
('xyz', 'xyz', 'xyz', 'xyz', 2, 'abcd'),
('abcd', 'abcd', 'abcd', 'abcd', 0, 'abcd'),
('abc', 'abc', 'abc', 'abc', 1, 'abcd'),
('ab', 'ab', 'ab', 'ab', 2, 'abcd'),
('Zahid', 'zahid', 'hasan', '1234', 2, 'roslin'),
('mehedi', 'Mehedi Momen', 'Hasan', 'unlock', 2, 'roslin');

-- --------------------------------------------------------

--
-- Table structure for table `balance`
--

CREATE TABLE `balance` (
  `Username` varchar(18) NOT NULL,
  `Date` date NOT NULL,
  `Payment` double(18,2) NOT NULL DEFAULT '0.00',
  `MarketCost` double(18,2) NOT NULL DEFAULT '0.00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `balance`
--

INSERT INTO `balance` (`Username`, `Date`, `Payment`, `MarketCost`) VALUES
('kabir', '2018-12-12', 125.50, 120.00),
('kabir', '2018-12-13', 100.00, 0.00),
('kabir', '2018-12-14', 200.00, 0.00),
('rohan', '2018-12-14', 150.00, 0.00),
('kabir', '2018-12-16', 150.00, 50.00),
('rohan', '2018-12-16', 200.00, 0.00),
('kabir', '2018-12-17', 120.00, 50.00),
('rohan', '2018-12-17', 100.00, 50.00),
('ab', '2018-12-18', 150.00, 0.00),
('Zahid', '2018-12-18', 190.00, 100.00),
('mehedi', '2018-12-18', 150.00, 30.00),
('roslin', '2018-12-02', 120.00, 0.00),
('roslin', '2018-12-01', 150.00, 0.00),
('abc', '2018-12-18', 100.00, 0.00),
('rohan', '2018-12-18', 100.00, 0.00),
('kabir', '2018-12-18', 50.00, 50.00),
('kabir', '2018-12-01', 125.00, 150.00),
('kabir', '2018-12-02', 100.00, 150.00),
('kabir', '2018-12-03', 110.00, 115.00),
('kabir', '2018-12-04', 120.00, 110.00),
('kabir', '2018-12-05', 140.00, 100.00),
('kabir', '2018-12-06', 100.00, 125.00),
('kabir', '2018-12-07', 125.00, 115.00),
('kabir', '2018-12-08', 120.00, 120.00),
('kabir', '2018-12-09', 105.00, 105.00),
('kabir', '2018-12-10', 110.00, 110.00),
('kabir', '2018-12-11', 125.00, 100.00),
('rohan', '2018-12-01', 150.00, 100.00),
('rohan', '2018-12-02', 140.00, 100.00),
('rohan', '2018-12-03', 125.00, 110.00),
('rohan', '2018-12-04', 100.00, 125.00),
('rohan', '2018-12-05', 105.00, 140.00),
('rohan', '2018-12-06', 105.00, 150.00),
('rohan', '2018-12-07', 125.00, 115.00),
('rohan', '2018-12-08', 145.00, 120.00),
('rohan', '2018-12-09', 110.00, 100.00),
('rohan', '2018-12-10', 125.00, 100.00),
('rohan', '2018-12-11', 150.00, 100.00),
('rohan', '2018-12-12', 150.00, 105.00),
('rohan', '2018-12-13', 105.00, 115.00),
('roslin', '2018-12-18', 100.00, 30.00);

-- --------------------------------------------------------

--
-- Table structure for table `meal`
--

CREATE TABLE `meal` (
  `Username` varchar(18) NOT NULL,
  `Date` date NOT NULL,
  `TotalMeal` int(18) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal`
--

INSERT INTO `meal` (`Username`, `Date`, `TotalMeal`) VALUES
('kabir', '2018-12-12', 2),
('kabir', '2018-12-13', 2),
('kabir', '2018-12-14', 2),
('rohan', '2018-12-14', 2),
('kabir', '2018-12-16', 2),
('rohan', '2018-12-16', 2),
('kabir', '2018-12-17', 2),
('rohan', '2018-12-17', 2),
('abc', '2018-12-18', 2),
('ab', '2018-12-18', 2),
('Zahid', '2018-12-18', 3),
('mehedi', '2018-12-18', 2),
('roslin', '2018-12-02', 2),
('roslin', '2018-12-01', 2),
('rohan', '2018-12-18', 2),
('kabir', '2018-12-18', 2),
('kabir', '2018-12-01', 1),
('kabir', '2018-12-02', 1),
('kabir', '2018-12-03', 2),
('kabir', '2018-12-04', 1),
('kabir', '2018-12-05', 2),
('kabir', '2018-12-06', 2),
('kabir', '2018-12-07', 2),
('kabir', '2018-12-08', 2),
('kabir', '2018-12-09', 2),
('kabir', '2018-12-10', 2),
('kabir', '2018-12-11', 1),
('rohan', '2018-12-01', 1),
('rohan', '2018-12-02', 2),
('rohan', '2018-12-03', 1),
('rohan', '2018-12-04', 2),
('rohan', '2018-12-05', 2),
('rohan', '2018-12-06', 2),
('rohan', '2018-12-07', 2),
('rohan', '2018-12-08', 2),
('rohan', '2018-12-09', 2),
('rohan', '2018-12-10', 2),
('rohan', '2018-12-11', 1),
('rohan', '2018-12-12', 2),
('rohan', '2018-12-13', 2),
('roslin', '2018-12-18', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `balance`
--
ALTER TABLE `balance`
  ADD PRIMARY KEY (`Username`,`Date`);

--
-- Indexes for table `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`Username`,`Date`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
