-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2019 at 09:01 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

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

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_employee` (INOUT `id2` VARCHAR(10))  NO SQL
BEGIN
	DELETE FROM tb_tr_employee_role
    WHERE employee = id2;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_employee` ()  BEGIN
	SELECT * FROM tb_m_employee;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_account`
--

CREATE TABLE `tb_m_account` (
  `id` varchar(11) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `token` varchar(100) NOT NULL,
  `status` int(1) NOT NULL DEFAULT -1,
  `verif_time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_account`
--

INSERT INTO `tb_m_account` (`id`, `password`, `token`, `status`, `verif_time`) VALUES
('14002', '$2a$10$DFaly43qvMOL9X.ZpZnppuXA3QcXvi7iaGJv7GNLqbrQmlY2vks9q', '14002', 0, '2019-10-18 09:59:54'),
('14004', NULL, '14004', -1, '2019-10-21 14:17:01'),
('14045', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '14045', 0, '2019-10-01 00:00:00'),
('14422', '$2a$10$DFaly43qvMOL9X.ZpZnppuXA3QcXvi7iaGJv7GNLqbrQmlY2vks9q', '0858dc21-916e-48da-af22-80ef65d01e74', 0, '2019-10-16 15:34:05'),
('14504', NULL, '14504', -1, '2019-10-21 14:27:18'),
('15523', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '15523', 0, '2019-09-17 00:00:00'),
('15525', '$2a$10$BROCSakMYA3JOiTRf9AwBehzDzSIUzzMmNx14t0ufMraDfp1UpkIa', '15525', 0, '2019-09-17 00:00:00'),
('54321', NULL, '54321', -1, '2019-10-21 14:23:09');

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
-- Table structure for table `tb_m_application`
--

CREATE TABLE `tb_m_application` (
  `id` int(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  `token` varchar(250) NOT NULL,
  `base_url` varchar(50) NOT NULL,
  `is_active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_m_application`
--

INSERT INTO `tb_m_application` (`id`, `name`, `token`, `base_url`, `is_active`) VALUES
(2, 'Asset Management', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBc3NldCBNYW5hZ2VtZW50IiwiaWF0IjoxNTcxMjEwMzQ0fQ.egQGVL6fHVvPnann4tvJlDR-4N7Pg8J-KC9hhbqa0w90ulWKya2sQUpIVQyqghy4iwBAmQu1fkVopr3eFPk34A', 'http://unknown:8084/', 1),
(3, 'Apa Management', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBcGEgTWFuYWdlbWVudCIsImlhdCI6MTU3MTIxMDYzNn0.jyIWeFpuWF5YS74BLqBtinEQtd7BG6DfVZyvmW-l2KPUekCr7pozLZ6UD7iveLIEzOAkTKB_rWFi4kM0xfnGiA', 'http://unknown:8084/', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_m_employee`
--

CREATE TABLE `tb_m_employee` (
  `id` varchar(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birth_place` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `nationality` enum('WNI','WNA') NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  `manager` varchar(11) DEFAULT NULL,
  `department` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_m_employee`
--

INSERT INTO `tb_m_employee` (`id`, `first_name`, `last_name`, `email`, `birth_place`, `birth_date`, `gender`, `nationality`, `is_delete`, `manager`, `department`) VALUES
('14002', 'test', 'test', 'testest@gmail.com', 'a', '2019-10-01', 'Male', 'WNI', 0, '14045', 'ADD1'),
('14004', 'tes2t', 'test', 'tes2tes2t@gmail.com', 'a', '2019-10-01', 'Male', 'WNI', 0, '14045', 'ADD1'),
('14045', 'Super', 'Admin', 'mii.bootcamp29@gmail.com', 'Unknown', '2019-10-01', 'Male', 'WNI', 0, NULL, 'ADD'),
('14422', 'Dev', 'Erliando', 'dav3rliando@gmail.com', 'Bandung', '2019-09-01', 'Male', 'WNI', 0, '14045', 'ADD1'),
('14504', 'Armanda', 'Dimas', 'armandadimas@live.com', 'Jakarta', '2019-05-08', 'Male', 'WNI', 0, '14045', 'ADD1'),
('15523', 'Muhammad', 'Affanul Halim', 'axffan@gmail.com', 'Nan Jauh Di mato', '2001-01-01', 'Male', 'WNI', 0, '14045', 'ADD2'),
('15525', 'Wirya', 'Likwan Ibrahim', 'wehijin@gmail.com', 'Jauh Sekali', '0001-01-01', 'Male', 'WNI', 0, '14045', 'ADD2'),
('54321', 'coba', 'coba', 'testest2@gmail.com', 'asd', '2019-10-01', 'Male', 'WNI', 0, '14045', 'ADD1');

--
-- Triggers `tb_m_employee`
--
DELIMITER $$
CREATE TRIGGER `add_employee` AFTER INSERT ON `tb_m_employee` FOR EACH ROW BEGIN  
	INSERT INTO tb_m_account (`id`, `token`) VALUES (NEW.id, NEW.id);
END
$$
DELIMITER ;

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
-- Table structure for table `tb_tr_application_role`
--

CREATE TABLE `tb_tr_application_role` (
  `id` int(11) NOT NULL,
  `application` int(2) NOT NULL,
  `role` varchar(11) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(3, '14045', 'EMP'),
(4, '14422', 'TRN'),
(5, '14422', 'EMP'),
(6, '14422', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_menu`
--

CREATE TABLE `tb_tr_menu` (
  `id` int(11) NOT NULL,
  `display_name` varchar(50) NOT NULL,
  `mapping` varchar(50) NOT NULL,
  `application_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_m_account`
--
ALTER TABLE `tb_m_account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `status` (`status`);

--
-- Indexes for table `tb_m_account_status`
--
ALTER TABLE `tb_m_account_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_application`
--
ALTER TABLE `tb_m_application`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_m_employee`
--
ALTER TABLE `tb_m_employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `manager` (`manager`);

--
-- Indexes for table `tb_m_role`
--
ALTER TABLE `tb_m_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tr_application_role`
--
ALTER TABLE `tb_tr_application_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role` (`role`),
  ADD KEY `application` (`application`);

--
-- Indexes for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee` (`employee`),
  ADD KEY `role` (`role`);

--
-- Indexes for table `tb_tr_menu`
--
ALTER TABLE `tb_tr_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `application_role` (`application_role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_m_application`
--
ALTER TABLE `tb_m_application`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_tr_application_role`
--
ALTER TABLE `tb_tr_application_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tb_tr_menu`
--
ALTER TABLE `tb_tr_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Constraints for table `tb_tr_application_role`
--
ALTER TABLE `tb_tr_application_role`
  ADD CONSTRAINT `tb_tr_application_role_ibfk_1` FOREIGN KEY (`role`) REFERENCES `tb_m_role` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_application_role_ibfk_2` FOREIGN KEY (`application`) REFERENCES `tb_m_application` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `tb_m_employee` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_2` FOREIGN KEY (`role`) REFERENCES `tb_m_role` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_tr_menu`
--
ALTER TABLE `tb_tr_menu`
  ADD CONSTRAINT `tb_tr_menu_ibfk_1` FOREIGN KEY (`application_role`) REFERENCES `tb_tr_application_role` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
