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
-- Table structure for table `salary_detail`
--

CREATE TABLE `salary_detail` (
  `EMPLOYEE_ID` int(11) NOT NULL,
  `ACTUAL_SALARY` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OVER_TIME_N` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OVER_TIME_W` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OVER_TIME_H` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BOUNUS` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUBSIDIZE` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADVANCE_PAYED` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TAX_PERSONAL` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MONTH` int(4) NOT NULL,
  `YEAR` int(4) NOT NULL,
  `COMMENT` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OVER_TIME_SALARY` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PAYED_INSURANCE` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WORK_COMPLETE` int(4) DEFAULT '100',
  `BASIC_SALARY` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `WORKED_DAY` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OTHER` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ARREARS` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PAY_STATUS` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUB_LUNCH` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUB_GAS` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUB_PHONE` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OVER_WORK` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TOTAL_INCOME` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TOTAL_REDUCE` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MAINTAIN_SALARY` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `R_SALARY` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `salary_detail`
--

INSERT INTO `salary_detail` (`EMPLOYEE_ID`, `ACTUAL_SALARY`, `OVER_TIME_N`, `OVER_TIME_W`, `OVER_TIME_H`, `BOUNUS`, `SUBSIDIZE`, `ADVANCE_PAYED`, `TAX_PERSONAL`, `MONTH`, `YEAR`, `COMMENT`, `OVER_TIME_SALARY`, `PAYED_INSURANCE`, `WORK_COMPLETE`, `BASIC_SALARY`, `WORKED_DAY`, `OTHER`, `ARREARS`, `PAY_STATUS`, `SUB_LUNCH`, `SUB_GAS`, `SUB_PHONE`, `OVER_WORK`, `TOTAL_INCOME`, `TOTAL_REDUCE`, `MAINTAIN_SALARY`, `R_SALARY`) VALUES
(4, '3732761.0', '1', '1', '1', '200000', '100000', '', '', 5, 2019, 'testing data', '118034.0', '926625.0', 100, '3486599.8', '23', '100000', '', '', '500000', '100000', '100000', '100000', '4659386.0', '926625.0', '5', '23.0'),
(4, '2559979.8', '', '', '', '', '', '', '', 12, 2018, '', '0.0', '926625.0', 100, '3486599.8', '22', '', '', 'Đã trả lương', '', '', '', '', '3486604.8', '926625.0', '5', NULL),
(10, '4000000.0', '', '', '', '2000000', '', '', '', 3, 2019, '', '0.0', NULL, 100, '2000000', '', '', '', '', '', '', '', '', '4000000.0', '0.0', '', NULL),
(10, '2000000.0', '', '', '', '', '', '', '', 4, 2019, '', '0.0', NULL, 100, '2000000', '', '', '', '', '', '', '', '', '2000000.0', '0.0', '', NULL),
(10, '2000000.0', '', '', '', '', '', '', '', 5, 2019, '', '0.0', NULL, 100, '2000000', '', '', '', NULL, '', '', '', '', '2000000.0', '0.0', '', NULL),
(10, '5350000.0', '', '', '', '2000000', '', '', '', 12, 2018, '', '0.0', NULL, 100, '2000000', '', '', '500000', '', '500000', '250000', '100000', '', '5850000.0', '500000.0', '1000000', NULL),
(16, '6140394.0', '', '', '', '500000', '100000', '', '', 5, 2019, '', '0.0', '459606.0', 100, '6000000', '', '', '', '', '', '', '', '', '6600000.0', '459606.0', '', NULL),
(21, '7260000.0', '', '', '', '', '', '200000', '', 1, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '200000.0', '', NULL),
(21, '7260000.0', '', '', '', '', '', '200000', '', 2, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '200000.0', '', NULL),
(21, '7460000.0', '', '', '', '', '', '', '', 3, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '0.0', '', NULL),
(21, '6860000.0', '', '', '', '', '', '600000', '', 4, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '600000.0', '', NULL),
(21, '7260000.0', '', '', '', '', '', '200000', '', 5, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '200000.0', '', NULL),
(21, '7260022.0', '', '', '', '', '', '200000', '', 10, 2018, '', '0.0', NULL, 100, '5960000.0', '22', '', '', '', '500000', '', '', '1000000', '7460022.0', '200000.0', '', '22.0'),
(21, '7360000.0', '', '', '', '', '', '200000', '', 11, 2018, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '100000', '1000000', '7560000.0', '200000.0', '', NULL),
(21, '7260000.0', '', '', '', '', '', '200000', '', 12, 2018, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '500000', '', '', '1000000', '7460000.0', '200000.0', '', NULL),
(38, '5815000.0', '2', '2', '2', '', '', '', '', 3, 2019, '', '0.0', NULL, 100, '5215000.0', '', '', '', '', '500000', '', '100000', '', '5815000.0', '0.0', '', NULL),
(38, '5568472.0', '2', '2', '3', '', '', '600000', '', 4, 2019, '', '453472.0', NULL, 100, '5215000.0', '', '', '', '', '500000', '', '', '', '6168472.0', '600000.0', '', NULL),
(38, '6086610.0', '2', '2', '1', '', '', '', '', 5, 2019, '', '271610.0', NULL, 100, '5215000.0', '', '', '', '', '500000', '', '100000', '', '6086610.0', '0.0', '', NULL),
(42, '6264000.0', '1', '2', '3', '', '', '600000', '', 3, 2019, '', '0.0', NULL, 100, '3.6', '', '', '', '', '500000', '', '', '', '6864000.0', '600000.0', '1000000', NULL),
(42, '5611792.0', '1', '2', '1', '', '', '', '', 4, 2019, '', '247792.0', NULL, 100, '5364000.0', '', '', '', '', '', '', '', '', '5611792.0', '0.0', '', NULL),
(42, '6264000.0', '1', '2', '3', '', '', '600000', '', 5, 2019, '', '0.0', NULL, 100, '5364000.0', '', '', '', '', '500000', '', '', '', '6864000.0', '600000.0', '1000000', NULL),
(46, '6560000.0', '', '', '', '500000', '', '200000', '', 4, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '300000', '', '', '', '6760000.0', '200000.0', '', NULL),
(46, '6260000.0', '', '', '', '500000', '', '200000', '', 5, 2019, '', '0.0', NULL, 100, '5960000.0', '', '', '', '', '', '', '', '', '6460000.0', '200000.0', '', NULL),
(76, '5215000.0', '1', '1', '1', '', '', '', '', 5, 2019, '', '0.0', NULL, 100, '5215000.0', '', '', '', '', '', '', '', '', '5215000.0', '0.0', '', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `salary_detail`
--
ALTER TABLE `salary_detail`
  ADD PRIMARY KEY (`EMPLOYEE_ID`,`MONTH`,`YEAR`) USING BTREE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
