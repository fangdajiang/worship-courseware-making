-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2023-02-13 16:23:34
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
-- 表的结构 `sunday_am_menu`
--

CREATE TABLE `sunday_am_menu` (
                                  `id` int NOT NULL,
                                  `host` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                                  `pastor` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                                  `sunday_date` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                                  `theological_subject` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `sunday_am_menu`
--

INSERT INTO `sunday_am_menu` (`id`, `call_to_worship`, `doctrine`, `end_hymn`, `host`, `new_testament_reader`, `old_testament_reader`, `pastor_praying`, `praise_prayer`, `preacher`, `sermon`, `sermon_hymn`, `sunday_date`, `thanks_prayer`, `theological_subject`) VALUES ('1', '诗篇118:1-4', '1689改革宗浸信会信仰告白（4.2）', '主在我们中间', '赵颐执事', '曹以琳', '曹以琳', '陈雅各', '张静', '陈雅各', '罗马书3:9-20，没有义人', '我何处去', '20230129', '闫晓燕', '我们今天聚集是为要敬拜那位审判罪人的神');
--
-- 转储表的索引
--

--
-- 表的索引 `sunday_am_menu`
--
ALTER TABLE `sunday_am_menu`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3xfhs3insnrjgwavhvcgm0enu` (`sunday_date`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
