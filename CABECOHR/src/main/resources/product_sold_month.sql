-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2019 at 11:56 AM
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
-- Table structure for table `product_sold_month`
--

CREATE TABLE `product_sold_month` (
  `CODE` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `MONEY_INCOME` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRICE` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `AMOUNT` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `MONTH` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `COMMENT` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SCALE` varchar(9) COLLATE utf8_unicode_ci DEFAULT '100',
  `DEPARTMENT` varchar(12) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product_sold_month`
--

INSERT INTO `product_sold_month` (`CODE`, `MONEY_INCOME`, `PRICE`, `AMOUNT`, `MONTH`, `COMMENT`, `SCALE`, `DEPARTMENT`) VALUES
('KVC', '57200000', '5500', '104', '2019-01', 'update', '100', 'BH_CSKH'),
('KVC', '39375000', '250', '1500', '2019-02', '', '105', ''),
('KXX', '1120000000', '5600', '2000', '2019-01', 'SUA GIA', '100', 'BH_CSKH'),
('KXX', '1800000000', '3600', '5000', '2019-02', '', '100', ''),
('LVC', '8400000', '150', '560', '2019-02', '', '100', ''),
('NGKHTV', '1265000000', '5500', '2300', '2019-01', '', '100', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product_sold_month`
--
ALTER TABLE `product_sold_month`
  ADD PRIMARY KEY (`CODE`,`MONTH`) USING BTREE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
