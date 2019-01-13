--TOP 50 RBI
--(RBI - Runs Credited to a Batter Based on their at bats)

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.RBI) AS RBI 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
ORDER BY RBI DESC 
LIMIT 50;

--TOP 50 HR

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.HR) AS HR 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
ORDER BY HR DESC LIMIT 50;

--JOKE TOP BATTING AVERAGE

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
(SUM(b.H)/SUM(b.AB)) AS AVG 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
ORDER BY AVG DESC 
LIMIT 50;

--TOP 50 BATTING AVERAGE
--(Percentage of ABs that turn into a hit)

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.AB) AS AB, (SUM(b.H)/SUM(b.AB)) AS AVG 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
HAVING AB > 1000 # Makes Sure Player Has 1000 ABs
ORDER BY AVG DESC 
LIMIT 50;

--TOP 50 OBP
--(How often a player gets on base)
--* COALESCE - If the first value equals null use 0 instead. This is done
--because Sacrifice Flys weren't recorded in the early days of baseball

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.AB) AS AB, (SUM(b.H)/SUM(b.AB)) AS AVG, (SUM(b.H)+SUM(b.BB)+SUM(b.HBP))/(SUM(b.AB)+SUM(b.BB)+SUM(b.HBP)+COALESCE(SUM(b.SF), 0)) AS OBP 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
HAVING AB > 1000 
ORDER BY OBP 
DESC LIMIT 50;

--TOP 50 OBP WITH POSITION

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.AB) AS AB, (SUM(b.H)/SUM(b.AB)) AS AVG, (SUM(b.H)+SUM(b.BB)+SUM(b.HBP))/(SUM(b.AB)+SUM(b.BB)+SUM(b.HBP)+COALESCE(SUM(b.SF), 0)) AS OBP,
f.POS AS POS 
FROM Batting b, Master m, Fielding f 
WHERE b.playerID = m.playerID AND b.playerID = f.playerID
GROUP BY playerID 
HAVING AB > 1000 
ORDER BY OBP 
DESC LIMIT 50;

--YEARLY OBP WITH POSITION

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
b.AB AS AB, (b.H/b.AB) AS AVG, (b.H+b.BB+b.HBP)/(b.AB+b.BB+b.HBP+COALESCE(b.SF, 0)) AS OBP,
f.POS AS POS,
s.salary,
b.yearID 
FROM Batting b, Master m, Fielding f, Salaries s 
WHERE b.playerID = m.playerID AND b.playerID = f.playerID AND b.yearID = 2002 AND s.playerID = b.playerID AND s.yearID = b.yearID
GROUP BY playerID 
HAVING AB > 300 
ORDER BY OBP 
DESC LIMIT 50;

--TOP 50 SLG
--(Measure of the power of a hitter)

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
SUM(b.AB) AS AB, (SUM(b.H)/SUM(b.AB)) AS AVG, (SUM(b.H)+(SUM(b.2B)*2)+(SUM(b.3B)*3)+(SUM(b.HR)*4))/SUM(b.AB) AS SLG 
FROM Batting b, Master m 
WHERE b.playerID = m.playerID 
GROUP BY playerID 
HAVING AB > 1000 
ORDER BY SLG 
DESC LIMIT 50;