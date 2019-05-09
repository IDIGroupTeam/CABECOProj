-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2019 at 09:22 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cabeco`
--

-- --------------------------------------------------------

--
-- Table structure for table `salary_info`
--

CREATE TABLE `salary_info` (
  `EMPLOYEE_ID` int(11) NOT NULL,
  `SALARY` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `BANK_NO` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMMENT` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANK_NAME` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BANK_BRANCH` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `salary_info`
--

INSERT INTO `salary_info` (`EMPLOYEE_ID`, `SALARY`, `BANK_NO`, `COMMENT`, `BANK_NAME`, `BANK_BRANCH`) VALUES
(4, '2.34', '123456789', '', '', ''),
(10, '3.3', '33333333333', '', '', ''),
(16, '4.5', '0011002200330044', '', 'VCB', 'Thang Long'),
(21, '4.0', '001100223456', '', 'VCB', 'Thang Long'),
(38, '3.5', '122432512422', '', 'VCB', 'Hoan kiem'),
(42, '3.6', '32523532', '', 'VCB', ''),
(46, '4.0', '34634642121414', '', 'VCB', ''),
(76, '3.5', '434216634222', '', 'VCB', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `salary_info`
--
ALTER TABLE `salary_info`
  ADD PRIMARY KEY (`EMPLOYEE_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
