/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.10-log : Database - sales
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sales` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sales`;

/*Table structure for table `all_choices` */

DROP TABLE IF EXISTS `all_choices`;

CREATE TABLE `all_choices` (
  `acid` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL,
  `spid` int(11) DEFAULT NULL,
  `bpid` int(11) DEFAULT NULL,
  PRIMARY KEY (`acid`),
  KEY `fk_ac_oid` (`oid`),
  KEY `fk_ac_spid` (`spid`),
  KEY `fk_ac_bpid` (`bpid`),
  CONSTRAINT `fk_ac_bpid` FOREIGN KEY (`bpid`) REFERENCES `brandedproducts` (`bpid`),
  CONSTRAINT `fk_ac_oid` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`),
  CONSTRAINT `fk_ac_spid` FOREIGN KEY (`spid`) REFERENCES `seller` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `all_choices` */

insert  into `all_choices`(`acid`,`oid`,`spid`,`bpid`) values (1,1,1,2),(2,1,2,2),(3,2,1,3),(4,2,2,2),(5,1,NULL,NULL);

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `brand` */

insert  into `brand`(`bid`,`name`) values (1,'Swiss'),(2,'LG'),(3,'Asus'),(4,'Honda'),(5,'Maruti');

/*Table structure for table `brandedproducts` */

DROP TABLE IF EXISTS `brandedproducts`;

CREATE TABLE `brandedproducts` (
  `bpid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bpid`),
  KEY `fk_bp_pid` (`pid`),
  KEY `fk_bp_bid` (`bid`),
  CONSTRAINT `fk_bp_bid` FOREIGN KEY (`bid`) REFERENCES `brand` (`bid`),
  CONSTRAINT `fk_bp_pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `brandedproducts` */

insert  into `brandedproducts`(`bpid`,`name`,`pid`,`bid`) values (1,'Honda Car',3,4),(2,'LG TV',1,2),(3,'Asus Laptop',5,3),(4,'Swiss Watch',1,1),(5,'LG Laptop',5,2),(6,'Maruti Car',3,5);

/*Table structure for table `card_details` */

DROP TABLE IF EXISTS `card_details`;

CREATE TABLE `card_details` (
  `cdid` int(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(15) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `pmid` int(11) NOT NULL,
  PRIMARY KEY (`cdid`),
  UNIQUE KEY `pmid` (`pmid`),
  KEY `fk_cd_uid` (`uid`),
  CONSTRAINT `fk_cd_pmid` FOREIGN KEY (`pmid`) REFERENCES `payment` (`pmid`),
  CONSTRAINT `fk_cd_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `card_details` */

insert  into `card_details`(`cdid`,`card_no`,`uid`,`pmid`) values (1,'12341234143',5,2),(2,'12341234143',5,3),(3,'34534546',3,1);

/*Table structure for table `cash` */

DROP TABLE IF EXISTS `cash`;

CREATE TABLE `cash` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `pmid` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `pmid` (`pmid`),
  CONSTRAINT `fk_c_pmid` FOREIGN KEY (`pmid`) REFERENCES `payment` (`pmid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `cash` */

insert  into `cash`(`cid`,`amount`,`pmid`) values (1,543,1),(2,54,2);

/*Table structure for table `city_incharge` */

DROP TABLE IF EXISTS `city_incharge`;

CREATE TABLE `city_incharge` (
  `ciid` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ciid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `city_incharge` */

insert  into `city_incharge`(`ciid`,`city`) values (1,'Pune'),(2,'Bangalore'),(3,'Amritsar'),(4,'Vrindavan'),(5,'Mayapur');

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `rfid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `bpid` int(11) NOT NULL,
  PRIMARY KEY (`iid`),
  KEY `fk_i_bpid` (`bpid`),
  KEY `fk_i_sid` (`sid`),
  CONSTRAINT `fk_i_bpid` FOREIGN KEY (`bpid`) REFERENCES `brandedproducts` (`bpid`),
  CONSTRAINT `fk_i_sid` FOREIGN KEY (`sid`) REFERENCES `seller` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`iid`,`rfid`,`sid`,`bpid`) values (1,12,1,2),(2,13,2,3),(3,14,2,2);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `fk_o_uid` (`uid`),
  CONSTRAINT `fk_o_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`oid`,`total`,`uid`,`status`) values (1,12000,1,0),(2,124334,3,1),(3,1234,2,0);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `pmid` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` int(11) NOT NULL,
  `oid` int(11) DEFAULT NULL COMMENT 'nullable for donations',
  PRIMARY KEY (`pmid`),
  UNIQUE KEY `oid` (`oid`),
  CONSTRAINT `fk_pm_oid` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/* Improvement- not null CHECK (payment_type IN ('trial', 'expired', 'active', 'cancelled')), */

/*Data for the table `payment` */

insert  into `payment`(`pmid`,`payment_type`,`oid`) values (1,1,1),(2,2,2),(3,2,3);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`pid`,`name`) values (1,'Watch'),(2,'TV'),(3,'Car'),(4,'Bike'),(5,'Laptop');

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `uid` (`uid`),
  CONSTRAINT `fk_s_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `seller` */

insert  into `seller`(`sid`,`uid`) values (1,2),(2,6);

/*Table structure for table `seller_proposals` */

DROP TABLE IF EXISTS `seller_proposals`;

CREATE TABLE `seller_proposals` (
  `spid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  PRIMARY KEY (`spid`),
  KEY `fk_sp_sid` (`sid`),
  KEY `fk_sp_bid` (`bid`),
  CONSTRAINT `fk_sp_bid` FOREIGN KEY (`bid`) REFERENCES `brand` (`bid`),
  CONSTRAINT `fk_sp_sid` FOREIGN KEY (`sid`) REFERENCES `seller` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `seller_proposals` */

insert  into `seller_proposals`(`spid`,`sid`,`bid`) values (1,1,2),(2,1,3),(3,2,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`name`) values (1,'Krishna'),(2,'Gauranga'),(3,'Radha'),(4,'Baladev'),(5,'Subhadra'),(6,'Nityananda');

/*Table structure for table `wallet` */

DROP TABLE IF EXISTS `wallet`;

CREATE TABLE `wallet` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `money` int(11) DEFAULT NULL,
  `pmid` int(11) NOT NULL,
  PRIMARY KEY (`wid`),
  UNIQUE KEY `pmid` (`pmid`),
  KEY `fk_w_uid` (`uid`),
  CONSTRAINT `fk_w_pmid` FOREIGN KEY (`pmid`) REFERENCES `payment` (`pmid`),
  CONSTRAINT `fk_w_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `wallet` */

insert  into `wallet`(`wid`,`uid`,`money`,`pmid`) values (1,1,50000000,1),(2,2,1423423432,3),(4,3,32423,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
