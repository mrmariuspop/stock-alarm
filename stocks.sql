-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: oct. 16, 2020 la 02:43 PM
-- Versiune server: 10.4.8-MariaDB
-- Versiune PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `stocks`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `alarm`
--

CREATE TABLE `alarm` (
  `id` int(11) NOT NULL,
  `stock_symbol` varchar(255) NOT NULL,
  `upper_threshold` int(11) NOT NULL,
  `lower_threshold` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `alarm`
--

INSERT INTO `alarm` (`id`, `stock_symbol`, `upper_threshold`, `lower_threshold`, `status`) VALUES
(7, 'BET', 14, 13, 'ACTIVE'),
(8, 'BET-XT', 14, 13, 'ACTIVE'),
(9, 'BET-R', 50, 50, 'ACTIVE'),
(10, 'TLV', 15, 10, 'INACTIVE');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `encryption`
--

CREATE TABLE `encryption` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `secret` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `encryption`
--

INSERT INTO `encryption` (`id`, `password`, `secret`) VALUES
(1, 'JX7KaoO4hb5xUlH8eheRcg==', 'secret');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `spring_session`
--

CREATE TABLE `spring_session` (
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `last_edited` timestamp NULL DEFAULT NULL,
  `alarm_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `stock`
--

INSERT INTO `stock` (`id`, `symbol`, `price`, `creation_date`, `last_edited`, `alarm_id`) VALUES
(4, 'TLV', 10000, '2020-10-16 09:01:20', '2020-10-16 12:26:36', 10),
(5, 'BET', 321, '2020-10-16 09:03:35', '2020-10-16 09:03:35', 7),
(6, 'BET-R', 20, '2020-10-16 09:03:39', '2020-10-16 12:17:08', 9),
(7, 'BET-XT', 123, '2020-10-16 09:03:46', '2020-10-16 09:03:46', 8);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `email`, `password`) VALUES
(6, 'string', 'string', 'pop@gmail.com', '$2a$12$oq9iIyva6xzOMpQW6AgqEePXwoTRUChf2REReV6eRiQhfz6MGrVKu'),
(7, 'marius', 'pop', 'test@test.com', '$2a$12$oq9iIyva6xzOMpQW6AgqEe/lyJOPamwx1Hnx6CdJqPJIYgBSZrXwK'),
(8, 'sica', 'marius', 'm@', '$2a$12$oq9iIyva6xzOMpQW6AgqEetp442tvnyF/mYEtsDvi9sx3WA7/qQR.'),
(9, 'Marius ', 'Pop', 'mrmariuspop@gmail.com', '$2a$12$oq9iIyva6xzOMpQW6AgqEelpAUMOcK2B2QHb38/sB6eav3.HDoePy');

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `alarm`
--
ALTER TABLE `alarm`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `encryption`
--
ALTER TABLE `encryption`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME`);

--
-- Indexuri pentru tabele `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_ID`,`ATTRIBUTE_NAME`),
  ADD KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID`);

--
-- Indexuri pentru tabele `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `alarm`
--
ALTER TABLE `alarm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pentru tabele `encryption`
--
ALTER TABLE `encryption`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pentru tabele `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pentru tabele `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
