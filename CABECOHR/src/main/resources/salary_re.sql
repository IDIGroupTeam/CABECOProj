-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 05, 2019 at 09:47 AM
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
-- Table structure for table `salary_re`
--

CREATE TABLE `salary_re` (
  `MONTH` int(2) NOT NULL,
  `YEAR` int(4) NOT NULL,
  `DEPARTMENT` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `salary_re`
--

INSERT INTO `salary_re` (`MONTH`, `YEAR`, `DEPARTMENT`, `VALUE`, `DESCRIPTION`) VALUES
(6, 2019, 'BH_CSKH', '5000', ''),
(7, 2019, 'BH_CSKH', '5500', ''),
(7, 2019, 'KHVT', '4500', 'updated');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `salary_re`
--
ALTER TABLE `salary_re`
  ADD PRIMARY KEY (`MONTH`,`YEAR`,`DEPARTMENT`) USING BTREE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
