--TOP 50 LOWEST ERAS (MINUS THE DEAD BALL PERIOD)

SELECT p.playerID,
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME,
SUM(p.ER)/SUM(p.IPOuts/3)*9 AS ERA, # IPOuts: Total Outs
SUM(p.IPOuts/3) AS IP,
m.birthYear AS BirthYear
FROM Pitching p, Master m
WHERE p.playerID = m.playerID
GROUP BY playerID
HAVING (IP >= 1000) AND (BirthYear > 1900)
ORDER BY ERA ASC
LIMIT 50;

--THEORETICAL TEAM RUNS CREATED CAREER
--(Estimates the Number of Runs a Player is Responsible for)

select b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME,  ((b.H+b.BB)+(2.4*(b.AB+b.BB)))*(t.TB+(3*(b.AB+b.BB)))/(9*(b.AB+b.BB))-(.9*(b.AB+b.BB)) AS TTRC
FROM Batting b, Master m, TOTBASES as t
WHERE b.playerID = m.playerID AND t.playerID = m.playerID
GROUP BY playerID  
ORDER BY TTRC 
DESC LIMIT 50;

--TOTAL BASES CAREER
--(Single = 1 Base + Double = 2 Bases + Triple = 3 Bases + Homer = 4 Bases)

CREATE VIEW TOTBASES AS
SELECT playerID,
(SUM(H)+(SUM(2B)*2)+(SUM(3B)*3)+(SUM(HR)*4)) AS TB 
FROM Batting
GROUP BY playerID;

SELECT playerID, TB FROM TOTBASES; // CALLS FOR TOTBASES

--BEST TOTAL BASES YEAR

CREATE VIEW TOTBYR AS # We create a view to store a query
SELECT playerID, yearID,
(H+(2B*2)+(3B*3)+(HR*4)) AS TB 
FROM Batting;

SELECT playerID, TB FROM TOTBYR // Returns Top 50 TB for 2010
WHERE yearID = 2010
ORDER BY TB DESC
LIMIT 50;

--THEORETICAL TEAM RUNS CREATED EVER IN A YEAR

select b.yearID, b.playerID, 
CONCAT(m.nameFirst, " ", m.nameLast) AS NAME,  ((b.H+b.BB)+(2.4*(b.AB+b.BB)))*(t.TB+(3*(b.AB+b.BB)))/(9*(b.AB+b.BB))-(.9*(b.AB+b.BB)) AS TTRC
FROM Batting b, Master m, TOTBYR as t
WHERE b.playerID = m.playerID AND t.playerID = m.playerID 
AND b.yearID = t.yearID AND t.yearID = 2011 # Every year or just 2010
ORDER BY TTRC 
DESC LIMIT 50;

--SHOW ALL VIEWS IN DATABASE

SHOW FULL TABLES 
IN bbstats 
WHERE TABLE_TYPE LIKE 'VIEW';

--DELETE A DATABASE VIEW

drop view databaseView;

--BATTING AVERAGE ON BALLS IN PLAY
--(A Players Skill at Getting Hits)

SELECT b.playerID, CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
yearID, teamID, 
(b.H-b.HR)/(b.AB-b.SO-b.HR+b.SF) AS BABIP
FROM batting b, Master m
WHERE b.playerID = m.playerID AND b.yearID = 2011 AND b.AB > 300
ORDER BY BABIP DESC
LIMIT 50;

--Weighted On Base Average or wOBA
--(OBP doesn't weight for how far they reached base. SLG doesn't consider walks)

SELECT b.playerID, CONCAT(m.nameFirst, " ", m.nameLast) AS NAME, 
yearID, teamID, 
(.72*(b.BB-b.IBB))+(.75*b.hbp)+(.90*b.h)+(1.84)+(1.24*b.2b)+(1.56*b.3b)+(1.95*b.hr)/b.ab AS wOBA 
FROM batting b, Master m
WHERE b.playerID = m.playerID AND b.yearID = 2011 AND b.AB > 300
ORDER BY wOBA DESC
LIMIT 50;