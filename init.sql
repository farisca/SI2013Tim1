-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2014 at 10:30 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jkp`
--
CREATE DATABASE `jkp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `jkp`;

-- --------------------------------------------------------

--
-- Table structure for table `pristupnipodaci`
--

CREATE TABLE IF NOT EXISTS `pristupnipodaci` (
  `korisnickoIme` varchar(30) NOT NULL,
  `lozinka` varchar(20) NOT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pristupnipodaci`
--

INSERT INTO `pristupnipodaci` (`korisnickoIme`, `lozinka`) VALUES
('aalic1', '123456'),
('acamdzic1', '123456'),
('acelik1', '123456'),
('dazinovic1', '123456'),
('dbajramovic1', '123456'),
('fcakaric1', '123456'),
('heminagic1', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `radninalog`
--

CREATE TABLE IF NOT EXISTS `radninalog` (
  `brojRadnogNaloga` int(11) NOT NULL,
  `datumKreiranja` date NOT NULL,
  `kreatorNaloga` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL,
  `posao` varchar(20) NOT NULL,
  `opisPosla` varchar(200) DEFAULT NULL,
  `planiraniDatumIzvrsenja` date NOT NULL,
  `izvrsiocPosla` varchar(30) NOT NULL,
  `potrebniMaterijal` varchar(200) NOT NULL,
  `lokacija` varchar(50) NOT NULL,
  `datumIzvrsenja` date DEFAULT NULL,
  `utrosenoVrijeme` time DEFAULT NULL,
  `razlogStorniranja` varchar(200) DEFAULT NULL,
  `osobaKojaStornira` varchar(30) DEFAULT NULL,
  `razlogModifikovanja` varchar(200) DEFAULT NULL,
  `osobaKojaModifikuje` varchar(30) DEFAULT NULL,
  `datumModifikovanja` date DEFAULT NULL,
  `odobren` tinyint(1) NOT NULL DEFAULT '0',
  KEY `kreatorNaloga` (`kreatorNaloga`),
  KEY `izvrsiocPosla` (`izvrsiocPosla`),
  KEY `osobaKojaStornira` (`osobaKojaStornira`,`osobaKojaModifikuje`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `radninalog`
--

INSERT INTO `radninalog` (`brojRadnogNaloga`, `datumKreiranja`, `kreatorNaloga`, `status`, `posao`, `opisPosla`, `planiraniDatumIzvrsenja`, `izvrsiocPosla`, `potrebniMaterijal`, `lokacija`, `datumIzvrsenja`, `utrosenoVrijeme`, `razlogStorniranja`, `osobaKojaStornira`, `razlogModifikovanja`, `osobaKojaModifikuje`, `datumModifikovanja`, `odobren`) VALUES
(1, '2014-04-01', 'aalic1', 'storniran', 'zamjena vodomjera', NULL, '2014-04-02', 'aalic1', 'Vodomjer serijskog broja 123456', 'Sarajevo Ferhadija 12', '2014-04-01', NULL, 'Prvoaprilska šala', 'heminagic1', NULL, NULL, NULL, 1),
(2, '2014-05-11', 'acelik1', 'nezakljucen', 'zamjena cjevi', NULL, '2014-05-14', 'aalic1', 'cijev od 3 m promjera 20 cm', 'Sarajevo Zmaja od Bosne bb', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(3, '2014-05-01', 'acelik1', 'zakljucen', 'zamjena cijevi', NULL, '2014-05-03', 'aalic1', 'cijev od 2 m promjera 10 cm', 'Sarajevo Cicin han 51', '2014-05-03', '01:30:00', NULL, NULL, NULL, NULL, NULL, 1),
(4, '2014-04-15', 'aalic1', 'zakljucen', 'woma masina', NULL, '2014-04-23', 'aalic1', 'woma masina', 'Sarajevo Krupska br 13', '2014-04-23', '00:30:00', NULL, NULL, NULL, NULL, NULL, 1),
(5, '2014-01-01', 'heminagic1', 'storniran', 'ugradnja vodomjera', NULL, '2014-01-03', 'heminagic1', 'Vodomjer serijskog broja 255163', 'Sarajevo Dalmatinska 5', '2014-01-03', NULL, 'Temperatura je bila preniska za rad.', 'acelik1', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `zaposlenik`
--

CREATE TABLE IF NOT EXISTS `zaposlenik` (
  `korisnickoIme` varchar(30) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `tipUposlenika` varchar(20) NOT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zaposlenik`
--

INSERT INTO `zaposlenik` (`korisnickoIme`, `ime`, `prezime`, `tipUposlenika`) VALUES
('aalic1', 'Ahmed', 'Alic', 'obicni'),
('acamdzic1', 'Alen', 'Camdzic', 'obicni'),
('acelik1', 'Amina', 'Celik', 'privilegovani'),
('dazinovic1', 'Dejan', 'Azinovic', 'obicni'),
('dbajramovic1', 'Dado', 'Bajramovic', 'obicni'),
('fcakaric1', 'Faris', 'Cakaric', 'privilegovani'),
('heminagic1', 'Haris', 'Eminagic', 'neaktivan');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pristupnipodaci`
--
ALTER TABLE `pristupnipodaci`
  ADD CONSTRAINT `pristupnipodaci_ibfk_1` FOREIGN KEY (`korisnickoIme`) REFERENCES `zaposlenik` (`korisnickoIme`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `radninalog`
--
ALTER TABLE `radninalog`
  ADD CONSTRAINT `IzvrsiocPosla` FOREIGN KEY (`izvrsiocPosla`) REFERENCES `zaposlenik` (`korisnickoIme`) ON UPDATE CASCADE,
  ADD CONSTRAINT `KreatorNaloga` FOREIGN KEY (`kreatorNaloga`) REFERENCES `zaposlenik` (`korisnickoIme`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
