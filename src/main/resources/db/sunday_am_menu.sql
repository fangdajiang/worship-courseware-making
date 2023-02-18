-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2023-02-17 14:58:07
-- 服务器版本： 8.0.31
-- PHP 版本： 8.0.19

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


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

CREATE TABLE `sunday_am_menu`
(
    `id`                      int                                      NOT NULL,
    `call_to_worship`         varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `doctrine_abbr`           varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `doctrine_content`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `doctrine_title`          varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `duty_manager`            varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `end_hymn`                varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `host`                    varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `new_testament_reader`    varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `new_testament_scripture` varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `old_testament_reader`    varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `old_testament_scripture` varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `pastor_praying`          varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `praise_prayer`           varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `preacher`                varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `sermon_hymn`             varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `sermon_outline`          varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `sermon_title`            varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `servant_for_audio_video` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `servant_for_kids`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `sunday_date`             varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `thanks_prayer`           varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `theological_subject`     varchar(255) COLLATE utf8mb4_general_ci  NOT NULL,
    `usher`                   varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `worship_notice1`         varchar(2048) COLLATE utf8mb4_general_ci NOT NULL,
    `worship_notice2`         varchar(2048) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `sunday_am_menu`
--

INSERT INTO `sunday_am_menu` (`id`, `call_to_worship`, `doctrine_abbr`, `doctrine_content`, `doctrine_title`,
                              `duty_manager`, `end_hymn`, `host`, `new_testament_reader`, `new_testament_scripture`,
                              `old_testament_reader`, `old_testament_scripture`, `pastor_praying`, `praise_prayer`,
                              `preacher`, `sermon_hymn`, `sermon_outline`, `sermon_title`, `servant_for_audio_video`,
                              `servant_for_kids`, `sunday_date`, `thanks_prayer`, `theological_subject`, `usher`,
                              `worship_notice1`, `worship_notice2`)
VALUES (1, ''诗篇118:1-4'', ''1689改革宗浸信会信仰告白（4.2）'', ''第二条：xxx'',
        ''1689改革宗浸信会公认信条，第四章，上帝的创造之工'', ''方大江'', ''主在我们中间'', ''赵颐执事'', ''曹以琳'',
        ''以弗所书2:1-10'', ''曹以琳'', ''以赛亚书59:1-8'', ''陈雅各'', ''张静'', ''陈雅各'', ''我何处去'',
        ''导论：\r\n1. xxx\r\n2. yyy\r\n3. zzz'', ''罗马书3:9-20，没有义人'', ''李文豪'', ''马志娟，陈子宇'',
        ''20230129 '', ''闫晓燕'', ''我们今天聚集是为要敬拜那位审判罪人的神'', ''张莹，张晓洁'',
        ''• 欢迎参加主日上午9:00-10:00的核心课程，系统性地教导和装备各位学员。目前正在进行的课程有《旧约概论·下》和《成员课程》。2月19日我们也会开始一门新的课程《新手上路》，并继续有《旧约概论》课程，欢迎各位参加。\r\n• 主日上午崇拜于10:15开始。为避免给聚会带来打扰，宣召后才到达的会众要在后门等候区等到解散儿童后才能进入就坐。若有主餐，主餐开始后亦不能进入会堂，需等候主餐结束。请早做准备、避免迟到。\r\n• 教会每周日下午1:30有分享祷告聚会。地方教会成员的共同祷告和彼此分享也是教会生活的重要部分，我们期待各位弟兄姊妹和朋友们的参加。下午聚会的内容包括分享、共同祷告和短讲。\r\n• 请成员和访客在周间主动登入通知群查看当周地址。没有在通知群里的访客或慕道的朋友请在今天或周间跟任一牧师询问。'',
        ''• 我们欢迎孩子在不打扰聚会的情况下与父母一同参加主堂敬拜，父母也可以在《主在我们中间》后把孩子交托给儿主老师和助教带到教室，自己参与崇拜和听道。\r\n• 主餐是为受洗且委身于某个教会的基督徒而准备的。来自其他教会的访客需提前与长老面谈获得书面许可方可参与主餐。每月第一个主日和成员大会的当天，主餐在公共崇拜中，其他主日的主餐在分享祷告聚会中。\r\n• 请注意主日的穿着得体，避免穿着暴露大片肌肤或不够尊重他人的衣着，例如吊带衫、热裤、超短裙、拖鞋、有脏话的T恤衫等等。如果您邀请朋友前来，请务必要提醒所邀请的朋友注意穿着，也请大家互相提醒。\r\n• 为保护教会的安全，请不要将教会地址发布在社交网络或微信（及QQ或其他）群中，仅邀请自己熟识和信任的朋友来教会，并约在最近的地铁站碰面。'');

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
