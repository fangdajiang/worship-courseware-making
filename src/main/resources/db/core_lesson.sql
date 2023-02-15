-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2023-02-15 10:33:56
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
-- 表的结构 `core_lesson`
--

CREATE TABLE `core_lesson` (
                               `id` int NOT NULL,
                               `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                               `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `core_lesson`
--

INSERT INTO `core_lesson` (`id`, `name`, `quantity`) VALUES
                                                         (1, '旧约概论', 18),
                                                         (2, '成员课程', 5);

--
-- 转储表的索引
--

--
-- 表的索引 `core_lesson`
--
ALTER TABLE `core_lesson`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_flfrci37l3aqdvd7aumavdo8c` (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
