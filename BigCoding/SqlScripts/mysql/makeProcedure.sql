/* for permanent environment changes- insert, updates in batch for consistent logical operations
source C:\Users\Saurabh\Desktop\proc_become_seller.sql
*/
DELIMITER $$

CREATE PROCEDURE `sales`.`proc_become_seller`(IN userid INT, OUT sellerid INT)
    BEGIN
	INSERT INTO `sales`.`seller` (`uid`) VALUES  (userid) ;
	UPDATE `sales`.`user` SET `isSeller` = 1 WHERE `uid` = userid ;
	SELECT sid INTO sellerid FROM seller WHERE `uid`=userid;
    END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE `sales`.`proc_remove_seller`(IN userid INT, OUT sellerid INT)
    BEGIN
    SELECT sid INTO sellerid FROM seller WHERE `uid`=userid;
    DELETE FROM `sales`.`item` where `sid`=sellerid;
    DELETE FROM `sales`.`seller_proposals` where `sid`=sellerid;
	DELETE FROM `sales`.`seller` where `sid`=sellerid;
	UPDATE `sales`.`user` SET `isSeller` = 0 WHERE `uid` = userid ;
    END$$

DELIMITER ;

call proc_become_seller(1,@sellerid);

select @sellerid;
 
drop procedure proc_become_seller;