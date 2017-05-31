create schema weather;
use weather;


CREATE TABLE `astronomy` (
  `idastronomy` int(11) NOT NULL AUTO_INCREMENT,
  `sunrise` time DEFAULT NULL,
  `sunset` time DEFAULT NULL,
  PRIMARY KEY (`idastronomy`)
) ;

CREATE TABLE `atmosphere` (
  `idatmosphere` int(11) NOT NULL AUTO_INCREMENT,
  `humidity` int(11) DEFAULT NULL,
  `pressure` float DEFAULT NULL,
  `rising` int(11) DEFAULT NULL,
  `visibility` float DEFAULT NULL,
  PRIMARY KEY (`idatmosphere`)
);

CREATE TABLE `weathercode` (
  `idweathercode` int(11) NOT NULL,
  `weather` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idweathercode`)
) ;

CREATE TABLE `wind` (
  `idwind` int(11) NOT NULL AUTO_INCREMENT,
  `chill` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  PRIMARY KEY (`idwind`)
) ;

CREATE TABLE `location` (
  `woeid` int(11) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `zone` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `lastupdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`woeid`)
) COLLATE utf8mb4_unicode_ci;

CREATE TABLE `today` (
  `idtoday` int(11) NOT NULL AUTO_INCREMENT,
  `woeid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `idcurrentweather` int(11) DEFAULT NULL,
  `currenttemperature` int(11) DEFAULT NULL,
  `idastronomy` int(11) DEFAULT NULL,
  `idatmosphere` int(11) DEFAULT NULL,
  `idwind` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtoday`),
  CONSTRAINT `today-astronomy` FOREIGN KEY (`idastronomy`) REFERENCES `astronomy` (`idastronomy`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today-atmosphere` FOREIGN KEY (`idatmosphere`) REFERENCES `atmosphere` (`idatmosphere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today-location` FOREIGN KEY (`woeid`) REFERENCES `location` (`woeid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today-weathercode` FOREIGN KEY (`idcurrentweather`) REFERENCES `weathercode` (`idweathercode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today-wind` FOREIGN KEY (`idwind`) REFERENCES `wind` (`idwind`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

CREATE TABLE `forecast` (
  `idforecast` int(11) NOT NULL AUTO_INCREMENT,
  `woeid` int(11) NOT NULL,
  `date` date NOT NULL,
  `idforecastweather` int(11) NOT NULL,
  `hightemperature` int(11) DEFAULT NULL,
  `lowtemperature` int(11) DEFAULT NULL,
  PRIMARY KEY (`idforecast`),
  CONSTRAINT `forecast-location` FOREIGN KEY (`woeid`) REFERENCES `location` (`woeid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `forecast-weathercode` FOREIGN KEY (`idforecastweather`) REFERENCES `weathercode` (`idweathercode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


insert into weathercode (idweathercode, weather) values 
(0, 'tornado'),
(1, 'tropical storm'),
(2, 'hurricane'),
(3, 'severe thunderstorms'),
(4, 'thunderstorms'),
(5, 'mixed rain and snow'),
(6, 'mixed rain and sleet'),
(7, 'mixed snow and sleet'),
(8, 'freezing drizzle'),
(9, 'drizzle'),
(10, 'freezing rain'),
(11, 'showers'),
(12, 'showers'),
(13, 'snow flurries'),
(14, 'light snow showers'),
(15, 'blowing snow'),
(16, 'snow'),
(17, 'hail'),
(18, 'sleet'),
(19, 'dust'),
(20, 'foggy'),
(21, 'haze'),
(22, 'smoky'),
(23, 'blustery'),
(24, 'windy'),
(25, 'cold'),
(26, 'cloudy'),
(27, 'mostly cloudy (night)'),
(28, 'mostly cloudy (day)'),
(29, 'partly cloudy (night)'),
(30, 'partly cloudy (day)'),
(31, 'clear (night)'),
(32, 'sunny'),
(33, 'fair (night)'),
(34, 'fair (day)'),
(35, 'mixed rain and hail'),
(36, 'hot'),
(37, 'isolated thunderstorms'),
(38, 'scattered thunderstorms'),
(39, 'scattered thunderstorms'),
(40, 'scattered showers'),
(41, 'heavy snow'),
(42, 'scattered snow showers'),
(43, 'heavy snow'),
(44, 'partly cloudy'),
(45, 'thundershowers'),
(46, 'snow showers'),
(47, 'isolated thundershowers'),
(3200, 'not available');