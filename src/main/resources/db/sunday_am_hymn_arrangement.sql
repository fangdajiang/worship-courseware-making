-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2023-02-11 13:27:24
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
-- 表的结构 `sunday_am_hymn_arrangement`
--

CREATE TABLE `sunday_am_hymn_arrangement` (
                                              `id` int NOT NULL,
                                              `name_cn` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                                              `name_en` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                              `serial_number` int NOT NULL,
                                              `sunday_date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `sunday_am_hymn_arrangement`
--

INSERT INTO `sunday_am_hymn_arrangement` (`id`, `name_cn`, `name_en`, `serial_number`, `sunday_date`) VALUES
                                                                                                          (1, '齐肃立称颂主', 'Stand Up and Bless the Lord', 1, '20230129'),
                                                                                                          (2, '祂除我罪', 'He Took My Sins Away', 2, '20230129'),
                                                                                                          (3, '祢信实何广大', 'Great Is Thy Faithfulness', 3, '20230129'),
                                                                                                          (4, '我何处去？', 'Where Shall I be?', 4, '20230129');

--
-- 转储表的索引
--

--
-- 表的索引 `sunday_am_hymn_arrangement`
--
ALTER TABLE `sunday_am_hymn_arrangement`
    ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
