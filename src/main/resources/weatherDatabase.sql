create schema weather;
use weather;

CREATE TABLE `astronomy` (
  `idastronomy` int(11) NOT NULL AUTO_INCREMENT,
  `sunrise` time DEFAULT NULL,
  `sunset` time DEFAULT NULL,
  PRIMARY KEY (`idastronomy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `atmosphere` (
  `idatmosphere` int(11) NOT NULL AUTO_INCREMENT,
  `humidity` int(11) DEFAULT NULL,
  `pressure` float DEFAULT NULL,
  `rising` int(11) DEFAULT NULL,
  `visibility` float DEFAULT NULL,
  PRIMARY KEY (`idatmosphere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `weathercode` (
  `idweathercode` int(11) NOT NULL,
  `weather` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idweathercode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `location` (
  `idlocation` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(45) DEFAULT NULL,
  `zone` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idlocation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wind` (
  `idwind` int(11) NOT NULL AUTO_INCREMENT,
  `chill` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  PRIMARY KEY (`idwind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `today` (
  `idtoday` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT NULL,
  `idcurrentweather` int(11) DEFAULT NULL,
  `currenttemperature` int(11) DEFAULT NULL,
  `idastronomy` int(11) DEFAULT NULL,
  `idatmosphere` int(11) DEFAULT NULL,
  `idwind` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtoday`),
  KEY `today_astronomy_idx` (`idastronomy`),
  KEY `today_atmosphere_idx` (`idatmosphere`),
  KEY `today_wind_idx` (`idwind`),
  KEY `today_weathercode_idx` (`idcurrentweather`),
  CONSTRAINT `today_astronomy` FOREIGN KEY (`idastronomy`) REFERENCES `astronomy` (`idastronomy`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today_atmosphere` FOREIGN KEY (`idatmosphere`) REFERENCES `atmosphere` (`idatmosphere`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today_weathercode` FOREIGN KEY (`idcurrentweather`) REFERENCES `weathercode` (`idweathercode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `today_wind` FOREIGN KEY (`idwind`) REFERENCES `wind` (`idwind`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `result` (
  `idresult` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) DEFAULT NULL,
  `idlocation` int(11) DEFAULT NULL,
  `idtoday` int(11) DEFAULT NULL,
  `pubdate` timestamp NULL DEFAULT NULL,
  `units` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`idresult`),
  KEY `result_today_idx` (`idtoday`),
  KEY `result_location_idx` (`idlocation`),
  CONSTRAINT `result_location` FOREIGN KEY (`idlocation`) REFERENCES `location` (`idlocation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `result_today` FOREIGN KEY (`idtoday`) REFERENCES `today` (`idtoday`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `forecast` (
  `idforecast` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `idforecastweather` int(11) DEFAULT NULL,
  `hightemperature` int(11) DEFAULT NULL,
  `lowtemperature` int(11) DEFAULT NULL,
  `idresult` int(11) DEFAULT '-1',
  PRIMARY KEY (`idforecast`),
  KEY `forecast_result_idx` (`idresult`),
  KEY `forecast_weathercode_idx` (`idforecastweather`),
  CONSTRAINT `forecast_result` FOREIGN KEY (`idresult`) REFERENCES `result` (`idresult`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `forecast_weathercode` FOREIGN KEY (`idforecastweather`) REFERENCES `weathercode` (`idweathercode`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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