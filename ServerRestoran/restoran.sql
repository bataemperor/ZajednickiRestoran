-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2015 at 12:40 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `restoran`
--

-- --------------------------------------------------------

--
-- Table structure for table `konobar`
--

CREATE TABLE IF NOT EXISTS `konobar` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `konobar`
--

INSERT INTO `konobar` (`Username`, `Password`) VALUES
('Batica2', '12345'),
('neki user', '123123');

-- --------------------------------------------------------

--
-- Table structure for table `narudzbina`
--

CREATE TABLE IF NOT EXISTS `narudzbina` (
  `NarudzbinaID` int(11) NOT NULL,
  `DatumNarudzbine` datetime DEFAULT NULL,
  `BrojStola` int(11) DEFAULT NULL,
  `UkupanIznos` int(11) DEFAULT NULL,
  `Status` varchar(20) DEFAULT '0',
  `Username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NarudzbinaID`),
  KEY `KonobarNarudzbina` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `narudzbina`
--

INSERT INTO `narudzbina` (`NarudzbinaID`, `DatumNarudzbine`, `BrojStola`, `UkupanIznos`, `Status`, `Username`) VALUES
(1, '2015-05-06 15:20:15', 4, 150, 'placen', 'Batica2'),
(2, '2015-05-06 15:23:01', 1, 870, 'placen', 'Batica2'),
(3, '2015-05-07 13:31:06', 3, 1470, 'placen', 'Batica2'),
(4, '2015-05-07 13:41:00', 8, 1400, 'placen', 'Batica2'),
(5, '2015-05-07 15:09:01', 1, 8100, 'placen', 'Batica2'),
(6, '2015-05-07 16:44:57', 1, 150, 'placen', 'Batica2'),
(7, '2015-05-08 02:56:47', 1, 150, 'placen', 'neki user'),
(8, '2015-05-08 02:57:55', 2, 150, 'placen', 'neki user');

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE IF NOT EXISTS `proizvod` (
  `ProizvodID` int(11) NOT NULL,
  `NazivProizvoda` varchar(255) DEFAULT NULL,
  `CenaProizvoda` int(11) DEFAULT NULL,
  `KolicinaNaStanju` int(11) DEFAULT NULL,
  `TipProizvoda` varchar(255) DEFAULT NULL,
  `OpisProizvoda` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProizvodID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`ProizvodID`, `NazivProizvoda`, `CenaProizvoda`, `KolicinaNaStanju`, `TipProizvoda`, `OpisProizvoda`) VALUES
