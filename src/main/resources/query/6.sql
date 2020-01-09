/*6. Поиск дат, в которые более, чем у 5 пассажиров день рождения*/

SELECT  DAY(birthdate) AS DAY, MONTH(birthdate) AS MONTH
FROM passenger
GROUP BY MONTH(birthdate),DAY(birthdate)
HAVING COUNT(MONTH(birthdate))>5 AND COUNT(DAY(birthdate))>5;