DELIMITER $$

CREATE FUNCTION sp_name (userid INT) RETURNS INT
BEGIN
	declare sellerid int;
	SELECT sid INTO sellerid FROM seller WHERE `uid`=userid;
	RETURN sellerid;
END$$

DELIMITER ;

select sp_name(2);