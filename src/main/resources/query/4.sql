/*4. Поиск пассажиров, которые выполняют полет в период +-3 дня от своего дня рождения*/

SELECT p.* ,t.date
FROM passenger p
         INNER JOIN ticket t ON p.id_passenger = t.id_passenger
WHERE MONTH(p.birthdate) = MONTH(t.date)
  AND DAYOFMONTH(p.birthdate) - DAYOFMONTH(t.date) BETWEEN -3 AND 3;