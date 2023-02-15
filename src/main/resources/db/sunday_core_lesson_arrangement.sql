-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2023-02-15 10:33:35
-- 服务器版本： 8.0.31
-- PHP 版本： 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `courseware`
--

-- --------------------------------------------------------

--
-- 表的结构 `sunday_core_lesson_arrangement`
--

CREATE TABLE `sunday_core_lesson_arrangement` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `serial_number` int NOT NULL,
  `sunday_date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `teacher` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `sunday_core_lesson_arrangement`
--

INSERT INTO `sunday_core_lesson_arrangement` (`id`, `name`, `serial_number`, `sunday_date`, `teacher`) VALUES
(1, '旧约概论', 18, '20230129', 'panyi'),
(2, '成员课程', 5, '20230129', '陈雅各牧师');

--
-- 转储表的索引
--

--
-- 表的索引 `sunday_core_lesson_arrangement`
--
ALTER TABLE `sunday_core_lesson_arrangement`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
