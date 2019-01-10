/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.21 : Database - prueba
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/prueba /*!40100 DEFAULT CHARACTER SET latin1 */;

USE prueba;

/*Table structure for table pru_cliente */

DROP TABLE IF EXISTS pru_cliente;

CREATE TABLE pru_cliente (
  id int(11) NOT NULL AUTO_INCREMENT,
  cedula varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  nombre varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

/*Table structure for table pru_prestamo */

DROP TABLE IF EXISTS pru_prestamo;

CREATE TABLE pru_prestamo (
  id int(11) NOT NULL AUTO_INCREMENT,
  idCliente int(11) NOT NULL,
  fecha timestamp NOT NULL,
  monto decimal(10,2) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_clienteprestamo (idCliente),
  CONSTRAINT fk_clienteprestamo FOREIGN KEY (idCliente) REFERENCES pru_cliente (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
