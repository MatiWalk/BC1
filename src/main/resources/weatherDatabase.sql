CREATE DATABASE Weather;
use Weather;
CREATE TABLE `result` (
  `idresult` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `astronomy_sunrise` time DEFAULT NULL,
  `astronomy_sunset` time DEFAULT NULL,
  `wind_chill` int(11) DEFAULT NULL,
  `wind_direction` int(11) DEFAULT NULL,
  `wind_speed` int(11) DEFAULT NULL,
  `atmosphere_humidity` int(11) DEFAULT NULL,
  `atmosphere_pressure` float DEFAULT NULL,
  `atmosphere_barometricpressure` int(11) DEFAULT NULL,
  `atmosphere_visibility` float DEFAULT NULL,
  `pubDate` datetime DEFAULT NULL,
  `unit` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idresult`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `weather_code` (
  `idweather_code` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idweather_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `day` (
  `idday` int(11) NOT NULL AUTO_INCREMENT,
  `day` date DEFAULT NULL,
  `idweather_code` int(11) DEFAULT NULL,
  `current_temperature` int(11) DEFAULT NULL,
  `high_temperature` int(11) DEFAULT NULL,
  `low_temperature` int(11) DEFAULT NULL,
  `idresult` int(11) NOT NULL,
  PRIMARY KEY (`idday`),
  CONSTRAINT `idresult` FOREIGN KEY (`idresult`) REFERENCES `result` (`idresult`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idweathercode` FOREIGN KEY (`idweather_code`) REFERENCES `weather_code` (`idweather_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into weather_code (idweather_code, name) values 
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