-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2016 a las 03:07:11
-- Versión del servidor: 5.7.9
-- Versión de PHP: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

DROP TABLE IF EXISTS `administrador`;
CREATE TABLE IF NOT EXISTS `administrador` (
  `cod_empleado` int(10) NOT NULL,
  `id_administrador` varchar(11) NOT NULL,
  PRIMARY KEY (`id_administrador`),
  KEY `cod_empleado` (`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`cod_empleado`, `id_administrador`) VALUES
(3, 'a3'),
(4, 'a4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atracciones`
--

DROP TABLE IF EXISTS `atracciones`;
CREATE TABLE IF NOT EXISTS `atracciones` (
  `cod_atraccion` int(9) NOT NULL,
  `nom_atraccion` varchar(20) NOT NULL,
  `fh_revision` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `id_administrador` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`cod_atraccion`),
  KEY `id_administrador` (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `atracciones`
--

INSERT INTO `atracciones` (`cod_atraccion`, `nom_atraccion`, `fh_revision`, `id_administrador`) VALUES
(1, 'Lanzadera', '2016-05-26 00:00:00', 'a3'),
(2, 'Sillas Voladoras', '2016-06-05 05:02:29', 'a3'),
(3, 'Autos Locos', NULL, NULL),
(4, 'Casa Encantada', NULL, NULL),
(5, 'Tornado', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

DROP TABLE IF EXISTS `entrada`;
CREATE TABLE IF NOT EXISTS `entrada` (
  `num_entrada` int(11) NOT NULL AUTO_INCREMENT,
  `fh_emision` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `precio` double NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `cod_taquillero` int(10) NOT NULL,
  PRIMARY KEY (`num_entrada`,`fh_emision`),
  KEY `cod_taquillero` (`cod_taquillero`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`num_entrada`, `fh_emision`, `precio`, `tipo`, `cod_taquillero`) VALUES
(1, '2016-06-05 02:51:55', 10, 'Paseo', 2),
(2, '2016-06-05 02:52:13', 0, 'Personal', 2),
(3, '2016-06-05 02:53:48', 20, 'Normal', 1),
(4, '2016-06-05 02:53:58', 15, 'Descuento', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

DROP TABLE IF EXISTS `personal`;
CREATE TABLE IF NOT EXISTS `personal` (
  `cod_empleado` int(10) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `telefono` int(9) NOT NULL,
  `nss` int(5) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  PRIMARY KEY (`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`cod_empleado`, `nombre`, `apellidos`, `dni`, `telefono`, `nss`, `direccion`, `categoria`) VALUES
(1, 'Alberto', 'Perez', '11122233A', 916506138, 88888, 'Calle1', 'Taquillero'),
(2, 'Paco', 'Paquin', '44455566A', 916525461, 11111, 'Calle2', 'Taquillero'),
(3, 'Luis', 'Garcia Sanchez', '12345678B', 123456789, 55555, 'Calle3', 'Administrador'),
(4, 'Laura', 'Cabrera', '36514250G', 695225334, 99922, 'Calle3', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revisiones`
--

DROP TABLE IF EXISTS `revisiones`;
CREATE TABLE IF NOT EXISTS `revisiones` (
  `fh_inicio_rev` date NOT NULL,
  `fh_fin_rev` date NOT NULL,
  `observaciones` varchar(25) NOT NULL,
  `cod_atraccion` int(9) NOT NULL,
  `id_administrador` varchar(11) NOT NULL,
  PRIMARY KEY (`fh_inicio_rev`,`cod_atraccion`),
  KEY `cod_atraccion` (`cod_atraccion`),
  KEY `id_administrador` (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `taquillero`
--

DROP TABLE IF EXISTS `taquillero`;
CREATE TABLE IF NOT EXISTS `taquillero` (
  `cod_empleado` int(10) NOT NULL,
  `horario` char(1) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `taquillero`
--

INSERT INTO `taquillero` (`cod_empleado`, `horario`) VALUES
(1, 'L'),
(2, 'X');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_3` FOREIGN KEY (`cod_empleado`) REFERENCES `personal` (`cod_empleado`);

--
-- Filtros para la tabla `atracciones`
--
ALTER TABLE `atracciones`
  ADD CONSTRAINT `atracciones_ibfk_1` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`id_administrador`);

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`cod_taquillero`) REFERENCES `taquillero` (`cod_empleado`);

--
-- Filtros para la tabla `revisiones`
--
ALTER TABLE `revisiones`
  ADD CONSTRAINT `revisiones_ibfk_2` FOREIGN KEY (`id_administrador`) REFERENCES `atracciones` (`id_administrador`),
  ADD CONSTRAINT `revisiones_ibfk_3` FOREIGN KEY (`cod_atraccion`) REFERENCES `atracciones` (`cod_atraccion`);

--
-- Filtros para la tabla `taquillero`
--
ALTER TABLE `taquillero`
  ADD CONSTRAINT `taquillero_ibfk_1` FOREIGN KEY (`cod_empleado`) REFERENCES `personal` (`cod_empleado`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