(1, 'Coca Cola', 260, 150, 'Pice', 'Osvezavajuce gazirano pice sa karamelom'),
(2, 'Coca Cola zero', 150, 150, 'Pice', 'Osvezavajuce gazirano pice bez secera'),
(3, 'Sprite', 150, 23, 'Pice', 'Osvezavajuce gazirano pice'),
(4, 'Fanta', 150, 233, 'Pice', 'Osvezavajuce gazirano pice od pomorandze'),
(5, 'Espresso', 120, 233, 'Pice', 'Kafa'),
(6, 'Dupli espresso', 150, 123, 'Pice', 'Kafa'),
(7, 'Dupli espresso', 150, 123, 'Pice', 'Kafa'),
(8, 'Espresso sa mlekom', 150, 232, 'Pice', 'Kafa'),
(9, 'Capuccino', 160, 213, 'Pice', 'Kafa'),
(10, 'Nes kafa', 180, 333, 'Pice', 'Kafa'),
(11, 'Caj', 120, 321, 'Pice', 'Caj'),
(12, 'Caj sa medom', 150, 321, 'Pice', 'Caj'),
(13, 'Tost sendvic', 300, 23, 'Dorucak', 'Tost sa namazom od rikote, praškom šunkom i gaudom, sa pomfritom i kecapom.'),
(14, 'Grcka salata', 400, 23, 'Predjelo', 'Paradajz, paprika, krastavac, crveni luk, origano, feta, zelena salata, masline.'),
(15, 'Cezar salata', 450, 12, 'Predjelo', 'Štapici grilovane piletine na ajzberg salati sa cezar dresingom i krutonima.'),
(16, 'Salata sa tunom', 450, 23, 'Predjelo', 'Mešane zelene salate sa štapicima grilovane tune, šargarepom, ?eri paradajzom i prelivom od pomorandže'),
(17, 'Pileca supa', 250, 31, 'Supe', 'Pileca supa sa rezancima'),
(18, 'Govedja supa', 250, 31, 'Supe', 'Govedja supa sa knedlama'),
(19, 'Piletina sa kari sosom', 700, 42, 'Glavno jelo', 'Grilovani pileci file sa sosom od karija serviran sa basmati pirin?em.'),
(20, 'Losos stek', 1200, 20, 'Glavno jelo', 'grilovani losos sa mešanim zelenim salatama, ceri paradajzom, crvenim lukom i pinjolima'),
(21, 'Piletina na meksicki nacin', 1000, 20, 'Glavno jelo', 'piletina u pikantnom sosu od paradajza'),
(22, 'Pileci batak mariniran sa terijaki sosom', 1200, 23, 'Glavno jelo', 'pile?i batak mariniran u terijaki sosu sa basmati pirin?em'),
(23, 'Markiza', 250, 23, 'Desert', 'Kolac markiza'),
(24, 'Moskva šnit', 270, 23, 'Desert', 'Kolac moskva šnit'),
(25, 'Mango torta', 280, 23, 'Desert', 'Mango kolac'),
(26, 'Cheesecake', 270, 23, 'Desert', 'Cheesecake kola?');

-- --------------------------------------------------------

--
-- Table structure for table `stavkanarudzbine`
--

CREATE TABLE IF NOT EXISTS `stavkanarudzbine` (
  `NarudzbinaID` int(11) NOT NULL,
  `RbStavke` int(11) NOT NULL,
  `Kolicina` int(11) DEFAULT NULL,
  `Iznos` int(11) DEFAULT NULL,
  `ProizvodID` int(11) DEFAULT NULL,
  `Napomena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NarudzbinaID`,`RbStavke`),
  KEY `ProizvodID` (`ProizvodID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stavkanarudzbine`
--

INSERT INTO `stavkanarudzbine` (`NarudzbinaID`, `RbStavke`, `Kolicina`, `Iznos`, `ProizvodID`, `Napomena`) VALUES
(1, 1, 1, 150, 1, 'radice sad'),
(2, 1, 1, 150, 1, 'bas'),
(2, 2, 2, 300, 2, ''),
(2, 3, 3, 420, 3, ''),
(3, 1, 1, 150, 1, ''),
(3, 2, 2, 300, 2, ''),
(3, 3, 3, 420, 3, ''),
(3, 4, 4, 600, 1, ''),
(4, 1, 10, 1400, 3, '10 pepsija jbt'),
(5, 1, 54, 8100, 2, 'bas dosta'),
(6, 1, 1, 150, 2, 'be'),
(7, 1, 1, 150, 2, 'asdf'),
(8, 1, 1, 150, 2, 'asdf');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `narudzbina`
--
ALTER TABLE `narudzbina`
  ADD CONSTRAINT `KonobarNarudzbina` FOREIGN KEY (`Username`) REFERENCES `konobar` (`Username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `stavkanarudzbine`
--
ALTER TABLE `stavkanarudzbine`
  ADD CONSTRAINT `NarudzbinaStavkaNarudzbine` FOREIGN KEY (`NarudzbinaID`) REFERENCES `narudzbina` (`NarudzbinaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ProizvodStavkaNarudzbine` FOREIGN KEY (`ProizvodID`) REFERENCES `proizvod` (`ProizvodID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
