-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2019 at 08:58 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_user_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_account`
--

CREATE TABLE `tb_m_account` (
  `id` varchar(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `token` varchar(100) NOT NULL,
  `status` int(1) NOT NULL,
  `verif_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_account`
--

INSERT INTO `tb_m_account` (`id`, `password`, `token`, `status`, `verif_time`) VALUES
('14045', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '-', 0, '2019-10-01 00:00:00'),
('14422', '$2a$10$9C/v7rDyVpDGMBvmKNTxAOPHHZhsOgfeEaudJmanmdgcJlY7duxiq', '-', 0, '2019-10-01 00:00:00'),
('15523', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '-', 0, '2019-09-17 00:00:00'),
('15525', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '-', 0, '2019-09-17 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_account_status`
--

CREATE TABLE `tb_m_account_status` (
  `id` int(1) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_account_status`
--

INSERT INTO `tb_m_account_status` (`id`, `name`) VALUES
(-2, 'Account Deleted'),
(-1, 'Not Verified'),
(0, 'Verified'),
(1, 'Once Login Failed'),
(2, 'Twice Login Failed'),
(3, 'Account Locked');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_employee`
--

CREATE TABLE `tb_m_employee` (
  `id` varchar(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birth_place` date NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `nationality` enum('WNI','WNA') NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  `manager` varchar(11) NOT NULL,
  `department` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_employee`
--

INSERT INTO `tb_m_employee` (`id`, `first_name`, `last_name`, `email`, `birth_place`, `birth_date`, `gender`, `nationality`, `is_delete`, `manager`, `department`) VALUES
('14045', 'Super', 'Admin', 'mii.bootcamp29@gmail.com', '2019-10-01', '2019-10-01', 'Male', 'WNI', 0, '14045', 'ADD'),
('14422', 'Dev', 'Erliando', 'dav3rliando@gmail.com', '2019-10-01', '2019-09-01', 'Male', 'WNI', 0, '14045', 'ADD1'),
('15523', 'Muhammad', 'Affanul Halim', 'axffan@gmail.com', '2019-10-01', '2001-01-01', 'Male', 'WNI', 0, '14045', 'ADD2'),
('15525', 'Wirya', 'Likwan Ibrahim', 'wehijin@gmail.com', '2019-10-01', '0001-01-01', 'Male', 'WNI', 0, '14045', 'ADD2');

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_role`
--

CREATE TABLE `tb_m_role` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_role`
--

INSERT INTO `tb_m_role` (`id`, `name`) VALUES
('ADM', 'Super Admin'),
('EMP', 'Employee'),
('MGR', 'Manager'),
('TRN', 'Trainer');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_employee_role`
--

CREATE TABLE `tb_tr_employee_role` (
  `id` int(11) NOT NULL,
  `employee` varchar(11) NOT NULL,
  `role` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_tr_employee_role`
--

INSERT INTO `tb_tr_employee_role` (`id`, `employee`, `role`) VALUES
(1, '14045', 'ADM'),
(2, '15523', 'EMP'),
(3, '14045', 'EMP'),
(4, '14422', 'TRN'),
(5, '14422', 'EMP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_m_account`
--
ALTER TABLE `tb_m_account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `status` (`status`);

--
-- Indexes for table `tb_m_account_status`
--
ALTER TABLE `tb_m_account_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_employee`
--
ALTER TABLE `tb_m_employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `manager` (`manager`);

--
-- Indexes for table `tb_m_role`
--
ALTER TABLE `tb_m_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee` (`employee`),
  ADD KEY `role` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_m_account`
--
ALTER TABLE `tb_m_account`
  ADD CONSTRAINT `tb_m_account_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_m_employee` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_m_account_ibfk_2` FOREIGN KEY (`status`) REFERENCES `tb_m_account_status` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_m_employee`
--
ALTER TABLE `tb_m_employee`
  ADD CONSTRAINT `tb_m_employee_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `tb_m_employee` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `tb_m_employee` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_2` FOREIGN KEY (`role`) REFERENCES `tb_m_role` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
